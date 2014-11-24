package br.com.orlandoburli.minhascontas.services.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.orlandoburli.minhascontas.services.acesso.AcessoService;

@ApplicationPath("services")
public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> rootResources = new HashSet<Class<?>>();
		rootResources.add(AcessoService.class);
		return rootResources;
	}
}
