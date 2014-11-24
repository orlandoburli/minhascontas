package br.com.orlandoburli.minhascontas.model.dao.financeiro;

import br.com.orlandoburli.framework.core.dao.BaseCadastroDao;
import br.com.orlandoburli.framework.core.dao.DAOManager;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.CategoriaVo;

public class CategoriaDao extends BaseCadastroDao<CategoriaVo> {

	public CategoriaDao(DAOManager manager) {
		super(manager);
	}

	@Override
	public int getMaxSubJoins() {
		return 5;
	}
}
