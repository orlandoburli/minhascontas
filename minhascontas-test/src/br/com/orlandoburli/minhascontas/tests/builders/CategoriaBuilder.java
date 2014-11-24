package br.com.orlandoburli.minhascontas.tests.builders;

import br.com.orlandoburli.minhascontas.model.vo.acesso.UsuarioVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.CategoriaVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.TipoCategoria;

public final class CategoriaBuilder {

	public static CategoriaVo criaCategoria(String nomeCategoria, String tipoCategoria, UsuarioVo usuario, CategoriaVo pai) {
		CategoriaVo categoria = new CategoriaVo();

		categoria.setNomeCategoria(nomeCategoria);
		categoria.setTipoCategoria(tipoCategoria);
		if (usuario != null) {
			categoria.setIdUsuario(usuario.getIdUsuario());
		}
		if (pai != null) {
			categoria.setIdCategoriaPai(pai.getIdCategoria());
		}

		return categoria;
	}

	public static CategoriaVo criaCategoriaReceita(UsuarioVo usuario) {
		return criaCategoria("Receitas", TipoCategoria.RECEITA, usuario, null);
	}

	public static CategoriaVo criaCategoriaReceitaFixas(UsuarioVo usuario, CategoriaVo pai) {
		return criaCategoria("Fixas", TipoCategoria.RECEITA, usuario, pai);
	}

	public static CategoriaVo criaCategoriaReceitaVariaveis(UsuarioVo usuario, CategoriaVo pai) {
		return criaCategoria("Variáveis", TipoCategoria.RECEITA, usuario, pai);
	}

	public static CategoriaVo criaCategoriaReceitaSalarios(UsuarioVo usuario, CategoriaVo pai) {
		return criaCategoria("Salários", TipoCategoria.RECEITA, usuario, pai);
	}

	public static CategoriaVo criaCategoriaReceitaOutrasReceitasVariaveis(UsuarioVo usuario, CategoriaVo pai) {
		return criaCategoria("Outras Receitas Variáveis", TipoCategoria.RECEITA, usuario, pai);
	}

	public static CategoriaVo criaCategoriaReceitaOutrasReceitasFixas(UsuarioVo usuario, CategoriaVo pai) {
		return criaCategoria("Outras Receitas Fixas", TipoCategoria.RECEITA, usuario, pai);
	}

	public static CategoriaVo criaCategoriaDespesa(UsuarioVo usuario) {
		return criaCategoria("Despesas", TipoCategoria.DESPESA, usuario, null);
	}
	
	public static CategoriaVo criaCategoriaDespesaFixa(UsuarioVo usuario, CategoriaVo pai) {
		return criaCategoria("Fixas", TipoCategoria.DESPESA, usuario, pai);
	}
	
	public static CategoriaVo criaCategoriaDespesaVariaveis(UsuarioVo usuario, CategoriaVo pai) {
		return criaCategoria("Variáveis", TipoCategoria.DESPESA, usuario, pai);
	}
}
