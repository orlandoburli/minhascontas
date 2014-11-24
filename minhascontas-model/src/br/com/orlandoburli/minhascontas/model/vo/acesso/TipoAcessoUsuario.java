package br.com.orlandoburli.minhascontas.model.vo.acesso;

import br.com.orlandoburli.framework.core.vo.BaseDomain;

public class TipoAcessoUsuario extends BaseDomain {

	public static final String EMAIL = "E";
	public static final String FACEBOOK = "F";

	public static final String EMAIL_DESCRITIVO = "Email";
	public static final String FACEBOOK_DESCRITIVO = "Facebook";

	@Override
	public String[] getValues() {
		return new String[] { EMAIL, FACEBOOK };
	}

	@Override
	public String[] getDescriptions() {
		return new String[] { EMAIL_DESCRITIVO, FACEBOOK_DESCRITIVO };
	}

}
