package br.com.orlandoburli.minhascontas.tests.builders;

import br.com.orlandoburli.minhascontas.model.vo.acesso.TipoAcessoUsuario;
import br.com.orlandoburli.minhascontas.model.vo.acesso.UsuarioVo;
import br.com.orlandoburli.minhascontas.model.vo.geral.SimNao;

public class UsuarioBuilder {

	public static UsuarioVo criaUsuario(String nomeUsuario, String sobreNomeUsuario, String senha, String tipoAutenticacao, String email, String flagConfirmado) {
		UsuarioVo usuario = new UsuarioVo();

		usuario.setNomeUsuario(nomeUsuario);
		usuario.setSobreNomeUsuario(sobreNomeUsuario);
		usuario.setSenha(senha);
		usuario.setTipoAutenticacao(tipoAutenticacao);
		usuario.setEmail(email);
		usuario.setFlagConfirmado(flagConfirmado);

		return usuario;
	}

	public static UsuarioVo criaUsuarioOrlando() {
		return criaUsuario("Orlando", "Burli Junior", "123123", TipoAcessoUsuario.EMAIL, "orlando.developermaster@gmail.com", "S");
	}

	public static UsuarioVo criaUsuarioAnaCarolina() {
		return criaUsuario("Ana Carolina", "Marques Moura", "abc123", TipoAcessoUsuario.EMAIL, "amoura.carolina@gmail.com", "S");
	}

	public static UsuarioVo criaUsuarioGustavo() {
		return criaUsuario("Gustavo", "Santos Arruda", "julinha", TipoAcessoUsuario.EMAIL, "gustavinho67@gmail.com", SimNao.SIM);
	}
}
