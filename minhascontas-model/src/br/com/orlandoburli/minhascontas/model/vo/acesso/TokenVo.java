package br.com.orlandoburli.minhascontas.model.vo.acesso;

import java.util.Calendar;

import br.com.orlandoburli.framework.core.dao.annotations.Column;
import br.com.orlandoburli.framework.core.dao.annotations.DataType;
import br.com.orlandoburli.framework.core.dao.annotations.Join;
import br.com.orlandoburli.framework.core.dao.annotations.Table;
import br.com.orlandoburli.framework.core.vo.BaseVo;
import br.com.orlandoburli.minhascontas.model.utils.Dicionario;
import br.com.orlandoburli.minhascontas.model.utils.Dicionario.Token.Colunas;
import br.com.orlandoburli.minhascontas.model.utils.Dicionario.Usuario;

@Table(Dicionario.Token.TABELA_TOKEN)
public class TokenVo extends BaseVo {

	private static final long serialVersionUID = 1L;

	@Column(name = Colunas.ID_TOKEN, dataType = DataType.INT, isAutoIncrement = true, isKey = true)
	private Integer idToken;

	@Column(name = Colunas.CHAVE_TOKEN, dataType = DataType.VARCHAR, maxSize = 50, isNotNull = true)
	private String chaveToken;

	@Column(name = Colunas.ID_USUARIO, dataType = DataType.INT, isNotNull = true)
	private Integer idUsuario;

	@Column(name = Colunas.DATA_HORA_CRIACAO, dataType = DataType.DATETIME, isNotNull = true)
	private Calendar dataHoraCriacao;

	@Column(name = Colunas.DATA_HORA_ULTIMO_ACESSO, dataType = DataType.DATETIME, isNotNull = true)
	private Calendar dataUltimoAcesso;

	@Column(name = Colunas.EXPIRADO, dataType = DataType.CHAR, maxSize = 1, isNotNull = true)
	private String expirado;

	@Join(columnsLocal = { Colunas.ID_USUARIO }, columnsRemote = { Usuario.Colunas.ID_USUARIO })
	private UsuarioVo usuario;

	public Integer getIdToken() {
		return idToken;
	}

	public void setIdToken(Integer idToken) {
		this.idToken = idToken;
	}

	public String getChaveToken() {
		return chaveToken;
	}

	public void setChaveToken(String chaveToken) {
		this.chaveToken = chaveToken;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Calendar getDataHoraCriacao() {
		return dataHoraCriacao;
	}

	public void setDataHoraCriacao(Calendar dataHoraCriacao) {
		this.dataHoraCriacao = dataHoraCriacao;
	}

	public Calendar getDataUltimoAcesso() {
		return dataUltimoAcesso;
	}

	public void setDataUltimoAcesso(Calendar dataUltimoAcesso) {
		this.dataUltimoAcesso = dataUltimoAcesso;
	}

	public String getExpirado() {
		return expirado;
	}

	public void setExpirado(String expirado) {
		this.expirado = expirado;
	}

	public UsuarioVo getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVo usuario) {
		this.usuario = usuario;
	}

}
