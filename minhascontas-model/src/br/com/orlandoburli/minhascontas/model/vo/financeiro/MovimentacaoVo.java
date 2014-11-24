package br.com.orlandoburli.minhascontas.model.vo.financeiro;

import java.math.BigDecimal;
import java.util.Calendar;

import br.com.orlandoburli.framework.core.be.validation.annotations.transformation.Precision;
import br.com.orlandoburli.framework.core.be.validation.annotations.validators.Domain;
import br.com.orlandoburli.framework.core.be.validation.annotations.validators.NotEmpty;
import br.com.orlandoburli.framework.core.be.validation.annotations.validators.NotNull;
import br.com.orlandoburli.framework.core.be.validation.annotations.validators.NotZero;
import br.com.orlandoburli.framework.core.dao.annotations.Column;
import br.com.orlandoburli.framework.core.dao.annotations.DataType;
import br.com.orlandoburli.framework.core.dao.annotations.Join;
import br.com.orlandoburli.framework.core.dao.annotations.Table;
import br.com.orlandoburli.framework.core.vo.BaseVo;
import br.com.orlandoburli.framework.core.vo.annotations.Description;
import br.com.orlandoburli.minhascontas.model.utils.Dicionario.Conta;
import br.com.orlandoburli.minhascontas.model.utils.Dicionario.Movimentacao;
import br.com.orlandoburli.minhascontas.model.utils.Dicionario.Movimentacao.Colunas;

@Table(Movimentacao.TABELA_MOVIMENTACAO)
public class MovimentacaoVo extends BaseVo {
	
	private static final long serialVersionUID = 1L;

	@Column(name = Colunas.ID_MOVIMENTACAO, dataType = DataType.INT, isAutoIncrement = true, isKey = true)
	private Integer idMovimentacao;

	@Column(name = Colunas.ID_CONTA, dataType = DataType.INT, isNotNull = true)
	@NotNull
	@NotEmpty
	@Description("Conta")
	private Integer idConta;

	@Column(name = Colunas.TIPO_MOVIMENTACAO, dataType = DataType.CHAR, maxSize = 1, isNotNull = true)
	@NotNull
	@NotEmpty
	@Description("Tipo de Movimentação")
	@Domain(CreditoDebito.class)
	private String tipoMovimentacao;

	@Column(name = Colunas.DATA_MOVIMENTACAO, dataType = DataType.DATE, isNotNull = true)
	@NotNull
	@Description("Data da movimentação")
	private Calendar dataMovimentacao;

	@Column(name = Colunas.DATA_HORA_LANCAMENTO_MOV, dataType = DataType.DATETIME, isNotNull = true)
	@NotNull
	@Description("Data / Hora do lançamento da movimentação")
	private Calendar dataHoraMovimentacao;

	@Column(name = Colunas.VALOR_MOVIMENTACAO, dataType = DataType.NUMERIC, maxSize = 10, precision = 2, isNotNull = true)
	@NotNull
	@Precision(2)
	@Description("Valor da Movimentação")
	@NotZero
	private BigDecimal valorMovimentacao;

	@Column(name = Colunas.SALDO_ATUAL, dataType = DataType.NUMERIC, maxSize = 10, precision = 2, isNotNull = true)
	@NotNull
	@Precision(2)
	@Description("Saldo atual")
	private BigDecimal saldoAtual;

	@Join(columnsLocal = { Colunas.ID_CONTA }, columnsRemote = { Conta.Colunas.ID_CONTA })
	private ContaVo conta;

	public Integer getIdMovimentacao() {
		return idMovimentacao;
	}

	public void setIdMovimentacao(Integer idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}

	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public BigDecimal getValorMovimentacao() {
		return valorMovimentacao;
	}

	public void setValorMovimentacao(BigDecimal valorMovimentacao) {
		this.valorMovimentacao = valorMovimentacao;
	}

	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}

	public BigDecimal getSaldoAtual() {
		return saldoAtual;
	}

	public void setSaldoAtual(BigDecimal saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public ContaVo getConta() {
		return conta;
	}

	public void setConta(ContaVo conta) {
		this.conta = conta;
	}

	public Calendar getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(Calendar dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public Calendar getDataHoraMovimentacao() {
		return dataHoraMovimentacao;
	}

	public void setDataHoraMovimentacao(Calendar dataHoraMovimentacao) {
		this.dataHoraMovimentacao = dataHoraMovimentacao;
	}
}
