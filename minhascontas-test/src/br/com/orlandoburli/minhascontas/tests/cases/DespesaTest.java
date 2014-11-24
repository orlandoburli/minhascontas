package br.com.orlandoburli.minhascontas.tests.cases;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

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
import br.com.orlandoburli.framework.core.log.Log;
import br.com.orlandoburli.minhascontas.model.be.acesso.UsuarioBe;
import br.com.orlandoburli.minhascontas.model.be.financeiro.CategoriaBe;
import br.com.orlandoburli.minhascontas.model.be.financeiro.ContaBe;
import br.com.orlandoburli.minhascontas.model.be.financeiro.DespesaBe;
import br.com.orlandoburli.minhascontas.model.vo.acesso.UsuarioVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.CategoriaVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.ContaVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.DespesaVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.StatusDespesaReceita;
import br.com.orlandoburli.minhascontas.tests.builders.CategoriaBuilder;
import br.com.orlandoburli.minhascontas.tests.builders.ContaBuilder;
import br.com.orlandoburli.minhascontas.tests.builders.DespesaBuilder;
import br.com.orlandoburli.minhascontas.tests.builders.UsuarioBuilder;
import br.com.orlandoburli.minhascontas.tests.utils.TestUtils;

@RunWith(JUnit4.class)
public class DespesaTest {

	private DAOManager manager;
	private UsuarioBe usuarioBe;
	private ContaBe contaBe;
	private DespesaBe despesaBe;
	private CategoriaBe categoriaBe;

	@Test
	public void testaCadastraDespesa() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = getUsuarioOrlando();

		CategoriaVo despesasVariaveis = getCategoriaDespesasVariaveis(orlando);

		DespesaVo contaLuz = DespesaBuilder.criaContaLuz(orlando, "10/03/2014", new BigDecimal(340.33), despesasVariaveis);

		despesaBe.save(contaLuz);

		assertThat(contaLuz.getValor(), equalTo(getNumber(340.33, 2)));

		assertThat(contaLuz.getJurosMulta(), equalTo(getNumber(0, 2)));

		assertThat(contaLuz.getDesconto(), equalTo(getNumber(0, 2)));

		assertThat(contaLuz.getValorTotal(), equalTo(getNumber(340.33, 2)));

		assertThat(contaLuz.getValorPago(), equalTo(getNumber(0, 2)));
	}

	@Test(expected = ValidationBeException.class)
	public void testaDespesaVencimentoErrado() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = getUsuarioOrlando();

		CategoriaVo despesasVariaveis = getCategoriaDespesasVariaveis(orlando);

		DespesaVo contaLuz = DespesaBuilder.criaContaLuz(orlando, null, new BigDecimal(340.33), despesasVariaveis);

		despesaBe.save(contaLuz);

		fail("Validação da data de vencimento falhou");
	}

	@Test
	public void testaPagarDespesaComJuros() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = getUsuarioOrlando();

		CategoriaVo despesasVariaveis = getCategoriaDespesasVariaveis(orlando);

		ContaVo carteira = getCarteira(orlando);

		DespesaVo contaLuz = DespesaBuilder.criaContaLuz(orlando, "10/04/2014", new BigDecimal(340.33), despesasVariaveis);

		despesaBe.save(contaLuz);

		despesaBe.pagar(contaLuz, carteira, getNumber(345.82, 2), Calendar.getInstance());

		assertThat(contaLuz.getValor(), equalTo(getNumber(340.33, 2)));

		assertThat(contaLuz.getJurosMulta(), equalTo(getNumber(5.49, 2)));

		assertThat(contaLuz.getDesconto(), equalTo(getNumber(0, 2)));

		assertThat(contaLuz.getValorPago(), equalTo(getNumber(345.82, 2)));

		assertThat(contaLuz.getStatusDespesa(), equalTo(StatusDespesaReceita.PAGO));
	}

	@Test
	public void testaPagarValorZero() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = getUsuarioOrlando();

		CategoriaVo despesasVariaveis = getCategoriaDespesasVariaveis(orlando);

		ContaVo carteira = getCarteira(orlando);

		DespesaVo contaLuz = DespesaBuilder.criaContaLuz(orlando, "10/04/2014", new BigDecimal(340.33), despesasVariaveis);

		despesaBe.save(contaLuz);

		try {
			despesaBe.pagar(contaLuz, carteira, getNumber(0, 2), Calendar.getInstance());
		} catch (ValidationBeException e) {
			assertThat(e.getMessage(), equalTo("Valor a pagar é obrigatório e deve ser maior que zero!"));
		}
	}

	@Test
	public void testaPagarDespesaComDesconto() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = getUsuarioOrlando();

		CategoriaVo despesasVariaveis = getCategoriaDespesasVariaveis(orlando);

		ContaVo carteira = getCarteira(orlando);

		DespesaVo contaLuz = DespesaBuilder.criaContaLuz(orlando, "10/04/2014", new BigDecimal(340.33), despesasVariaveis);

		despesaBe.save(contaLuz);

		despesaBe.pagar(contaLuz, carteira, getNumber(330.22, 2), Calendar.getInstance());

		assertThat(contaLuz.getValor(), equalTo(getNumber(340.33, 2)));

		assertThat(contaLuz.getJurosMulta(), equalTo(getNumber(0, 2)));

		assertThat(contaLuz.getDesconto(), equalTo(getNumber(10.11, 2)));

		assertThat(contaLuz.getValorPago(), equalTo(getNumber(330.22, 2)));

		assertThat(contaLuz.getStatusDespesa(), equalTo(StatusDespesaReceita.PAGO));

		// Verifica o saldo da carteira
		carteira = getCarteira(orlando);

		// O saldo na carteira deve ser de 169,78, pois o saldo inicial era de
		// 500.
		assertThat(carteira.getSaldoAtual(), equalTo(getNumber(169.78, 2)));
	}

	@Test
	public void testaEstorno() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = getUsuarioOrlando();

		CategoriaVo despesasVariaveis = getCategoriaDespesasVariaveis(orlando);

		ContaVo carteira = getCarteira(orlando);

		DespesaVo contaLuz = DespesaBuilder.criaContaLuz(orlando, "10/04/2014", new BigDecimal(340.33), despesasVariaveis);

		Log.info("------------- Inserindo a conta de Luz ------------");
		despesaBe.save(contaLuz);

		Log.info("------------- Pagando a conta de Luz ------------");
		despesaBe.pagar(contaLuz, carteira, getNumber(330.22, 2), Calendar.getInstance());

		Log.info("------------- Buscando a conta de Luz ------------");
		contaLuz = despesaBe.get(contaLuz);

		Log.info("------------- Estornando o pagamento da conta de Luz ------------");
		despesaBe.estornar(contaLuz, Calendar.getInstance());

		assertThat(contaLuz.getValor(), equalTo(getNumber(340.33, 2)));

		assertThat(contaLuz.getJurosMulta(), equalTo(getNumber(0, 2)));

		assertThat(contaLuz.getDesconto(), equalTo(getNumber(0, 2)));

		assertThat(contaLuz.getValorTotal(), equalTo(getNumber(340.33, 2)));

		assertThat(contaLuz.getValorPago(), equalTo(getNumber(0, 2)));

		// Verifica o saldo da carteira
		carteira = getCarteira(orlando);

		// O saldo na carteira deve ser de 500, mesmo saldo inicial.
		assertThat(carteira.getSaldoAtual(), equalTo(getNumber(500, 2)));
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

	public CategoriaVo getCategoriaDespesasVariaveis(UsuarioVo usuario) throws ListException {
		CategoriaVo despesas = categoriaBe.getByNome(usuario, "Despesas", null);

		CategoriaVo despesasVariaveis = categoriaBe.getByNome(usuario, "Variáveis", despesas);

		return despesasVariaveis;
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
		despesaBe = new DespesaBe(manager);
		categoriaBe = new CategoriaBe(manager);
	}

	@After
	public void finalizar() {
		manager.commit();
	}
}
