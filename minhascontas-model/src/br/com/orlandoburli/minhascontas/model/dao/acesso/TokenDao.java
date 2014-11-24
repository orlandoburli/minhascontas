package br.com.orlandoburli.minhascontas.model.dao.acesso;

import br.com.orlandoburli.framework.core.dao.BaseCadastroDao;
import br.com.orlandoburli.framework.core.dao.DAOManager;
import br.com.orlandoburli.minhascontas.model.vo.acesso.TokenVo;

public class TokenDao extends BaseCadastroDao<TokenVo>{

	public TokenDao(DAOManager manager) {
		super(manager);
	}

}
