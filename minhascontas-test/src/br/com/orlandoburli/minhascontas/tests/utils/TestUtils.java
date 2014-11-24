package br.com.orlandoburli.minhascontas.tests.utils;

import java.io.FileInputStream;
import java.io.IOException;

import br.com.orlandoburli.framework.core.dao.DAOManager;
import br.com.orlandoburli.framework.core.dao.exceptions.DAOException;
import br.com.orlandoburli.minhascontas.model.dao.acesso.UsuarioDao;
import br.com.orlandoburli.minhascontas.model.dao.financeiro.CategoriaDao;
import br.com.orlandoburli.minhascontas.model.dao.financeiro.ContaDao;
import br.com.orlandoburli.minhascontas.model.dao.financeiro.DespesaDao;
import br.com.orlandoburli.minhascontas.model.dao.financeiro.MovimentacaoDao;
import br.com.orlandoburli.minhascontas.model.dao.financeiro.ReceitaDao;

public class TestUtils {

	public static void clearDB(DAOManager manager) throws DAOException {
		new UsuarioDao(manager).dropTable().dropSequence().checkTable();
		new CategoriaDao(manager).dropTable().dropSequence().checkTable();
		new ContaDao(manager).dropTable().dropSequence().checkTable();
		new MovimentacaoDao(manager).dropTable().dropSequence().checkTable();
		new DespesaDao(manager).dropTable().dropSequence().checkTable();
		new ReceitaDao(manager).dropTable().dropSequence().checkTable();
	}

	public static void loadPropertiesIfNecessary() {
		if (System.getProperty("log.level") == null) {
			try {
				System.getProperties().load(new FileInputStream("teste-postgres.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
