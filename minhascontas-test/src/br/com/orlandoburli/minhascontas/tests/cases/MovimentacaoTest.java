package br.com.orlandoburli.minhascontas.tests.cases;

import static org.junit.Assert.assertEquals;
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
import br.com.orlandoburli.minhascontas.model.be.acesso.UsuarioBe;
import br.com.orlandoburli.minhascontas.model.be.financeiro.ContaBe;
import br.com.orlandoburli.minhascontas.model.be.financeiro.MovimentacaoBe;
import br.com.orlandoburli.minhascontas.model.vo.acesso.UsuarioVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.ContaVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.CreditoDebito;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.MovimentacaoVo;
import br.com.orlandoburli.minhascontas.tests.builders.ContaBuilder;
import br.com.orlandoburli.minhascontas.tests.builders.MovimentacaoBuilder;
import br.com.orlandoburli.minhascontas.tests.builders.UsuarioBuilder;
import br.com.orlandoburli.minhascontas.tests.utils.TestUtils;

@RunWith(JUnit4.class)
public class MovimentacaoTest {

	private DAOManager manager;
	private UsuarioBe usuarioBe;
	private ContaBe contaBe;
	private MovimentacaoBe movimentacaoBe;

	@Test
	public void testaCadastraMovimentacao() throws BeException {
		criaDadosTeste();

		ContaVo carteira = getCarteira(getUsuarioOrlando());

		movimentacaoBe.save(MovimentacaoBuilder.criaSaldo(carteira, new BigDecimal(103.02)));
		movimentacaoBe.save(MovimentacaoBuilder.criaCredito(carteira, new BigDecimal(102.35), new BigDecimal(105.37)));
		movimentacaoBe.save(MovimentacaoBuilder.criaDebito(carteira, new BigDecimal(50), new BigDecimal(55.372)));
	}

	@Test
	public void testaSaldoInicialConta() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = getUsuarioOrlando();

		ContaVo carteira = getCarteira(orlando);

		// O saldo inicial deve ser zero.
		assertEquals(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_EVEN), carteira.getSaldoAtual());
	}

	@Test
	public void testaMovimentacao() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = getUsuarioOrlando();

		ContaVo carteira = getCarteira(orlando);

		// O saldo inicial deve ser zero.
		assertEquals(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_EVEN), carteira.getSaldoAtual());

		// Adiciona 102,03 na conta.
		contaBe.addCredito(carteira, Calendar.getInstance(), new BigDecimal(102.03));

		// Busca de novo do banco de daddos
		carteira = getCarteira(orlando);
		// Debito de 30.76

		contaBe.addDebito(carteira, Calendar.getInstance(), new BigDecimal(30.76));

		// O saldo deve ser de 71,27
		assertEquals(new BigDecimal(71.27).setScale(2, BigDecimal.ROUND_HALF_EVEN), carteira.getSaldoAtual());

		// Busca de novo do banco de daddos
		carteira = getCarteira(orlando);

		// Adiciona 304.33
		contaBe.addCredito(carteira, Calendar.getInstance(), new BigDecimal(304.33));

		// Busca de novo do banco de daddos
		carteira = getCarteira(orlando);

		// Adiciona 1253.07
		contaBe.addCredito(carteira, Calendar.getInstance(), new BigDecimal(1253.07));

		// Busca de novo do banco de daddos
		carteira = getCarteira(orlando);

		// O saldo deve ser de 1628,67
		assertEquals(new BigDecimal(1628.67).setScale(2, BigDecimal.ROUND_HALF_EVEN), carteira.getSaldoAtual());

	}

	@Test
	public void testaMovimentacaoTipoErrado() throws BeException {
		criaDadosTeste();

		MovimentacaoVo mov = MovimentacaoBuilder.criaMovimentacao(getCarteira(getUsuarioOrlando()), "X", new BigDecimal(100), new BigDecimal(100));

		try {
			movimentacaoBe.save(mov);
		} catch (ValidationBeException e) {
			assertEquals("Valor 'X' não permitido em Tipo de Movimentação! Valores permitidos são C, D, S.", e.getMessage());
		}
	}

	@Test
	public void testaMovimentacaoValorNulo() throws BeException {
		criaDadosTeste();

		MovimentacaoVo mov = MovimentacaoBuilder.criaMovimentacao(getCarteira(getUsuarioOrlando()), CreditoDebito.CREDITO, null, new BigDecimal(100));

		try {
			movimentacaoBe.save(mov);
		} catch (ValidationBeException e) {
			assertEquals("Campo Valor da Movimentação é obrigatório!", e.getMessage());
			return;
		}

		fail("Falhou validação de não nulo");
	}

	@Test
	public void testaMovimentacaoValorZero() throws BeException {
		criaDadosTeste();

		MovimentacaoVo mov = MovimentacaoBuilder.criaMovimentacao(getCarteira(getUsuarioOrlando()), CreditoDebito.CREDITO, BigDecimal.ZERO, new BigDecimal(100));

		try {
			movimentacaoBe.save(mov);
		} catch (ValidationBeException e) {
			assertEquals("Campo Valor da Movimentação é obrigatório e deve ser maior que zero!", e.getMessage());
			return;
		}

		fail("Falhou validação de valor zero");
	}

	private ContaVo getCarteira(UsuarioVo usuario) throws ListException {
		return contaBe.getByNome("Carteira", usuario);
	}

	private UsuarioVo getUsuarioOrlando() throws ListException {
		return usuarioBe.getByEmail("orlando.developermaster@gmail.com");
	}

	public void criaDadosTeste() throws BeException {
		usuarioBe.save(UsuarioBuilder.criaUsuarioOrlando());
		usuarioBe.save(UsuarioBuilder.criaUsuarioAnaCarolina());
		usuarioBe.save(UsuarioBuilder.criaUsuarioGustavo());

		UsuarioVo orlando = getUsuarioOrlando();

		contaBe.save(ContaBuilder.criaContaCarteira(BigDecimal.ZERO, orlando));
		contaBe.save(ContaBuilder.criaContaBradesco(BigDecimal.ZERO, orlando));
		contaBe.save(ContaBuilder.criaContaBB(BigDecimal.ZERO, orlando));
	}

	@Before
	public void inicializar() throws DAOException {

		manager = DAOManager.getInstance();

		TestUtils.clearDB(manager);

		usuarioBe = new UsuarioBe(manager);
		contaBe = new ContaBe(manager);
		movimentacaoBe = new MovimentacaoBe(manager);
	}

	@After
	public void finalizar() {
		manager.commit();
	}
}
