package br.com.orlandoburli.minhascontas.model.be.acesso;

import java.util.Calendar;
import java.util.UUID;

import br.com.orlandoburli.framework.core.be.BaseBe;
import br.com.orlandoburli.framework.core.be.exceptions.BeException;
import br.com.orlandoburli.framework.core.dao.DAOManager;
import br.com.orlandoburli.minhascontas.model.dao.acesso.TokenDao;
import br.com.orlandoburli.minhascontas.model.vo.acesso.TokenVo;
import br.com.orlandoburli.minhascontas.model.vo.acesso.UsuarioVo;
import br.com.orlandoburli.minhascontas.model.vo.geral.SimNao;

public class TokenBe extends BaseBe<TokenVo, TokenDao> {

	public TokenBe(DAOManager manager) {
		super(manager);
	}

	public TokenVo newToken(String email, String senha, boolean senhaMD5) throws BeException {

		UsuarioVo usuario = new UsuarioBe(getManager()).login(email, senha, senhaMD5);

		TokenVo token = new TokenVo();
		token.setChaveToken(UUID.randomUUID().toString());
		token.setDataHoraCriacao(Calendar.getInstance());
		token.setDataUltimoAcesso(Calendar.getInstance());
		token.setExpirado(SimNao.NAO);
		token.setUsuario(usuario);
		token.setIdUsuario(usuario.getIdUsuario());

		return save(token);
	}
}
