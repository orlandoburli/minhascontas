package br.com.orlandoburli.minhascontas.tests.suites;

import java.io.FileInputStream;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.orlandoburli.minhascontas.tests.cases.CategoriaTest;
import br.com.orlandoburli.minhascontas.tests.cases.ContaTest;
import br.com.orlandoburli.minhascontas.tests.cases.DespesaTest;
import br.com.orlandoburli.minhascontas.tests.cases.MovimentacaoTest;
import br.com.orlandoburli.minhascontas.tests.cases.ReceitaTest;
import br.com.orlandoburli.minhascontas.tests.cases.UsuarioTest;

@RunWith(Suite.class)
@SuiteClasses({ UsuarioTest.class, CategoriaTest.class, ContaTest.class, MovimentacaoTest.class, DespesaTest.class, ReceitaTest.class })
public class AllPostgresTests {

	@BeforeClass
	public static void prepare() {
		try {
			System.getProperties().load(new FileInputStream("teste-postgres.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
