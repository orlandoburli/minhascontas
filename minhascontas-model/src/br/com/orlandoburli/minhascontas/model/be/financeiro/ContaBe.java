package br.com.orlandoburli.minhascontas.model.be.financeiro;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import br.com.orlandoburli.framework.core.be.BaseBe;
import br.com.orlandoburli.framework.core.be.exceptions.BeException;
import br.com.orlandoburli.framework.core.be.exceptions.persistence.ListException;
import br.com.orlandoburli.framework.core.dao.DAOManager;
import br.com.orlandoburli.minhascontas.model.dao.financeiro.ContaDao;
import br.com.orlandoburli.minhascontas.model.vo.acesso.UsuarioVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.ContaVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.CreditoDebito;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.MovimentacaoVo;

public class ContaBe extends BaseBe<ContaVo, ContaDao> {

	public ContaBe(DAOManager manager) {
		super(manager);
	}

	public List<ContaVo> getByUsuario(UsuarioVo usuario) throws ListException {
		if (usuario == null) {
			return null;
		}

		ContaVo filter = new ContaVo();
		filter.setIdUsuario(usuario.getIdUsuario());

		return getList(filter);
	}

	public ContaVo getByNome(String nomeConta, UsuarioVo usuario) throws ListException {
		if (usuario == null || nomeConta == null || nomeConta.trim().equals("")) {
			return null;
		}

		ContaVo filter = new ContaVo();
		filter.setIdUsuario(usuario.getIdUsuario());
		filter.setNomeConta(nomeConta.trim());

		List<ContaVo> list = getList(filter);

		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public synchronized MovimentacaoVo addCredito(ContaVo conta, Calendar dataMovimentacao, BigDecimal valor) throws BeException {
		return addMovimentacao(conta, dataMovimentacao, CreditoDebito.CREDITO, valor);
	}

	private MovimentacaoVo addMovimentacao(ContaVo conta, Calendar dataMovimentacao, String tipoMovimentacao, BigDecimal valor) throws BeException {
		MovimentacaoVo mov = new MovimentacaoVo();

		BigDecimal saldoAtual = conta.getSaldoAtual();

		if (tipoMovimentacao.equals(CreditoDebito.CREDITO)) {
			saldoAtual = saldoAtual.add(valor);
		} else if (tipoMovimentacao.equals(CreditoDebito.DEBITO)) {
			saldoAtual = saldoAtual.subtract(valor);
		} else if (tipoMovimentacao.equals(CreditoDebito.SALDO)) {
			saldoAtual = valor;
		}

		mov.setConta(conta);
		mov.setIdConta(conta.getIdConta());

		mov.setTipoMovimentacao(tipoMovimentacao);
		mov.setDataMovimentacao(dataMovimentacao);
		mov.setDataHoraMovimentacao(Calendar.getInstance());

		// salva o saldo anterior, mais o valor adicionado.
		mov.setSaldoAtual(saldoAtual);
		mov.setValorMovimentacao(valor);

		try {
			// Salva a movimentacao
			new MovimentacaoBe(getManager()).save(mov);

			conta.setSaldoAtual(saldoAtual);
			save(conta);
		} catch (BeException e) {
			// Qualquer erro, rollback geral.
			getManager().rollback();
			throw e;
		}
		
		return mov;
	}

	public MovimentacaoVo addDebito(ContaVo conta, Calendar dataMovimentacao, BigDecimal valor) throws BeException {
		return addMovimentacao(conta, dataMovimentacao, CreditoDebito.DEBITO, valor);
	}

	public MovimentacaoVo setSaldo(ContaVo conta, Calendar dataMovimentacao, BigDecimal valor) throws BeException {
		return addMovimentacao(conta, dataMovimentacao, CreditoDebito.SALDO, valor);
	}
}
