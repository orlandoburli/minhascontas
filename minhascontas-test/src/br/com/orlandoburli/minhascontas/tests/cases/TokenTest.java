package br.com.orlandoburli.minhascontas.tests.cases;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.orlandoburli.framework.core.be.exceptions.BeException;
import br.com.orlandoburli.framework.core.dao.DAOManager;
import br.com.orlandoburli.framework.core.dao.exceptions.DAOException;
import br.com.orlandoburli.minhascontas.model.be.acesso.TokenBe;
import br.com.orlandoburli.minhascontas.model.be.acesso.UsuarioBe;
import br.com.orlandoburli.minhascontas.model.vo.acesso.TokenVo;
import br.com.orlandoburli.minhascontas.model.vo.acesso.UsuarioVo;
import br.com.orlandoburli.minhascontas.model.vo.geral.SimNao;
import br.com.orlandoburli.minhascontas.tests.builders.UsuarioBuilder;
import br.com.orlandoburli.minhascontas.tests.utils.TestUtils;

@RunWith(JUnit4.class)
public final class TokenTest {

	private DAOManager manager;
	private UsuarioBe usuarioBe;
	private UsuarioVo orlando;
	private TokenBe tokenBe;

	@Test
	public void testaToken() throws BeException {
		TokenVo token = tokenBe.newToken(orlando.getEmail(), orlando.getSenha(), false);

		assertEquals(token.getExpirado(), SimNao.NAO);
	}

	@Before
	public void inicializar() throws DAOException, BeException {

		manager = DAOManager.getInstance();

		TestUtils.loadPropertiesIfNecessary();

		TestUtils.clearDB(manager);

		usuarioBe = new UsuarioBe(manager);

		orlando = UsuarioBuilder.criaUsuarioOrlando();
		usuarioBe.save(orlando);

		tokenBe = new TokenBe(manager);
	}

	@After
	public void finalizar() {
		manager.commit();
	}
}
