package br.com.orlandoburli.minhascontas.model.be.financeiro;

import java.math.BigDecimal;
import java.util.Calendar;

import br.com.orlandoburli.framework.core.be.BaseBe;
import br.com.orlandoburli.framework.core.be.exceptions.BeException;
import br.com.orlandoburli.framework.core.be.exceptions.validation.ValidationBeException;
import br.com.orlandoburli.framework.core.dao.DAOManager;
import br.com.orlandoburli.minhascontas.model.dao.financeiro.DespesaDao;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.ContaVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.DespesaVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.MovimentacaoVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.StatusDespesaReceita;

public class DespesaBe extends BaseBe<DespesaVo, DespesaDao> {

	public DespesaBe(DAOManager manager) {
		super(manager);
	}

	@Override
	public void doBeforeSave(DespesaVo vo) throws BeException {
		super.doBeforeSave(vo);

		// Soma os juros e diminui as multas para obter o valor total.
		vo.setValorTotal(vo.getValor().add(vo.getJurosMulta()).subtract(vo.getDesconto()));
	}

	/**
	 * Paga uma despesa, usando uma determinada conta.
	 * 
	 * @param despesa
	 *            Despesa a ser paga
	 * @param conta
	 *            Conta daonde sera extraido o recurso
	 * @param valor
	 *            Valor a ser pago
	 * @param data
	 * @throws BeException
	 */
	public void pagar(DespesaVo despesa, ContaVo conta, BigDecimal valor, Calendar data) throws BeException {
		if (despesa == null) {
			throw new ValidationBeException("Despesa não pode ser nula!", "");
		}

		if (conta == null) {
			throw new ValidationBeException("Conta não pode ser nula!", "");
		}

		if (valor == null) {
			throw new ValidationBeException("O valor não pode ser nulo!", "valor");
		}

		if (valor.compareTo(BigDecimal.ZERO) <= 0) {
			throw new ValidationBeException("Valor a pagar é obrigatório e deve ser maior que zero!", "valor");
		}

		if (despesa.getStatusDespesa().equals(StatusDespesaReceita.PAGO)) {
			throw new ValidationBeException("Despesa já está paga!", "statusDespesa");
		}

		BigDecimal diferenca = despesa.getValor().subtract(valor);

		if (diferenca.compareTo(BigDecimal.ZERO) > 0) {
			// Se a diferenca for maior que zero, entao esta com desconto.
			despesa.setDesconto(diferenca);
		} else if (diferenca.compareTo(BigDecimal.ZERO) < 0) {
			// Se a diferenca for menor que zero, entao esta com juros/multas.
			despesa.setJurosMulta(diferenca.multiply(new BigDecimal(-1)));
		}

		despesa.setDataPagamento(data);

		// Gera a movimentacao
		MovimentacaoVo mov = new ContaBe(getManager()).addDebito(conta, data, valor);

		despesa.setIdMovimentacao(mov.getIdMovimentacao());
		despesa.setMovimentacao(mov);

		// Seta o valor, marca como pago e salva.
		despesa.setValorPago(valor);

		despesa.setStatusDespesa(StatusDespesaReceita.PAGO);

		save(despesa);
	}

	/**
	 * Estorna o pagamento de uma despesa.
	 * 
	 * @param despesa
	 *            Despesa a ser estornada.
	 * @param dataEstorno
	 *            Data do estorno.
	 * @throws BeException
	 */
	public void estornar(DespesaVo despesa, Calendar dataEstorno) throws BeException {
		if (despesa == null) {
			throw new ValidationBeException("Despesa não pode ser nula!", "");
		}

		if (!despesa.getStatusDespesa().equals(StatusDespesaReceita.PAGO)) {
			throw new ValidationBeException("Despesa não está paga!", "");
		}

		// Adiciona o credito de volta a conta
		MovimentacaoVo movimentacao = despesa.getMovimentacao();
		if (movimentacao == null) {
			throw new ValidationBeException("Movimentação do pagamento está nula!", "");
		}
		new ContaBe(getManager()).addCredito(movimentacao.getConta(), dataEstorno, movimentacao.getValorMovimentacao());

		// Apaga a movimentacao, e seta o valor da despesa para zero.
		despesa.setMovimentacao(null);
		despesa.setIdMovimentacao(null);
		despesa.setStatusDespesa(StatusDespesaReceita.ABERTO);
		despesa.setValorPago(null);
		despesa.setDesconto(null);
		despesa.setJurosMulta(null);
		despesa.setDataPagamento(null);
		
		save(despesa);
	}
}
