package br.com.orlandoburli.minhascontas.tests.suites;

import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;

public class BurliSuite extends Suite {

	protected BurliSuite(Class<?> klass, Class<?>[] suiteClasses) throws InitializationError {
		super(klass, suiteClasses);
	}
	
}
