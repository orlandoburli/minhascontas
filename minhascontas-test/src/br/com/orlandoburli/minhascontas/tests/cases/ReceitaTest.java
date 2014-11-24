package br.com.orlandoburli.minhascontas.tests.cases;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.orlandoburli.framework.core.be.exceptions.BeException;
import br.com.orlandoburli.framework.core.be.exceptions.persistence.ListException;
import br.com.orlandoburli.framework.core.be.exceptions.validation.ValidationBeException;
import br.com.orlandoburli.framework.core.dao.DAOManager;
import br.com.orlandoburli.framework.core.dao.exceptions.DAOException;
import br.com.orlandoburli.minhascontas.model.be.acesso.UsuarioBe;
import br.com.orlandoburli.minhascontas.model.be.financeiro.CategoriaBe;
import br.com.orlandoburli.minhascontas.model.be.financeiro.ContaBe;
import br.com.orlandoburli.minhascontas.model.be.financeiro.ReceitaBe;
import br.com.orlandoburli.minhascontas.model.vo.acesso.UsuarioVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.CategoriaVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.ContaVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.ReceitaVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.StatusDespesaReceita;
import br.com.orlandoburli.minhascontas.tests.builders.CategoriaBuilder;
import br.com.orlandoburli.minhascontas.tests.builders.ContaBuilder;
import br.com.orlandoburli.minhascontas.tests.builders.ReceitaBuilder;
import br.com.orlandoburli.minhascontas.tests.builders.UsuarioBuilder;
import br.com.orlandoburli.minhascontas.tests.utils.TestUtils;

@RunWith(JUnit4.class)
public class ReceitaTest {

	private DAOManager manager;
	private UsuarioBe usuarioBe;
	private ContaBe contaBe;
	private ReceitaBe receitaBe;
	private CategoriaBe categoriaBe;

	@Test
	public void testaCadastraReceita() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = getUsuarioOrlando();
		CategoriaVo receitasFixas = getReceitasFixasSalarios(orlando);

		ReceitaVo salario = ReceitaBuilder.criaReceitaSalario("05/04/2014", getNumber(4087.323, 2), orlando, receitasFixas);

		receitaBe.save(salario);

		assertThat(salario.getDescricaoReceita(), equalTo("Salário"));

		assertThat(salario.getValor(), equalTo(getNumber(4087.32, 2)));

		assertThat(salario.getValorTotal(), equalTo(getNumber(4087.32, 2)));

		assertThat(salario.getDesconto(), equalTo(getNumber(0, 2)));

		assertThat(salario.getJurosMultas(), equalTo(getNumber(0, 2)));
	}

	@Test
	public void testaCadastroSemDescricao() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = getUsuarioOrlando();
		CategoriaVo receitasFixas = getReceitasFixasSalarios(orlando);

		ReceitaVo receita = ReceitaBuilder.criaReceita("   ", Calendar.getInstance(), getNumber(10, 2), orlando, receitasFixas);

		try {
			receitaBe.save(receita);
		} catch (ValidationBeException e) {
			assertThat(e.getMessage(), equalTo("Campo Descrição da Receita é obrigatório!"));
		}
	}

	@Test
	public void testaCadastroSemVencimento() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = getUsuarioOrlando();
		CategoriaVo receitasFixas = getReceitasFixasSalarios(orlando);

		ReceitaVo receita = ReceitaBuilder.criaReceita("Teste", null, getNumber(10, 2), orlando, receitasFixas);

		try {
			receitaBe.save(receita);
		} catch (ValidationBeException e) {
			assertThat(e.getMessage(), equalTo("Campo Data de Vencimento é obrigatório!"));
		}
	}

	@Test
	public void testaCadastroValorZero() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = getUsuarioOrlando();
		CategoriaVo receitasFixas = getReceitasFixasSalarios(orlando);

		ReceitaVo receita = ReceitaBuilder.criaReceita("Teste", Calendar.getInstance(), getNumber(0, 2), orlando, receitasFixas);

		try {
			receitaBe.save(receita);
		} catch (ValidationBeException e) {
			assertThat(e.getMessage(), equalTo("Campo Valor é obrigatório e deve ser maior que zero!"));
		}
	}

	@Test
	public void testaCadastroValorNegativo() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = getUsuarioOrlando();
		CategoriaVo receitasFixas = getReceitasFixasSalarios(orlando);

		ReceitaVo receita = ReceitaBuilder.criaReceita("Teste", Calendar.getInstance(), getNumber(-123, 2), orlando, receitasFixas);

		try {
			receitaBe.save(receita);
		} catch (ValidationBeException e) {
			assertThat(e.getMessage(), equalTo("Campo Valor é obrigatório e deve ser maior que zero!"));
		}
	}

	@Test
	public void testaCadastroValorNulo() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = getUsuarioOrlando();
		CategoriaVo receitasFixas = getReceitasFixasSalarios(orlando);

		ReceitaVo receita = ReceitaBuilder.criaReceita("Teste", Calendar.getInstance(), null, orlando, receitasFixas);

		try {
			receitaBe.save(receita);
		} catch (ValidationBeException e) {
			assertThat(e.getMessage(), equalTo("Campo Valor é obrigatório!"));
		}
	}

	@Test
	public void testaReceberReceita() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = getUsuarioOrlando();
		CategoriaVo receitasFixas = getReceitasFixasSalarios(orlando);

		ReceitaVo salario = ReceitaBuilder.criaReceitaSalario("05/04/2014", getNumber(4087.323, 2), orlando, receitasFixas);

		receitaBe.save(salario);

		assertThat(salario.getDescricaoReceita(), equalTo("Salário"));

		assertThat(salario.getValor(), equalTo(getNumber(4087.32, 2)));

		assertThat(salario.getValorTotal(), equalTo(getNumber(4087.32, 2)));

		assertThat(salario.getDesconto(), equalTo(getNumber(0, 2)));

		assertThat(salario.getJurosMultas(), equalTo(getNumber(0, 2)));

		// Recebimento
		receitaBe.receber(salario, Calendar.getInstance(), getBradesco(orlando), getNumber(4087.32, 2));

		salario = receitaBe.get(salario);

		assertThat(salario.getValor(), equalTo(getNumber(4087.32, 2)));

		assertThat(salario.getValorTotal(), equalTo(getNumber(4087.32, 2)));

		assertThat(salario.getDesconto(), equalTo(getNumber(0, 2)));

		assertThat(salario.getJurosMultas(), equalTo(getNumber(0, 2)));

		assertThat(salario.getValorRecebido(), equalTo(getNumber(4087.32, 2)));

		assertThat(salario.getStatusReceita(), equalTo(StatusDespesaReceita.PAGO));

		assertThat(salario.getMovimentacao().getValorMovimentacao(), equalTo(getNumber(4087.32, 2)));

		// Estorno
		receitaBe.estornar(salario, Calendar.getInstance());

		salario = receitaBe.get(salario);

		assertThat(salario.getValor(), equalTo(getNumber(4087.32, 2)));

		assertThat(salario.getValorTotal(), equalTo(getNumber(4087.32, 2)));

		assertThat(salario.getDesconto(), equalTo(getNumber(0, 2)));

		assertThat(salario.getJurosMultas(), equalTo(getNumber(0, 2)));

		assertThat(salario.getValorRecebido(), equalTo(getNumber(0, 2)));

		assertThat(salario.getStatusReceita(), equalTo(StatusDespesaReceita.ABERTO));

		assertThat(salario.getMovimentacao(), equalTo(null));
	}

	@Test
	public void testaReceberValorMaior() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = getUsuarioOrlando();
		CategoriaVo receitasFixas = getReceitasFixasSalarios(orlando);

		ReceitaVo salario = ReceitaBuilder.criaReceitaSalario("05/04/2014", getNumber(4087.323, 2), orlando, receitasFixas);

		receitaBe.save(salario);

		assertThat(salario.getDescricaoReceita(), equalTo("Salário"));

		assertThat(salario.getValor(), equalTo(getNumber(4087.32, 2)));

		assertThat(salario.getValorTotal(), equalTo(getNumber(4087.32, 2)));

		assertThat(salario.getDesconto(), equalTo(getNumber(0, 2)));

		assertThat(salario.getJurosMultas(), equalTo(getNumber(0, 2)));

		// Recebimento
		receitaBe.receber(salario, Calendar.getInstance(), getBradesco(orlando), getNumber(4382.33, 2));

		salario = receitaBe.get(salario);

		assertThat(salario.getValor(), equalTo(getNumber(4087.32, 2)));

		assertThat(salario.getValorTotal(), equalTo(getNumber(4382.33, 2)));

		assertThat(salario.getDesconto(), equalTo(getNumber(0, 2)));

		assertThat(salario.getJurosMultas(), equalTo(getNumber(295.01, 2)));

		assertThat(salario.getValorRecebido(), equalTo(getNumber(4382.33, 2)));

		assertThat(salario.getStatusReceita(), equalTo(StatusDespesaReceita.PAGO));

		assertThat(salario.getMovimentacao().getValorMovimentacao(), equalTo(getNumber(4382.33, 2)));

		// Estorno
		receitaBe.estornar(salario, Calendar.getInstance());

		salario = receitaBe.get(salario);

		assertThat(salario.getValor(), equalTo(getNumber(4087.32, 2)));

		assertThat(salario.getValorTotal(), equalTo(getNumber(4087.32, 2)));

		assertThat(salario.getDesconto(), equalTo(getNumber(0, 2)));

		assertThat(salario.getJurosMultas(), equalTo(getNumber(0, 2)));

		assertThat(salario.getValorRecebido(), equalTo(getNumber(0, 2)));

		assertThat(salario.getStatusReceita(), equalTo(StatusDespesaReceita.ABERTO));

		assertThat(salario.getMovimentacao(), equalTo(null));
	}

	@Test
	public void testaReceberValorMenor() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = getUsuarioOrlando();
		CategoriaVo receitasFixas = getReceitasFixasSalarios(orlando);

		ReceitaVo salario = ReceitaBuilder.criaReceitaSalario("05/04/2014", getNumber(4087.323, 2), orlando, receitasFixas);

		receitaBe.save(salario);

		assertThat(salario.getDescricaoReceita(), equalTo("Salário"));

		assertThat(salario.getValor(), equalTo(getNumber(4087.32, 2)));

		assertThat(salario.getValorTotal(), equalTo(getNumber(4087.32, 2)));

		assertThat(salario.getDesconto(), equalTo(getNumber(0, 2)));

		assertThat(salario.getJurosMultas(), equalTo(getNumber(0, 2)));

		// Recebimento
		receitaBe.receber(salario, Calendar.getInstance(), getBradesco(orlando), getNumber(3233.42, 2));

		salario = receitaBe.get(salario);

		assertThat(salario.getValor(), equalTo(getNumber(4087.32, 2)));

		assertThat(salario.getValorTotal(), equalTo(getNumber(3233.42, 2)));

		assertThat(salario.getDesconto(), equalTo(getNumber(853.9, 2)));

		assertThat(salario.getJurosMultas(), equalTo(getNumber(0, 2)));

		assertThat(salario.getValorRecebido(), equalTo(getNumber(3233.42, 2)));

		assertThat(salario.getStatusReceita(), equalTo(StatusDespesaReceita.PAGO));

		assertThat(salario.getMovimentacao().getValorMovimentacao(), equalTo(getNumber(3233.42, 2)));

		// Estorno
		receitaBe.estornar(salario, Calendar.getInstance());

		salario = receitaBe.get(salario);

		assertThat(salario.getValor(), equalTo(getNumber(4087.32, 2)));

		assertThat(salario.getValorTotal(), equalTo(getNumber(4087.32, 2)));

		assertThat(salario.getDesconto(), equalTo(getNumber(0, 2)));

		assertThat(salario.getJurosMultas(), equalTo(getNumber(0, 2)));

		assertThat(salario.getValorRecebido(), equalTo(getNumber(0, 2)));

		assertThat(salario.getStatusReceita(), equalTo(StatusDespesaReceita.ABERTO));

		assertThat(salario.getMovimentacao(), equalTo(null));
	}

	public ContaVo getBradesco(UsuarioVo usuario) throws ListException {
		return contaBe.getByNome("Bradesco", usuario);
	}

	private CategoriaVo getReceitasFixasSalarios(UsuarioVo usuario) throws ListException {
		return categoriaBe.getByNome(usuario, "Salários", categoriaBe.getByNome(usuario, "Fixas", categoriaBe.getByNome(usuario, "Receitas", null)));
	}

	public BigDecimal getNumber(double val, int scale) {
		return new BigDecimal(val).setScale(scale, BigDecimal.ROUND_HALF_EVEN);
	}

	public ContaVo getCarteira(UsuarioVo usuario) throws ListException {
		return contaBe.getByNome("Carteira", usuario);
	}

	public UsuarioVo getUsuarioOrlando() throws ListException {
		return usuarioBe.getByEmail("orlando.developermaster@gmail.com");
	}

	public void criaDadosTeste() throws BeException {
		// Cria usuarios
		usuarioBe.save(UsuarioBuilder.criaUsuarioOrlando());
		usuarioBe.save(UsuarioBuilder.criaUsuarioAnaCarolina());
		usuarioBe.save(UsuarioBuilder.criaUsuarioGustavo());

		UsuarioVo orlando = getUsuarioOrlando();

		// Cria contas
		contaBe.save(ContaBuilder.criaContaCarteira(getNumber(500, 2), orlando));
		contaBe.save(ContaBuilder.criaContaBradesco(BigDecimal.ZERO, orlando));
		contaBe.save(ContaBuilder.criaContaBB(BigDecimal.ZERO, orlando));

		// Cria categorias de despesa/receitas

		CategoriaVo receitas = CategoriaBuilder.criaCategoriaReceita(orlando);
		categoriaBe.save(receitas);

		CategoriaVo receitasFixas = CategoriaBuilder.criaCategoriaReceitaFixas(orlando, receitas);
		categoriaBe.save(receitasFixas);

		CategoriaVo receitasVariaveis = CategoriaBuilder.criaCategoriaReceitaVariaveis(orlando, receitas);
		categoriaBe.save(receitasVariaveis);

		categoriaBe.save(CategoriaBuilder.criaCategoriaReceitaSalarios(orlando, receitasFixas));

		CategoriaVo despesas = CategoriaBuilder.criaCategoriaDespesa(orlando);
		categoriaBe.save(despesas);

		CategoriaVo despesasFixas = CategoriaBuilder.criaCategoriaDespesaFixa(orlando, despesas);
		categoriaBe.save(despesasFixas);

		CategoriaVo despesasVariaveis = CategoriaBuilder.criaCategoriaDespesaVariaveis(orlando, despesas);
		categoriaBe.save(despesasVariaveis);
	}

	@Before
	public void inicializar() throws DAOException {

		manager = DAOManager.getInstance();

		TestUtils.loadPropertiesIfNecessary();

		TestUtils.clearDB(manager);

		usuarioBe = new UsuarioBe(manager);
		contaBe = new ContaBe(manager);
		categoriaBe = new CategoriaBe(manager);
		receitaBe = new ReceitaBe(manager);
	}

	@After
	public void finalizar() {
		manager.commit();
	}
}
