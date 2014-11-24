package br.com.orlandoburli.minhascontas.services.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.framework.core.log.Log;
import br.com.orlandoburli.minhascontas.model.utils.DBUtils;

@WebServlet(value = "/load", loadOnStartup = 1)
public class LoadAppServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.init();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	public void init() throws ServletException {
		super.init();

		try {
			System.getProperties().load(new FileInputStream(getServletContext().getRealPath("/") + File.separator + "app.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Log.info("Inicializando app Minhas Contas - Services");

		Log.info("Propriedades carregadas.");

		DBUtils.checkDaos();

		Log.info("DAOs checadas.");

		Log.info("App Minhas Contas - Services inicializado.");
	}
}
