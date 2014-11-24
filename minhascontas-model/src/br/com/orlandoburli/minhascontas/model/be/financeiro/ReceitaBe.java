package br.com.orlandoburli.minhascontas.model.be.financeiro;

import java.math.BigDecimal;
import java.util.Calendar;

import br.com.orlandoburli.framework.core.be.BaseBe;
import br.com.orlandoburli.framework.core.be.exceptions.BeException;
import br.com.orlandoburli.framework.core.be.exceptions.validation.ValidationBeException;
import br.com.orlandoburli.framework.core.dao.DAOManager;
import br.com.orlandoburli.minhascontas.model.dao.financeiro.ReceitaDao;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.ContaVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.MovimentacaoVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.ReceitaVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.StatusDespesaReceita;

public class ReceitaBe extends BaseBe<ReceitaVo, ReceitaDao> {

	public ReceitaBe(DAOManager manager) {
		super(manager);
	}

	@Override
	public void doBeforeSave(ReceitaVo vo) throws BeException {
		super.doBeforeSave(vo);

		// Soma os juros e diminui as multas para obter o valor total.
		vo.setValorTotal(vo.getValor().add(vo.getJurosMultas()).subtract(vo.getDesconto()));
	}

	public void receber(ReceitaVo receita, Calendar data, ContaVo conta, BigDecimal valor) throws BeException {
		if (receita == null) {
			throw new ValidationBeException("A receita deve ser informada!", "");
		}

		if (data == null) {
			throw new ValidationBeException("A data de recebimento deve ser informada!", "dataRecebimento");
		}

		if (valor == null) {
			throw new ValidationBeException("O valor recebido deve ser informado", "valor");
		}

		if (!receita.getStatusReceita().equals(StatusDespesaReceita.ABERTO)) {
			throw new ValidationBeException("A receita não está em aberto!", "statusReceita");
		}

		MovimentacaoVo mov = new ContaBe(getManager()).addCredito(conta, data, valor);

		receita.setIdMovimentacao(mov.getIdMovimentacao());
		receita.setMovimentacao(mov);

		receita.setStatusReceita(StatusDespesaReceita.PAGO);

		receita.setValorRecebido(valor);

		BigDecimal diferenca = valor.subtract(receita.getValor());

		if (diferenca.compareTo(BigDecimal.ZERO) > 0) {
			// Se a diferenca for maior que zero, entao foi pago juros/multas.
			receita.setJurosMultas(diferenca);
		} else if (diferenca.compareTo(BigDecimal.ZERO) < 0) {
			// Se a diferenca for menor que zero, entao foi dado desconto.
			receita.setDesconto(diferenca.multiply(new BigDecimal(-1)));
		}

		save(receita);

	}

	public void estornar(ReceitaVo receita, Calendar dataEstorno) throws BeException {
		if (receita == null) {
			throw new ValidationBeException("Despesa não pode ser nula!", "");
		}

		if (!receita.getStatusReceita().equals(StatusDespesaReceita.PAGO)) {
			throw new ValidationBeException("Despesa não está paga!", "");
		}

		// Adiciona o credito de volta a conta
		MovimentacaoVo movimentacao = receita.getMovimentacao();
		if (movimentacao == null) {
			throw new ValidationBeException("Movimentação do pagamento está nula!", "");
		}
		new ContaBe(getManager()).addDebito(movimentacao.getConta(), dataEstorno, movimentacao.getValorMovimentacao());

		// Apaga a movimentacao, e seta o valor da despesa para zero.
		receita.setMovimentacao(null);
		receita.setIdMovimentacao(null);
		receita.setStatusReceita(StatusDespesaReceita.ABERTO);
		receita.setValorRecebido(null);
		receita.setDesconto(null);
		receita.setJurosMultas(null);
		receita.setDataRecebimento(null);

		save(receita);
	}
}
