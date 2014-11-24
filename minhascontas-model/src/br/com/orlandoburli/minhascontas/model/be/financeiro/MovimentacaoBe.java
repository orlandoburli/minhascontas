package br.com.orlandoburli.minhascontas.model.be.financeiro;

import br.com.orlandoburli.framework.core.be.BaseBe;
import br.com.orlandoburli.framework.core.dao.DAOManager;
import br.com.orlandoburli.minhascontas.model.dao.financeiro.MovimentacaoDao;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.MovimentacaoVo;

public class MovimentacaoBe extends BaseBe<MovimentacaoVo, MovimentacaoDao> {

	public MovimentacaoBe(DAOManager manager) {
		super(manager);
	}

}
