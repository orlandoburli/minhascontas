package br.com.orlandoburli.minhascontas.services.acesso;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.orlandoburli.framework.core.be.exceptions.BeException;
import br.com.orlandoburli.framework.core.dao.DAOManager;
import br.com.orlandoburli.framework.core.log.Log;
import br.com.orlandoburli.framework.core.utils.Utils;
import br.com.orlandoburli.framework.core.web.retorno.RetornoAction;
import br.com.orlandoburli.minhascontas.model.be.acesso.TokenBe;
import br.com.orlandoburli.minhascontas.model.be.acesso.UsuarioBe;
import br.com.orlandoburli.minhascontas.model.vo.acesso.TipoAcessoUsuario;
import br.com.orlandoburli.minhascontas.model.vo.acesso.TokenVo;
import br.com.orlandoburli.minhascontas.model.vo.acesso.UsuarioVo;
import br.com.orlandoburli.minhascontas.model.vo.geral.SimNao;

@Path("/acesso")
public class AcessoService {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public String login(@FormParam("email") String email, @FormParam("senha") String senha, @FormParam("senhaMD5") boolean senhaMD5) {

		DAOManager manager = DAOManager.getInstance();

		try {
			TokenVo token = new TokenBe(manager).newToken(email, senha, senhaMD5);

			return Utils.voToJson(new RetornoAction(true, "Seja bem vindo, " + token.getUsuario().getNomeUsuario() + "!", token));
		} catch (BeException e) {
			Log.critical(e);
			return Utils.voToJson(new RetornoAction(false, e.getMessage()));
		} finally {
			manager.commit();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/criar")
	public String criarUsuario(@FormParam("nomeUsuario") String nomeUsuario, @FormParam("email") String email, @FormParam("sobreNomeUsuario") String sobreNomeUsuario, @FormParam("senha") String senha) {
		DAOManager manager = DAOManager.getInstance();

		UsuarioVo usuario = new UsuarioVo();

		try {
			usuario.setNomeUsuario(nomeUsuario);
			usuario.setEmail(email);
			usuario.setSobreNomeUsuario(sobreNomeUsuario);
			usuario.setSenha(senha);
			usuario.setTipoAutenticacao(TipoAcessoUsuario.EMAIL);
			usuario.setFlagConfirmado(SimNao.NAO);

			new UsuarioBe(manager).save(usuario);

			return Utils.voToJson(new RetornoAction(true, "Usuário cadastrado com sucesso!"));
		} catch (BeException e) {
			Log.error(e);
			return Utils.voToJson(new RetornoAction(false, e.getMessage(), e.getField()));
		} finally {
			manager.commit();
		}

	}
}