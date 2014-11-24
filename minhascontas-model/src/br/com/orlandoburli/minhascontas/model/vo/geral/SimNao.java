package br.com.orlandoburli.minhascontas.model.vo.geral;

import br.com.orlandoburli.framework.core.vo.BaseDomain;

public class SimNao extends BaseDomain {

	public static final String SIM = "S";
	public static final String NAO = "N";

	public static final String SIM_DESCRITIVO = "Sim";
	public static final String NAO_DESCRITIVO = "Não";

	@Override
	public String[] getValues() {
		return new String[] { SIM, NAO };
	}

	@Override
	public String[] getDescriptions() {
		return new String[] { SIM_DESCRITIVO, NAO_DESCRITIVO };
	}

}
