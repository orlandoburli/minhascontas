package br.com.orlandoburli.minhascontas.model.vo.acesso;

import br.com.orlandoburli.framework.core.be.validation.annotations.validators.Domain;
import br.com.orlandoburli.framework.core.be.validation.annotations.validators.Email;
import br.com.orlandoburli.framework.core.be.validation.annotations.validators.MaxSize;
import br.com.orlandoburli.framework.core.be.validation.annotations.validators.MinSize;
import br.com.orlandoburli.framework.core.be.validation.annotations.validators.NotEmpty;
import br.com.orlandoburli.framework.core.be.validation.annotations.validators.NotNull;
import br.com.orlandoburli.framework.core.dao.annotations.Column;
import br.com.orlandoburli.framework.core.dao.annotations.DataType;
import br.com.orlandoburli.framework.core.dao.annotations.Table;
import br.com.orlandoburli.framework.core.dao.annotations.UniqueConstraint;
import br.com.orlandoburli.framework.core.vo.BaseVo;
import br.com.orlandoburli.framework.core.vo.annotations.Description;
import br.com.orlandoburli.minhascontas.model.utils.Dicionario.Usuario;
import br.com.orlandoburli.minhascontas.model.utils.Dicionario.Usuario.Colunas;
import br.com.orlandoburli.minhascontas.model.vo.geral.SimNao;

@Table(value = Usuario.TABELA_USUARIO, constraints = { @UniqueConstraint(constraintName = Usuario.CONSTRAINT_UNIQUE_EMAIL, column = Usuario.Colunas.EMAIL), @UniqueConstraint(constraintName = Usuario.CONSTRAINT_UNIQUE_HASH_EMAIL, columns = { Usuario.Colunas.HASH_EMAIL }) })
public class UsuarioVo extends BaseVo {

	private static final long serialVersionUID = 1L;

	@Column(name = Usuario.Colunas.ID_USUARIO, dataType = DataType.INT, isAutoIncrement = true, isKey = true, sequenceName = Usuario.SEQUENCIA_USUARIO)
	@Description("Id do Usuário")
	private Integer idUsuario;

	@Column(name = Usuario.Colunas.NOME, dataType = DataType.VARCHAR, maxSize = 50, isNotNull = true)
	@Description("Nome do Usuário")
	@NotNull
	@NotEmpty
	@MinSize(3)
	@MaxSize(50)
	private String nomeUsuario;

	@Column(name = Usuario.Colunas.SOBRENOME, dataType = DataType.VARCHAR, maxSize = 100)
	@MaxSize(100)
	@NotEmpty
	@NotNull
	@Description("Sobrenome")
	private String sobreNomeUsuario;

	@Column(name = Colunas.TIPO_AUTENTICACAO, dataType = DataType.CHAR, maxSize = 1, isNotNull = true)
	@Description("Tipo de Autenticação")
	@NotNull
	@NotEmpty
	@Domain(TipoAcessoUsuario.class)
	private String tipoAutenticacao;

	@Column(name = Colunas.EMAIL, dataType = DataType.VARCHAR, maxSize = 500, isNotNull = true)
	@NotNull
	@NotEmpty
	@Email
	@Description("Email")
	private String email;

	@Column(name = Colunas.ID_FACEBOOK, dataType = DataType.VARCHAR, maxSize = 100)
	@Description("Id do Facebook")
	private String idFacebook;

	@Column(name = Colunas.SENHA, dataType = DataType.VARCHAR, maxSize = 200)
	@NotNull
	@NotEmpty
	private String senha;

	@Column(name = Colunas.FLAG_CONFIRMADO, dataType = DataType.CHAR, maxSize = 1, isNotNull = true)
	@NotNull
	@NotEmpty
	@Description("Flag do Email Confirmado")
	@Domain(SimNao.class)
	private String flagConfirmado;

	@Column(name = Colunas.PATH_FOTO, dataType = DataType.VARCHAR, maxSize = 200)
	private String pathFoto;

	@Column(name = Colunas.HASH_EMAIL, dataType = DataType.VARCHAR, maxSize = 200)
	private String hashEmail;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSobreNomeUsuario() {
		return sobreNomeUsuario;
	}

	public void setSobreNomeUsuario(String sobreNomeUsuario) {
		this.sobreNomeUsuario = sobreNomeUsuario;
	}

	public String getTipoAutenticacao() {
		return tipoAutenticacao;
	}

	public void setTipoAutenticacao(String tipoAutenticacao) {
		this.tipoAutenticacao = tipoAutenticacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdFacebook() {
		return idFacebook;
	}

	public void setIdFacebook(String idFacebook) {
		this.idFacebook = idFacebook;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPathFoto() {
		return pathFoto;
	}

	public void setPathFoto(String pathFoto) {
		this.pathFoto = pathFoto;
	}

	public String getFlagConfirmado() {
		return flagConfirmado;
	}

	public void setFlagConfirmado(String flagConfirmado) {
		this.flagConfirmado = flagConfirmado;
	}

	public String getHashEmail() {
		return hashEmail;
	}

	public void setHashEmail(String hashEmail) {
		this.hashEmail = hashEmail;
	}
}
