package br.com.orlandoburli.minhascontas.tests.builders;

import java.math.BigDecimal;
import java.util.Calendar;

import br.com.orlandoburli.minhascontas.model.vo.financeiro.ContaVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.CreditoDebito;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.MovimentacaoVo;

public final class MovimentacaoBuilder {

	public static MovimentacaoVo criaMovimentacao(ContaVo conta, String tipoMovimentacao, Calendar dataMovimentacao, Calendar dataHoraMovimentacao, BigDecimal valorMovimentacao, BigDecimal saldoAtual) {
		MovimentacaoVo movimentacao = new MovimentacaoVo();

		if (conta != null) {
			movimentacao.setIdConta(conta.getIdConta());
			movimentacao.setConta(conta);
		}
		movimentacao.setValorMovimentacao(valorMovimentacao);
		movimentacao.setDataMovimentacao(dataMovimentacao);
		movimentacao.setDataHoraMovimentacao(dataHoraMovimentacao);
		movimentacao.setSaldoAtual(saldoAtual);
		movimentacao.setTipoMovimentacao(tipoMovimentacao);

		return movimentacao;
	}

	public static MovimentacaoVo criaMovimentacao(ContaVo conta, String tipoMovimentacao, BigDecimal valorMovimentacao, BigDecimal saldoAtual) {
		Calendar dataMovimentacao = Calendar.getInstance();
		Calendar dataHoraMovimentacao = Calendar.getInstance();

		return criaMovimentacao(conta, tipoMovimentacao, dataMovimentacao, dataHoraMovimentacao, valorMovimentacao, saldoAtual);
	}

	public static MovimentacaoVo criaSaldo(ContaVo conta, BigDecimal valorMovimentacao) {
		return criaMovimentacao(conta, CreditoDebito.SALDO, valorMovimentacao, valorMovimentacao);
	}

	public static MovimentacaoVo criaCredito(ContaVo conta, BigDecimal valorMovimentacao, BigDecimal saldoAtual) {
		return criaMovimentacao(conta, CreditoDebito.CREDITO, valorMovimentacao, saldoAtual);
	}

	public static MovimentacaoVo criaDebito(ContaVo conta, BigDecimal valorMovimentacao, BigDecimal saldoAtual) {
		return criaMovimentacao(conta, CreditoDebito.DEBITO, valorMovimentacao, saldoAtual);
	}
}
