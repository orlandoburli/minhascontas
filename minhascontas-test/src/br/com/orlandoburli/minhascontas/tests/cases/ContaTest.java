package br.com.orlandoburli.minhascontas.tests.cases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;

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
import br.com.orlandoburli.minhascontas.model.vo.acesso.UsuarioVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.ContaVo;
import br.com.orlandoburli.minhascontas.tests.builders.ContaBuilder;
import br.com.orlandoburli.minhascontas.tests.builders.UsuarioBuilder;
import br.com.orlandoburli.minhascontas.tests.utils.TestUtils;

@RunWith(JUnit4.class)
public class ContaTest {
	private DAOManager manager;
	private UsuarioBe usuarioBe;
	private ContaBe contaBe;

	@Test
	public void testaCadastroConta() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = getUsuarioOrlando();

		ContaVo carteira = ContaBuilder.criaContaCarteira(BigDecimal.ZERO, orlando);

		contaBe.save(carteira);
	}

	@Test
	public void testaContaSemUsuario() throws BeException {
		ContaVo carteira = ContaBuilder.criaContaCarteira(null, null);

		try {
			contaBe.save(carteira);
		} catch (ValidationBeException e) {
			assertEquals("Campo Usuário é obrigatório!", e.getMessage());
			return;
		}

		fail("Falha na validação de usuário");
	}

	@Test
	public void testaContaSemNome() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = getUsuarioOrlando();

		ContaVo carteira = ContaBuilder.criaContaCarteira(BigDecimal.ZERO, orlando);

		carteira.setNomeConta("   ");

		try {
			contaBe.save(carteira);
		} catch (ValidationBeException e) {
			assertEquals("Campo Nome da Conta é obrigatório!", e.getMessage());
			return;
		}

		fail("Falha na validação de usuário");
	}

	@Test
	public void testaContaNomeNulo() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = getUsuarioOrlando();

		ContaVo carteira = ContaBuilder.criaContaCarteira(BigDecimal.ZERO, orlando);

		carteira.setNomeConta(null);

		try {
			contaBe.save(carteira);
		} catch (ValidationBeException e) {
			assertEquals("Campo Nome da Conta é obrigatório!", e.getMessage());
			return;
		}

		fail("Falha na validação de usuário");
	}

	@Test
	public void testaContaMultiUsuario() throws BeException {
		criaDadosTeste();

		List<UsuarioVo> usuarios = usuarioBe.getList(null);

		for (UsuarioVo usuario : usuarios) {
			contaBe.save(ContaBuilder.criaContaCarteira(BigDecimal.ZERO, usuario));
			contaBe.save(ContaBuilder.criaContaBradesco(BigDecimal.ZERO, usuario));
			contaBe.save(ContaBuilder.criaContaBB(BigDecimal.ZERO, usuario));
		}

		// Verifica o total de contas cadastradas
		List<ContaVo> contas = contaBe.getList(null);

		assertEquals(9, contas.size());

		// Verifica so o total das contas do orlando
		List<ContaVo> contasOrlando = contaBe.getByUsuario(getUsuarioOrlando());

		assertEquals(3, contasOrlando.size());
	}

	@Test
	public void testaSaldoConta() throws BeException {
		criaDadosTeste();

		UsuarioVo orlando = getUsuarioOrlando();

		contaBe.save(ContaBuilder.criaContaCarteira(BigDecimal.ZERO, orlando));
		contaBe.save(ContaBuilder.criaContaBradesco(BigDecimal.ZERO, orlando));
		contaBe.save(ContaBuilder.criaContaBB(BigDecimal.ZERO, orlando));

		ContaVo carteira = contaBe.getByNome("Carteira", orlando);

		assertEquals("Carteira", carteira.getNomeConta());

		assertEquals(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_EVEN), carteira.getSaldoAtual());

		carteira.setSaldoAtual(new BigDecimal(23.17));

		contaBe.save(carteira);

		ContaVo carteira2 = contaBe.getByNome("Carteira", orlando);

		assertEquals(new BigDecimal(23.17).setScale(2, BigDecimal.ROUND_HALF_EVEN), carteira2.getSaldoAtual());
	}

	private UsuarioVo getUsuarioOrlando() throws ListException {
		return usuarioBe.getByEmail("orlando.developermaster@gmail.com");
	}

	public void criaDadosTeste() throws BeException {
		usuarioBe.save(UsuarioBuilder.criaUsuarioOrlando());
		usuarioBe.save(UsuarioBuilder.criaUsuarioAnaCarolina());
		usuarioBe.save(UsuarioBuilder.criaUsuarioGustavo());
	}

	@Before
	public void inicializar() throws DAOException {

		manager = DAOManager.getInstance();

		TestUtils.clearDB(manager);

		usuarioBe = new UsuarioBe(manager);
		contaBe = new ContaBe(manager);
	}

	@After
	public void finalizar() {
		manager.commit();
	}
}
