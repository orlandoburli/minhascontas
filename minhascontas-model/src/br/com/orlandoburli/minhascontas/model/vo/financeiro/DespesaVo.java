package br.com.orlandoburli.minhascontas.model.vo.financeiro;

import java.math.BigDecimal;
import java.util.Calendar;

import br.com.orlandoburli.framework.core.be.validation.annotations.transformation.FullTrim;
import br.com.orlandoburli.framework.core.be.validation.annotations.transformation.Precision;
import br.com.orlandoburli.framework.core.be.validation.annotations.transformation.ZeroIfNull;
import br.com.orlandoburli.framework.core.be.validation.annotations.validators.Domain;
import br.com.orlandoburli.framework.core.be.validation.annotations.validators.MaxSize;
import br.com.orlandoburli.framework.core.be.validation.annotations.validators.MinSize;
import br.com.orlandoburli.framework.core.be.validation.annotations.validators.NotEmpty;
import br.com.orlandoburli.framework.core.be.validation.annotations.validators.NotNegative;
import br.com.orlandoburli.framework.core.be.validation.annotations.validators.NotNull;
import br.com.orlandoburli.framework.core.be.validation.annotations.validators.NotZero;
import br.com.orlandoburli.framework.core.dao.annotations.Column;
import br.com.orlandoburli.framework.core.dao.annotations.DataType;
import br.com.orlandoburli.framework.core.dao.annotations.Join;
import br.com.orlandoburli.framework.core.dao.annotations.Table;
import br.com.orlandoburli.framework.core.vo.BaseVo;
import br.com.orlandoburli.framework.core.vo.annotations.Description;
import br.com.orlandoburli.minhascontas.model.utils.Dicionario.Categoria;
import br.com.orlandoburli.minhascontas.model.utils.Dicionario.Movimentacao;
import br.com.orlandoburli.minhascontas.model.utils.Dicionario.Usuario;
import br.com.orlandoburli.minhascontas.model.vo.acesso.UsuarioVo;
import br.com.orlandoburli.minhascontas.model.vo.geral.SimNao;
import static br.com.orlandoburli.minhascontas.model.utils.Dicionario.Despesa.*;
import static br.com.orlandoburli.minhascontas.model.utils.Dicionario.Despesa.Colunas.*;

@Table(TABELA_DESPESA)
public class DespesaVo extends BaseVo {

	private static final long serialVersionUID = 1L;

	@Column(name = ID_DESPESA, dataType = DataType.INT, isAutoIncrement = true, isKey = true, sequenceName = SEQUENCIA_DESPESA)
	private Integer idDespesa;

	@Column(name = ID_USUARIO, dataType = DataType.INT, isNotNull = true)
	@Description("Usuário")
	@NotNull
	@NotEmpty
	private Integer idUsuario;

	@Column(name = DESCRICAO_DESPESA, dataType = DataType.VARCHAR, maxSize = 150, isNotNull = true)
	@Description("Descrição da Despesa")
	@NotNull
	@NotEmpty
	@FullTrim
	@MinSize(3)
	@MaxSize(150)
	private String descricaoDespesa;

	@Column(name = DATA_VENCIMENTO, dataType = DataType.DATE, isNotNull = true)
	@NotNull
	@Description("Vencimento")
	private Calendar dataVencimento;

	@Column(name = DATA_PAGAMENTO, dataType = DataType.DATE)
	@Description("Pagamento")
	private Calendar dataPagamento;

	@Column(name = REINCIDENTE, dataType = DataType.CHAR, maxSize = 1)
	@Description("Reincidente")
	@MinSize(1)
	@MaxSize(1)
	@Domain(SimNao.class)
	private String reincidente;

	@Column(name = ID_CATEGORIA, dataType = DataType.INT, isNotNull = true)
	@NotNull
	@Description("Categoria")
	private Integer idCategoria;

	@Column(name = VALOR, dataType = DataType.NUMERIC, maxSize = 10, precision = 2, isNotNull = true)
	@Description("Valor")
	@NotNull
	@NotZero
	@Precision(2)
	private BigDecimal valor;

	@Column(name = JUROS_MULTAS, dataType = DataType.NUMERIC, maxSize = 10, precision = 2)
	@Description("Juros / Multas")
	@NotNegative
	@NotNull
	@ZeroIfNull
	@Precision(2)
	private BigDecimal jurosMulta;

	@Column(name = DESCONTO, dataType = DataType.NUMERIC, maxSize = 10, precision = 2)
	@Description("Desconto")
	@NotNull
	@NotNegative
	@ZeroIfNull
	@Precision(2)
	private BigDecimal desconto;

	@Column(name = VALOR_TOTAL, dataType = DataType.NUMERIC, maxSize = 10, precision = 2)
	@ZeroIfNull
	@Precision(2)
	private BigDecimal valorTotal;

	@Column(name = VALOR_PAGO, dataType = DataType.NUMERIC, maxSize = 10, precision = 2)
	@ZeroIfNull
	@Precision(2)
	private BigDecimal valorPago;

	@Column(name = STATUS_DESPESA, dataType = DataType.CHAR, maxSize = 1)
	@NotNull
	@NotEmpty
	@MinSize(1)
	@MaxSize(1)
	@Description("Status")
	@Domain(StatusDespesaReceita.class)
	private String statusDespesa;

	@Column(name = ID_MOVIMENTACAO, dataType = DataType.INT)
	private Integer idMovimentacao;

	@Join(columnsLocal = { ID_USUARIO }, columnsRemote = { Usuario.Colunas.ID_USUARIO })
	private UsuarioVo usuario;

	@Join(columnsLocal = { ID_MOVIMENTACAO }, columnsRemote = { Movimentacao.Colunas.ID_MOVIMENTACAO })
	private MovimentacaoVo movimentacao;
	
	@Join(columnsLocal = { ID_CATEGORIA }, columnsRemote = { Categoria.Colunas.ID_CATEGORIA })
	private CategoriaVo categoria;

	public Integer getIdDespesa() {
		return idDespesa;
	}

	public void setIdDespesa(Integer idDespesa) {
		this.idDespesa = idDespesa;
	}

	public String getDescricaoDespesa() {
		return descricaoDespesa;
	}

	public void setDescricaoDespesa(String descricaoDespesa) {
		this.descricaoDespesa = descricaoDespesa;
	}

	public Calendar getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Calendar getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getReincidente() {
		return reincidente;
	}

	public void setReincidente(String reincidente) {
		this.reincidente = reincidente;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Integer getIdMovimentacao() {
		return idMovimentacao;
	}

	public void setIdMovimentacao(Integer idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}

	public BigDecimal getJurosMulta() {
		return jurosMulta;
	}

	public void setJurosMulta(BigDecimal jurosMulta) {
		this.jurosMulta = jurosMulta;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public UsuarioVo getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVo usuario) {
		this.usuario = usuario;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public CategoriaVo getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaVo categoria) {
		this.categoria = categoria;
	}

	public MovimentacaoVo getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(MovimentacaoVo movimentacao) {
		this.movimentacao = movimentacao;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public String getStatusDespesa() {
		return statusDespesa;
	}

	public void setStatusDespesa(String statusDespesa) {
		this.statusDespesa = statusDespesa;
	}
}
