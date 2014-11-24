package br.com.orlandoburli.minhascontas.model.vo.financeiro;

import br.com.orlandoburli.framework.core.vo.BaseDomain;

public class CreditoDebito extends BaseDomain {
	public static final String CREDITO = "C";
	public static final String DEBITO = "D";
	public static final String SALDO = "S";

	public static final String CREDITO_DESCRITIVO = "Crédito";
	public static final String DEBITO_DESCRITIVO = "Débito";
	public static final String SALDO_DESCRITIVO = "Saldo";

	@Override
	public String[] getValues() {
		return new String[] { CREDITO, DEBITO, SALDO };
	}

	@Override
	public String[] getDescriptions() {
		return new String[] { CREDITO_DESCRITIVO, DEBITO_DESCRITIVO, SALDO_DESCRITIVO };
	}

	public Integer fator(String tipo) {
		if (tipo != null) {
			if (tipo.equalsIgnoreCase(CREDITO)) {
				return 1;
			} else if (tipo.equalsIgnoreCase(DEBITO)) {
				return -1;
			} else if (tipo.equalsIgnoreCase(SALDO)) {
				return 0;
			}
		}
		return null;
	}

}
