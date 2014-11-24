package br.com.orlandoburli.minhascontas.model.dao.financeiro;

import br.com.orlandoburli.framework.core.dao.BaseCadastroDao;
import br.com.orlandoburli.framework.core.dao.DAOManager;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.ContaVo;

public class ContaDao extends BaseCadastroDao<ContaVo> {

	public ContaDao(DAOManager manager) {
		super(manager);
	}

}
