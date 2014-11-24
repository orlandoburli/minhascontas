package br.com.orlandoburli.minhascontas.tests.builders;

import java.math.BigDecimal;

import br.com.orlandoburli.minhascontas.model.vo.acesso.UsuarioVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.ContaVo;

public final class ContaBuilder {

	public static ContaVo criaConta(String nomeConta, BigDecimal saldoAtual, UsuarioVo usuario) {
		ContaVo conta = new ContaVo();
		conta.setNomeConta(nomeConta);
		conta.setSaldoAtual(saldoAtual);

		if (usuario != null) {
			conta.setIdUsuario(usuario.getIdUsuario());
		}

		return conta;
	}

	public static ContaVo criaContaCarteira(BigDecimal saldoInicial, UsuarioVo usuario) {
		return criaConta("Carteira", saldoInicial, usuario);
	}

	public static ContaVo criaContaBradesco(BigDecimal saldoInicial, UsuarioVo usuario) {
		return criaConta("Bradesco", saldoInicial, usuario);
	}

	public static ContaVo criaContaBB(BigDecimal saldoInicial, UsuarioVo usuario) {
		return criaConta("BB", saldoInicial, usuario);
	}
}
