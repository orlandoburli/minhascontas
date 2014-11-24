package br.com.orlandoburli.minhascontas.model.exceptions;

import br.com.orlandoburli.framework.core.be.exceptions.BeException;

public class LoginInvalidoException extends BeException {

	private static final long serialVersionUID = 1L;

	public LoginInvalidoException(String message, String field) {
		super(message, field);
	}

}