package br.com.orlandoburli.minhascontas.model.vo.financeiro;

import java.math.BigDecimal;

import br.com.orlandoburli.framework.core.be.validation.annotations.transformation.Precision;
import br.com.orlandoburli.framework.core.be.validation.annotations.validators.MaxSize;
import br.com.orlandoburli.framework.core.be.validation.annotations.validators.NotEmpty;
import br.com.orlandoburli.framework.core.be.validation.annotations.validators.NotNull;
import br.com.orlandoburli.framework.core.dao.annotations.Column;
import br.com.orlandoburli.framework.core.dao.annotations.DataType;
import br.com.orlandoburli.framework.core.dao.annotations.Join;
import br.com.orlandoburli.framework.core.dao.annotations.Table;
import br.com.orlandoburli.framework.core.vo.BaseVo;
import br.com.orlandoburli.framework.core.vo.annotations.Description;
import br.com.orlandoburli.minhascontas.model.utils.Dicionario.Conta;
import br.com.orlandoburli.minhascontas.model.utils.Dicionario.Usuario;
import br.com.orlandoburli.minhascontas.model.utils.Dicionario.Conta.Colunas;
import br.com.orlandoburli.minhascontas.model.vo.acesso.UsuarioVo;

@Table(Conta.TABELA_CONTA)
public class ContaVo extends BaseVo {

	private static final long serialVersionUID = 1L;

	@Column(name = Colunas.ID_CONTA, dataType = DataType.INT, isAutoIncrement = true, isKey = true, sequenceName = Conta.SEQUENCIA_CONTA)
	@Description("Id da Conta")
	private Integer idConta;

	@Column(name = Colunas.NOME_CONTA, dataType = DataType.VARCHAR, maxSize = 100, isNotNull = true)
	@MaxSize(100)
	@NotNull
	@NotEmpty
	@Description("Nome da Conta")
	private String nomeConta;

	@Column(name = Colunas.ID_USUARIO, dataType = DataType.INT, isNotNull = true)
	@NotNull
	@NotEmpty
	@Description("Usuário")
	private Integer idUsuario;

	@Column(name = Colunas.SALDO_ATUAL, dataType = DataType.NUMERIC, maxSize = 10, precision = 2)
	@NotNull
	@Description("Saldo Inicial")
	@Precision(2)
	private BigDecimal saldoAtual;

	@Join(columnsLocal = { Colunas.ID_USUARIO }, columnsRemote = { Usuario.Colunas.ID_USUARIO })
	private UsuarioVo usuario;

	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}

	public String getNomeConta() {
		return nomeConta;
	}

	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
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

	public BigDecimal getSaldoAtual() {
		return saldoAtual;
	}

	public void setSaldoAtual(BigDecimal saldoAtual) {
		this.saldoAtual = saldoAtual;
	}
}
