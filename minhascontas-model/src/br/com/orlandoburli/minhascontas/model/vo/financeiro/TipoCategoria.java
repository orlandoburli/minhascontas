package br.com.orlandoburli.minhascontas.model.vo.financeiro;

import br.com.orlandoburli.framework.core.vo.BaseDomain;

public class TipoCategoria extends BaseDomain {

	public static final String DESPESA = "D";
	public static final String RECEITA = "R";

	public static final String DESPESA_DESCRITIVO = "Despesas";
	public static final String RECEITA_DESCRITIVO = "Receitas";

	@Override
	public String[] getValues() {
		return new String[] { DESPESA, RECEITA };
	}

	@Override
	public String[] getDescriptions() {
		return new String[] { DESPESA_DESCRITIVO, RECEITA_DESCRITIVO };
	}

}
