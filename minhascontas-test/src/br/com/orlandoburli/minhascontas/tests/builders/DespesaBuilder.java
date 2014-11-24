package br.com.orlandoburli.minhascontas.tests.builders;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.orlandoburli.minhascontas.model.vo.acesso.UsuarioVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.CategoriaVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.DespesaVo;
import br.com.orlandoburli.minhascontas.model.vo.financeiro.StatusDespesaReceita;
import br.com.orlandoburli.minhascontas.model.vo.geral.SimNao;

public final class DespesaBuilder {

	public static DespesaVo criaDespesa(UsuarioVo usuario, String descricaoDespesa, Calendar dataVencimento, BigDecimal valor, String reincidente, CategoriaVo categoria) {

		DespesaVo despesa = new DespesaVo();

		despesa.setDescricaoDespesa(descricaoDespesa);
		despesa.setDataVencimento(dataVencimento);
		despesa.setValor(valor);
		despesa.setReincidente(reincidente);

		if (usuario != null) {
			despesa.setIdUsuario(usuario.getIdUsuario());
			despesa.setUsuario(usuario);
		}

		if (categoria != null) {
			despesa.setIdCategoria(categoria.getIdCategoria());
			despesa.setCategoria(categoria);
		}

		despesa.setStatusDespesa(StatusDespesaReceita.ABERTO);

		return despesa;
	}

	public static DespesaVo criaContaLuz(UsuarioVo usuario, String data, BigDecimal valor, CategoriaVo categoria) {

		Calendar dataVencimento = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			dataVencimento.setTime(sdf.parse(data));
		} catch (ParseException e) {
			dataVencimento = null;
		} catch (NullPointerException e) {
			dataVencimento = null;
		}

		return criaDespesa(usuario, "Conta de Luz", dataVencimento, valor, SimNao.SIM, categoria);
	}
}
