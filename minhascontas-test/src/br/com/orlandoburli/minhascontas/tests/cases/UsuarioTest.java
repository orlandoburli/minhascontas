package br.com.orlandoburli.minhascontas.tests.cases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.orlandoburli.framework.core.be.exceptions.BeException;
import br.com.orlandoburli.framework.core.dao.DAOManager;
import br.com.orlandoburli.framework.core.dao.exceptions.DAOException;
import br.com.orlandoburli.minhascontas.model.be.acesso.UsuarioBe;
import br.com.orlandoburli.minhascontas.model.vo.acesso.UsuarioVo;
import br.com.orlandoburli.minhascontas.tests.builders.UsuarioBuilder;
import br.com.orlandoburli.minhascontas.tests.utils.TestUtils;

@RunWith(JUnit4.class)
public class UsuarioTest {

	private DAOManager manager;
	private UsuarioBe usuarioBe;

	@Test
	public void testaCadastrarUsuario() throws BeException {
		UsuarioVo usuario = UsuarioBuilder.criaUsuarioOrlando();

		usuarioBe.save(usuario);

		UsuarioVo usuario2 = usuarioBe.getByEmail("orlando.developermaster@gmail.com");

		assertNotNull(usuario2);

		assertEquals("Orlando", usuario2.getNomeUsuario());

		assertEquals("Burli Junior", usuario2.getSobreNomeUsuario());

		assertEquals("orlando.developermaster@gmail.com", usuario2.getEmail());

	}

	@Test
	public void testaUsuarioNomeVazio() {
		UsuarioVo usuario = UsuarioBuilder.criaUsuarioOrlando();

		usuario.setNomeUsuario("");

		try {
			usuarioBe.save(usuario);
		} catch (BeException e) {
			assertEquals("Campo Nome do Usuário é obrigatório!", e.getMessage());
			return;
		}

		fail("Validação de nome do usuário falhou");
	}

	@Test
	public void testaExcluirUsuario() throws BeException {
		UsuarioVo usuario = UsuarioBuilder.criaUsuarioOrlando();

		usuarioBe.remove(usuario);

		UsuarioVo usuario2 = usuarioBe.get(usuario);

		if (usuario2 != null) {
			fail("Usuário não foi removido!");
		}
	}

	@Test
	public void testaUsuarioSobreNomeVazio() {
		UsuarioVo usuario = UsuarioBuilder.criaUsuarioOrlando();

		usuario.setSobreNomeUsuario("");

		try {
			usuarioBe.save(usuario);
		} catch (BeException e) {
			assertEquals("Campo Sobrenome é obrigatório!", e.getMessage());
			return;
		}

		fail("Validação de sobrenome do usuário falhou");
	}

	@Test
	public void testaUsuarioEmailVazio() {
		UsuarioVo usuario = UsuarioBuilder.criaUsuarioOrlando();

		usuario.setEmail("");

		try {
			usuarioBe.save(usuario);
		} catch (BeException e) {
			assertEquals("Campo Email é obrigatório!", e.getMessage());
			return;
		}

		fail("Validação de email do usuário falhou");
	}

	@Test
	public void testaUsuarioEmailNulo() {
		UsuarioVo usuario = UsuarioBuilder.criaUsuarioOrlando();

		usuario.setEmail(null);

		try {
			usuarioBe.save(usuario);
		} catch (BeException e) {
			assertEquals("Campo Email é obrigatório!", e.getMessage());
			return;
		}

		fail("Validação de email do usuário falhou");
	}

	@Test
	public void testaUsuarioEmailInvalido() {
		UsuarioVo usuario = UsuarioBuilder.criaUsuarioOrlando();

		usuario.setEmail("sss@sxsss");

		try {
			usuarioBe.save(usuario);
		} catch (BeException e) {
			assertEquals("Campo Email não é um email válido!", e.getMessage());
			return;
		}

		fail("Validação de email do usuário falhou");
	}

	@Test
	public void testaUsuarioTipoAutenticacaoInvalido() {
		UsuarioVo usuario = UsuarioBuilder.criaUsuarioOrlando();

		usuario.setTipoAutenticacao("X");

		try {
			usuarioBe.save(usuario);
		} catch (BeException e) {
			assertEquals("Valor 'X' não permitido em Tipo de Autenticação! Valores permitidos são E, F.", e.getMessage());
			return;
		}

		fail("Validaçãoo de tipo de autenticacao do usuário falhou");
	}

	@Test
	public void testaUsuarioTipoAutenticacaoVazio() {
		UsuarioVo usuario = UsuarioBuilder.criaUsuarioOrlando();

		usuario.setTipoAutenticacao("");

		try {
			usuarioBe.save(usuario);
		} catch (BeException e) {
			assertEquals("Campo Tipo de Autenticação é obrigatório!", e.getMessage());
			return;
		}

		fail("Validação de tipo de autenticacao do usuário falhou");
	}

	@Test
	public void testaUsuarioTipoAutenticacaoNulo() {
		UsuarioVo usuario = UsuarioBuilder.criaUsuarioOrlando();

		usuario.setTipoAutenticacao(null);

		try {
			usuarioBe.save(usuario);
		} catch (BeException e) {
			assertEquals("Campo Tipo de Autenticação é obrigatório!", e.getMessage());
			return;
		}

		fail("Validação de tipo de autenticacao do usuário falhou");
	}

	@Test
	public void testaUsuarioFlagConfirmado() {
		UsuarioVo usuario = UsuarioBuilder.criaUsuarioOrlando();

		usuario.setFlagConfirmado("X");

		try {
			usuarioBe.save(usuario);
		} catch (BeException e) {
			assertEquals("Valor 'X' não permitido em Flag do Email Confirmado! Valores permitidos são S, N.", e.getMessage());
			return;
		}

		fail("Validação de flag de confirmação do usuário falhou");
	}

	@Test
	public void testaListaUsuarios() throws BeException {
		usuarioBe.save(UsuarioBuilder.criaUsuarioOrlando());
		usuarioBe.save(UsuarioBuilder.criaUsuarioAnaCarolina());
		usuarioBe.save(UsuarioBuilder.criaUsuarioGustavo());

		List<UsuarioVo> list = usuarioBe.getList(null);

		assertEquals(3, list.size());
	}

	@Test(expected = BeException.class)
	public void testaEmailDuplicado() throws BeException {
		usuarioBe.save(UsuarioBuilder.criaUsuarioOrlando());
		usuarioBe.save(UsuarioBuilder.criaUsuarioOrlando());
	}

	@Before
	public void inicializar() throws DAOException {

		manager = DAOManager.getInstance();

		TestUtils.loadPropertiesIfNecessary();

		TestUtils.clearDB(manager);

		usuarioBe = new UsuarioBe(manager);
	}

	@After
	public void finalizar() {
		manager.commit();
	}
}
