package br.com.orlandoburli.minhascontas.model.utils;

import br.com.orlandoburli.framework.core.dao.DAOManager;
import br.com.orlandoburli.framework.core.dao.exceptions.DAOException;
import br.com.orlandoburli.framework.core.log.Log;
import br.com.orlandoburli.minhascontas.model.dao.acesso.TokenDao;
import br.com.orlandoburli.minhascontas.model.dao.acesso.UsuarioDao;
import br.com.orlandoburli.minhascontas.model.dao.financeiro.CategoriaDao;
import br.com.orlandoburli.minhascontas.model.dao.financeiro.ContaDao;
import br.com.orlandoburli.minhascontas.model.dao.financeiro.DespesaDao;
import br.com.orlandoburli.minhascontas.model.dao.financeiro.MovimentacaoDao;
import br.com.orlandoburli.minhascontas.model.dao.financeiro.ReceitaDao;

public final class DBUtils {

	public static void checkDaos() {
		DAOManager manager = DAOManager.getInstance();

		try {
			new UsuarioDao(manager).checkTable();
			new TokenDao(manager).checkTable();

			new CategoriaDao(manager).checkTable();
			new ContaDao(manager).checkTable();
			new MovimentacaoDao(manager).checkTable();
			new DespesaDao(manager).checkTable();
			new ReceitaDao(manager).checkTable();
		} catch (DAOException e) {
			Log.critical(e);
		} finally {
			manager.commit();
		}
	}
}
