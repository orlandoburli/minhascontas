package br.com.orlandoburli.minhascontas.model.be.financeiro;

import java.util.List;

import br.com.orlandoburli.framework.core.be.BaseBe;
import br.com.orlandoburli.framework.core.be.exceptions.persistence.ListException;
import br.com.orlandoburli.framework.core.dao.DAOManager;
import br.com.orlandoburli.minhascontas.model.dao.financeiro.CategoriaDao;
import br.com.orlandoburli.minhascontas.model.vo.acesso.UsuarioVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.CategoriaVo;

public class CategoriaBe extends BaseBe<CategoriaVo, CategoriaDao> {

	public CategoriaBe(DAOManager manager) {
		super(manager);
	}

	public List<CategoriaVo> getByUsuario(UsuarioVo usuario) throws ListException {
		if (usuario == null) {
			return null;
		}

		CategoriaVo filter = new CategoriaVo();
		filter.setIdUsuario(usuario.getIdUsuario());

		return getList(filter);
	}

	public CategoriaVo getByNome(UsuarioVo usuario, String nome, CategoriaVo pai) throws ListException {
		if (usuario == null) {
			return null;
		}

		CategoriaVo filter = new CategoriaVo();
		filter.setIdUsuario(usuario.getIdUsuario());
		filter.setNomeCategoria(nome);

		if (pai != null) {
			filter.setIdCategoriaPai(pai.getIdCategoria());
		}
		
		List<CategoriaVo> list = getList(filter);
		
		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

}
