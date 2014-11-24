package br.com.orlandoburli.minhascontas.model.dao.financeiro;

import br.com.orlandoburli.framework.core.dao.BaseCadastroDao;
import br.com.orlandoburli.framework.core.dao.DAOManager;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.DespesaVo;

public class DespesaDao extends BaseCadastroDao<DespesaVo> {

	public DespesaDao(DAOManager manager) {
		super(manager);
	}

	@Override
	public int getMaxSubJoins() {
		return 5;
	}
}
