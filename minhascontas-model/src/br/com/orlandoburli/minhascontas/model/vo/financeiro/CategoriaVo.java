package br.com.orlandoburli.minhascontas.model.vo.financeiro;

import br.com.orlandoburli.framework.core.be.validation.annotations.validators.Domain;
import br.com.orlandoburli.framework.core.be.validation.annotations.validators.NotEmpty;
import br.com.orlandoburli.framework.core.be.validation.annotations.validators.NotNull;
import br.com.orlandoburli.framework.core.dao.annotations.Column;
import br.com.orlandoburli.framework.core.dao.annotations.DataType;
import br.com.orlandoburli.framework.core.dao.annotations.Join;
import br.com.orlandoburli.framework.core.dao.annotations.Table;
import br.com.orlandoburli.framework.core.vo.BaseVo;
import br.com.orlandoburli.framework.core.vo.annotations.Description;
import br.com.orlandoburli.minhascontas.model.utils.Dicionario.Categoria;
import br.com.orlandoburli.minhascontas.model.utils.Dicionario.Categoria.Colunas;
import br.com.orlandoburli.minhascontas.model.utils.Dicionario.Usuario;
import br.com.orlandoburli.minhascontas.model.vo.acesso.UsuarioVo;

@Table(Categoria.TABELA_CATEGORIA)
public class CategoriaVo extends BaseVo {

	private static final long serialVersionUID = 1L;

	@Column(name = Colunas.ID_CATEGORIA, dataType = DataType.INT, isKey = true, isAutoIncrement = true, sequenceName = Categoria.SEQUENCIA_CATEGORIA)
	private Integer idCategoria;

	@Column(name = Colunas.ID_USUARIO, dataType = DataType.INT, isNotNull = true)
	@Description("Usuário")
	@NotNull
	@NotEmpty
	private Integer idUsuario;

	@Column(name = Colunas.NOME_CATEGORIA, dataType = DataType.VARCHAR, maxSize = 100, isNotNull = true)
	@NotNull
	@NotEmpty
	@Description("Descrição da Categoria")
	private String nomeCategoria;

	@Column(name = Colunas.TIPO_CATEGORIA, dataType = DataType.CHAR, maxSize = 1, isNotNull = true)
	@NotNull
	@NotEmpty
	@Domain(TipoCategoria.class)
	@Description("Tipo de Categoria")
	private String tipoCategoria;

	@Column(name = Colunas.ID_CATEGORIA_PAI, dataType = DataType.INT)
	@Description("Categoria Pai")
	private Integer idCategoriaPai;

	@Join(columnsLocal = { Colunas.ID_USUARIO }, columnsRemote = { Usuario.Colunas.ID_USUARIO })
	private UsuarioVo usuario;

	@Join(columnsLocal = { Colunas.ID_CATEGORIA_PAI }, columnsRemote = { Colunas.ID_CATEGORIA })
	private CategoriaVo categoriaPai;

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getTipoCategoria() {
		return tipoCategoria;
	}

	public void setTipoCategoria(String tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}

	public Integer getIdCategoriaPai() {
		return idCategoriaPai;
	}

	public void setIdCategoriaPai(Integer idCategoriaPai) {
		this.idCategoriaPai = idCategoriaPai;
	}

	public UsuarioVo getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVo usuario) {
		this.usuario = usuario;
	}

	public CategoriaVo getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(CategoriaVo categoriaPai) {
		this.categoriaPai = categoriaPai;
	}
}
