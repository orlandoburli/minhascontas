package br.com.orlandoburli.minhascontas.model.vo.financeiro;

import java.math.BigDecimal;
import java.util.Calendar;

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
import static br.com.orlandoburli.minhascontas.model.utils.Dicionario.Receita.*;
import static br.com.orlandoburli.minhascontas.model.utils.Dicionario.Receita.Colunas.*;

@Table(TABELA_RECEITA)
public class ReceitaVo extends BaseVo {

	private static final long serialVersionUID = 1L;

	@Column(name = ID_RECEITA, dataType = DataType.INT, isAutoIncrement = true, isKey = true, sequenceName = SEQUENCIA_RECEITA)
	@Description("Código")
	private Integer idReceita;

	@Column(name = ID_USUARIO, dataType = DataType.INT, isNotNull = true)
	@Description("Usuário")
	@NotNull
	private Integer idUsuario;

	@Column(name = DESCRICAO_RECEITA, isNotNull = true, dataType = DataType.VARCHAR, maxSize = 150)
	@Description("Descrição da Receita")
	@NotNull
	@NotEmpty
	@MinSize(3)
	@MaxSize(150)
	private String descricaoReceita;

	@Column(name = DATA_VENCIMENTO, isNotNull = true, dataType = DataType.DATE)
	@NotNull
	@Description("Data de Vencimento")
	private Calendar dataVencimento;

	@Column(name = DATA_RECEBIMENTO, dataType = DataType.DATE)
	@Description("Data de Recebimento")
	private Calendar dataRecebimento;

	@Column(name = VALOR, dataType = DataType.NUMERIC, maxSize = 10, precision = 2)
	@Description("Valor")
	@NotNull
	@NotZero
	@Precision(2)
	private BigDecimal valor;

	@Column(name = ID_CATEGORIA, dataType = DataType.INT, isNotNull = true)
	@Description("Categoria")
	@NotNull
	private Integer idCategoria;

	@Column(name = JUROS_MULTAS, dataType = DataType.NUMERIC, maxSize = 10, precision = 2)
	@Description("Juros / Multas")
	@NotNegative
	@ZeroIfNull
	@Precision(2)
	private BigDecimal jurosMultas;

	@Column(name = DESCONTO, dataType = DataType.NUMERIC, maxSize = 10, precision = 2)
	@Description("Descontos")
	@NotNegative
	@ZeroIfNull
	@Precision(2)
	private BigDecimal desconto;

	@Column(name = VALOR_TOTAL, dataType = DataType.NUMERIC, maxSize = 10, precision = 2)
	@Description("Valor Total")
	@NotNegative
	@ZeroIfNull
	@Precision(2)
	private BigDecimal valorTotal;

	@Column(name = ID_MOVIMENTACAO, dataType = DataType.INT)
	@Description("Movimentação")
	private Integer idMovimentacao;

	@Column(name = VALOR_RECEBIDO, dataType = DataType.NUMERIC, maxSize = 10, precision = 2)
	@Description("Valor Recebido")
	@NotNegative
	@Precision(2)
	@ZeroIfNull
	private BigDecimal valorRecebido;

	@Column(name = STATUS_RECEITA, dataType = DataType.CHAR, maxSize = 1, isNotNull = true)
	@Description("Status")
	@Domain(StatusDespesaReceita.class)
	private String statusReceita;

	@Join(columnsLocal = { ID_USUARIO }, columnsRemote = { Usuario.Colunas.ID_USUARIO })
	private UsuarioVo usuario;

	@Join(columnsLocal = { ID_MOVIMENTACAO }, columnsRemote = { Movimentacao.Colunas.ID_MOVIMENTACAO })
	private MovimentacaoVo movimentacao;

	@Join(columnsLocal = { ID_CATEGORIA }, columnsRemote = { Categoria.Colunas.ID_CATEGORIA })
	private CategoriaVo categoria;

	public Integer getIdReceita() {
		return idReceita;
	}

	public void setIdReceita(Integer idReceita) {
		this.idReceita = idReceita;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDescricaoReceita() {
		return descricaoReceita;
	}

	public void setDescricaoReceita(String descricaoReceita) {
		this.descricaoReceita = descricaoReceita;
	}

	public Calendar getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Calendar getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Calendar dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public BigDecimal getJurosMultas() {
		return jurosMultas;
	}

	public void setJurosMultas(BigDecimal jurosMultas) {
		this.jurosMultas = jurosMultas;
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

	public String getStatusReceita() {
		return statusReceita;
	}

	public void setStatusReceita(String statusReceita) {
		this.statusReceita = statusReceita;
	}

	public UsuarioVo getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVo usuario) {
		this.usuario = usuario;
	}

	public CategoriaVo getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaVo categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(BigDecimal valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public MovimentacaoVo getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(MovimentacaoVo movimentacao) {
		this.movimentacao = movimentacao;
	}

}
