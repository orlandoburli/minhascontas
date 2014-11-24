package br.com.orlandoburli.minhascontas.tests.builders;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.orlandoburli.minhascontas.model.vo.acesso.UsuarioVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.CategoriaVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.ReceitaVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.StatusDespesaReceita;

public class ReceitaBuilder {

	public static ReceitaVo criaReceita(String descricaoReceita, Calendar dataVencimento, BigDecimal valor, UsuarioVo usuario, CategoriaVo categoria) {
		ReceitaVo receita = new ReceitaVo();

		receita.setDescricaoReceita(descricaoReceita);
		receita.setDataVencimento(dataVencimento);
		receita.setValor(valor);

		if (usuario != null) {
			receita.setIdUsuario(usuario.getIdUsuario());
			receita.setUsuario(usuario);
		}

		if (categoria != null) {
			receita.setIdCategoria(categoria.getIdCategoria());
			receita.setCategoria(categoria);
		}

		receita.setStatusReceita(StatusDespesaReceita.ABERTO);

		return receita;
	}

	public static ReceitaVo criaReceitaSalario(String data, BigDecimal valor, UsuarioVo usuario, CategoriaVo categoria) {
		Calendar dataVencimento = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			dataVencimento.setTime(sdf.parse(data));
		} catch (ParseException e) {
			dataVencimento = null;
		} catch (NullPointerException e) {
			dataVencimento = null;
		}

		return criaReceita("Salário", dataVencimento, valor, usuario, categoria);
	}

	public static ReceitaVo criaReceitaOutrasRendas(String data, BigDecimal valor, UsuarioVo usuario, CategoriaVo categoria) {
		Calendar dataVencimento = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			dataVencimento.setTime(sdf.parse(data));
		} catch (ParseException e) {
			dataVencimento = null;
		} catch (NullPointerException e) {
			dataVencimento = null;
		}
		return criaReceita("Outras Rendas", dataVencimento, valor, usuario, categoria);
	}

}
