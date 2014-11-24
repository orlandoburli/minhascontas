package br.com.orlandoburli.minhascontas.tests.cases;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.orlandoburli.framework.core.be.exceptions.BeException;
import br.com.orlandoburli.framework.core.be.exceptions.validation.ValidationBeException;
import br.com.orlandoburli.framework.core.dao.DAOManager;
import br.com.orlandoburli.framework.core.dao.exceptions.DAOException;
import br.com.orlandoburli.minhascontas.model.be.acesso.UsuarioBe;
import br.com.orlandoburli.minhascontas.model.be.financeiro.CategoriaBe;
import br.com.orlandoburli.minhascontas.model.vo.acesso.UsuarioVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.CategoriaVo;
import br.com.orlandoburli.minhascontas.tests.builders.CategoriaBuilder;
import br.com.orlandoburli.minhascontas.tests.builders.UsuarioBuilder;
import br.com.orlandoburli.minhascontas.tests.utils.TestUtils;

@RunWith(JUnit4.class)
public class CategoriaTest {
	private DAOManager manager;
	private UsuarioBe usuarioBe;
	private CategoriaBe categoriaBe;

	@Test
	public void testaCadastroCategoria() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = usuarioBe.getByEmail("orlando.developermaster@gmail.com");

		CategoriaVo despesas = CategoriaBuilder.criaCategoriaDespesa(orlando);
		CategoriaVo receitas = CategoriaBuilder.criaCategoriaReceita(orlando);

		categoriaBe.save(despesas);
		categoriaBe.save(receitas);

		CategoriaVo receitasFixas = CategoriaBuilder.criaCategoriaReceitaFixas(orlando, despesas);

		categoriaBe.save(receitasFixas);

		CategoriaVo receitasVariaveis = CategoriaBuilder.criaCategoriaReceitaVariaveis(orlando, receitas);

		categoriaBe.save(receitasVariaveis);
	}

	@Test
	public void testaCadastroMultiUsuarios() throws BeException {
		criaDadosTeste();

		List<UsuarioVo> usuarios = usuarioBe.getList(null);

		for (UsuarioVo usuario : usuarios) {
			// Para cada usuario, criar as categorias de despesa
			CategoriaVo despesas = CategoriaBuilder.criaCategoriaDespesa(usuario);
			CategoriaVo receitas = CategoriaBuilder.criaCategoriaReceita(usuario);

			categoriaBe.save(despesas);
			categoriaBe.save(receitas);

			CategoriaVo receitasFixas = CategoriaBuilder.criaCategoriaReceitaFixas(usuario, despesas);
			categoriaBe.save(receitasFixas);

			CategoriaVo receitasVariaveis = CategoriaBuilder.criaCategoriaReceitaVariaveis(usuario, receitas);
			categoriaBe.save(receitasVariaveis);

			categoriaBe.save(CategoriaBuilder.criaCategoriaReceitaSalarios(usuario, receitasFixas));
		}

		UsuarioVo orlando = usuarioBe.getByEmail("orlando.developermaster@gmail.com");

		List<CategoriaVo> categoriasOrlando = categoriaBe.getByUsuario(orlando);

		// Verifica se existem 5 itens na lista
		assertThat(categoriasOrlando.size(), equalTo(5));

		//
	}

	@Test
	public void testaCadastroSemUsuario() throws BeException {
		CategoriaVo vazio = CategoriaBuilder.criaCategoriaDespesa(null);

		try {
			categoriaBe.save(vazio);
		} catch (ValidationBeException e) {
			assertEquals("Campo Usuário é obrigatório!", e.getMessage());
			return;
		}

		fail("Validaçãoo sem usuário falhou.");
	}

	@Test
	public void testaCadastroNomeVazio() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = usuarioBe.getByEmail("orlando.developermaster@gmail.com");

		CategoriaVo despesa = CategoriaBuilder.criaCategoriaDespesa(orlando);

		despesa.setNomeCategoria("   ");

		try {
			categoriaBe.save(despesa);
		} catch (ValidationBeException e) {
			assertEquals("Campo Descrição da Categoria é obrigatório!", e.getMessage());
			return;
		}

		fail("Validaçãoo Descrição falhou.");
	}

	@Test
	public void testaCadastroNomeNulo() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = usuarioBe.getByEmail("orlando.developermaster@gmail.com");

		CategoriaVo despesa = CategoriaBuilder.criaCategoriaDespesa(orlando);

		despesa.setNomeCategoria(null);

		try {
			categoriaBe.save(despesa);
		} catch (ValidationBeException e) {
			assertEquals("Campo Descrição da Categoria é obrigatório!", e.getMessage());
			return;
		}

		fail("Validação Descrição falhou.");
	}

	@Test
	public void testaCadastroCategoriaErrada() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = usuarioBe.getByEmail("orlando.developermaster@gmail.com");

		CategoriaVo despesa = CategoriaBuilder.criaCategoriaDespesa(orlando);
		despesa.setTipoCategoria("X");

		try {
			categoriaBe.save(despesa);
		} catch (ValidationBeException e) {
			assertEquals("Valor 'X' não permitido em Tipo de Categoria! Valores permitidos são D, R.", e.getMessage());
			return;
		}

		fail("Validação Tipo de Categoria falhou.");
	}

	public void criaDadosTeste() throws BeException {
		usuarioBe.save(UsuarioBuilder.criaUsuarioOrlando());
		usuarioBe.save(UsuarioBuilder.criaUsuarioAnaCarolina());
		usuarioBe.save(UsuarioBuilder.criaUsuarioGustavo());
	}

	@Before
	public void inicializar() throws DAOException {

		manager = DAOManager.getInstance();

		TestUtils.loadPropertiesIfNecessary();

		TestUtils.clearDB(manager);

		usuarioBe = new UsuarioBe(manager);
		categoriaBe = new CategoriaBe(manager);
	}

	@After
	public void finalizar() {
		manager.commit();
	}
}
