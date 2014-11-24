package br.com.orlandoburli.minhascontas.model.be.acesso;

import java.util.List;

import br.com.orlandoburli.framework.core.be.BaseBe;
import br.com.orlandoburli.framework.core.be.exceptions.BeException;
import br.com.orlandoburli.framework.core.be.exceptions.persistence.ListException;
import br.com.orlandoburli.framework.core.be.validation.implementation.transformation.MD5Transformation;
import br.com.orlandoburli.framework.core.dao.DAOManager;
import br.com.orlandoburli.minhascontas.model.dao.acesso.UsuarioDao;
import br.com.orlandoburli.minhascontas.model.exceptions.EmailDuplicadoException;
import br.com.orlandoburli.minhascontas.model.exceptions.LoginInvalidoException;
import br.com.orlandoburli.minhascontas.model.vo.acesso.UsuarioVo;

public class UsuarioBe extends BaseBe<UsuarioVo, UsuarioDao> {

	public UsuarioBe(DAOManager manager) {
		super(manager);
	}

	@Override
	public void doBeforeInsert(UsuarioVo vo) throws BeException {
		super.doBeforeInsert(vo);

		vo.setSenha(MD5Transformation.md5(vo.getSenha()));
	}

	@Override
	public void doBeforeSave(UsuarioVo vo) throws BeException {
		super.doBeforeSave(vo);

		// Valida email duplicado
		UsuarioVo usuario2 = getByEmail(vo.getEmail());

		if (usuario2 != null) {
			if (vo.isNew()) {
				throw new EmailDuplicadoException("Esse email já existe cadastrado em outro usuário!", "email");
			} else {
				if (!vo.getIdUsuario().equals(usuario2.getIdUsuario())) {
					throw new EmailDuplicadoException("Esse email já existe cadastrado em outro usuário!", "email");
				}
			}
		}
	}

	public UsuarioVo login(String email, String senha, boolean senhaMD5) throws LoginInvalidoException, ListException {
		UsuarioVo usuario = getByEmail(email);

		if (usuario == null) {
			throw new LoginInvalidoException("Usuário / Senha inválidos!", "login");
		}

		String md5 = null;
		if (!senhaMD5) {
			md5 = MD5Transformation.md5(senha);
		} else {
			md5 = senha;
		}

		if (usuario.getEmail().equalsIgnoreCase(email) && usuario.getSenha().equals(md5)) {
			return usuario;
		}

		throw new LoginInvalidoException("Email / Senha inválidos!", "login");
	}

	public UsuarioVo login(String email, String senha) throws LoginInvalidoException, ListException {
		return login(email, senha, false);
	}

	public UsuarioVo getByEmail(String email) throws ListException {

		UsuarioVo filter = new UsuarioVo();
		filter.setEmail(email);

		List<UsuarioVo> list = getList(filter);

		if (list.size() == 1) {
			return list.get(0);
		}

		return null;
	}

}
