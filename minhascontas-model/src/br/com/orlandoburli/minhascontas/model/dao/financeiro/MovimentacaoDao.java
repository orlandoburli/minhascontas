package br.com.orlandoburli.minhascontas.model.dao.financeiro;

import br.com.orlandoburli.framework.core.dao.BaseCadastroDao;
import br.com.orlandoburli.framework.core.dao.DAOManager;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.MovimentacaoVo;

public class MovimentacaoDao extends BaseCadastroDao<MovimentacaoVo>{

	public MovimentacaoDao(DAOManager manager) {
		super(manager);
	}

}
