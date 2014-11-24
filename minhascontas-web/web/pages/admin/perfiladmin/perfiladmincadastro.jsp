<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style type="text/css">
#idPerfil {
	direction: rtl;
}

#nome {
	width: 700px !important;
}

</style>
<aside>
	<label class="TituloTela">Cadastro de Perfis</label>

	<div class="FormularioCadastro FormularioGeral"
		data-page-consulta="perfiladminconsulta.admin"
		data-page-operacao="${operacao}"
		data-page-cadastro="perfiladmincadastro.admin">

		<fieldset>
			<legend>C&oacute;digo</legend>
			<input id="idPerfil" type="text" disabled="disabled"
				value="${vo.idPerfil}" />
		</fieldset>

		<fieldset>
			<legend>Nome</legend>
			<input id="nome" type="text" value="${vo.nome}" autofocus="autofocus" />
		</fieldset>

	</div>

	<div class="FormularioBotoes">
		<button title="Salvar (Ctrl + S)" class="BotaoSalvar">Salvar</button>
		<button title="Excluir (Ctrl + Del)" class="BotaoExcluir">Excluir</button>
		<button title="Voltar (Esc)" class="BotaoVoltar">Voltar</button>
	</div>
</aside>