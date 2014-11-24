package br.com.orlandoburli.minhascontas.model.vo.financeiro;

import br.com.orlandoburli.framework.core.vo.BaseDomain;

public class StatusDespesaReceita extends BaseDomain {

	public static final String ABERTO = "A";
	public static final String PAGO = "P";
	public static final String CANCELADO = "C";

	public static final String ABERTO_DESCRITIVO = "Aberto";
	public static final String PAGO_DESCRITIVO = "Pago";
	public static final String CANCELADO_DESCRITIVO = "Cancelado";

	@Override
	public String[] getValues() {
		return new String[] { ABERTO, PAGO, CANCELADO };
	}

	@Override
	public String[] getDescriptions() {
		return new String[] { ABERTO_DESCRITIVO, PAGO_DESCRITIVO, CANCELADO_DESCRITIVO };
	}

}
