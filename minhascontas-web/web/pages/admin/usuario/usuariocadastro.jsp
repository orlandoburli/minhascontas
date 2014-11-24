<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style type="text/css">
idUsuario {
	direction: rtl;
}

#nome {
	width: 500px !important;
}

#email {
	width: 500px !important;
}
</style>
<aside>
	<label class="TituloTela">Cadastro de Usu&aacute;rios</label>

	<div class="FormularioCadastro FormularioGeral"
		data-page-consulta="usuarioconsulta.admin"
		data-page-operacao="${operacao}"
		data-page-cadastro="usuariocadastro.admin">

		<fieldset>
			<legend>C&oacute;digo</legend>
			<input id="idUsuario" type="text" disabled="disabled"
				value="${vo.idUsuario }" />
		</fieldset>

		<fieldset>
			<legend>Nome</legend>
			<input id="nome" type="text" value="${vo.nome}" autofocus="autofocus" />
		</fieldset>

		<fieldset>
			<legend>Login</legend>
			<input id="login" type="text" value="${vo.login}" />
		</fieldset>

		<fieldset>
			<legend>Email</legend>
			<input id="email" type="text" value="${vo.email}" />
		</fieldset>

		<fieldset>
			<legend>Senha</legend>
			<input id="senha" type="text" value="${vo.senha}" type="password" />
		</fieldset>
		
		<fieldset>
			<legend>Perfil</legend>
			<select id="idPerfil" data-field-type="autocomplete"
				data-field-value="${vo.idPerfil}">
				<c:forEach items="${perfis}" var="perfil">
					<option value="${perfil.idPerfil}">${perfil.nome}</option>
				</c:forEach>				
			</select>
		</fieldset>

		<fieldset>
			<legend>Ativo</legend>
			<select id="ativo" data-field-type="autocomplete"
				data-field-value="${vo.ativo}">
				<option value="S">Sim</option>
				<option value="N">Não</option>
			</select>
		</fieldset>

	</div>

	<div class="FormularioBotoes">
		<button title="Salvar (Ctrl + S)" class="BotaoSalvar">Salvar</button>
		<button title="Excluir (Ctrl + Del)" class="BotaoExcluir">Excluir</button>
		<button title="Voltar (Esc)" class="BotaoVoltar">Voltar</button>
	</div>
</aside>