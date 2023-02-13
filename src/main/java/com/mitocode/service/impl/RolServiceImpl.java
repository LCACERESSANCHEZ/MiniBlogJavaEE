package com.mitocode.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import com.mitocode.dao.IRolDAO;
import com.mitocode.model.Rol;
import com.mitocode.model.Usuario;
import com.mitocode.model.UsuarioRol;
import com.mitocode.service.IRolService;

@Named
public class RolServiceImpl implements IRolService, Serializable{

	@EJB
	private IRolDAO dao;
	
	@Override
	public Integer registrar(Rol t) throws Exception {
		return dao.registrar(t);
	}

	@Override
	public Integer modificar(Rol t) throws Exception {
		return dao.modificar(t);
	}

	@Override
	public Integer eliminar(Rol t) throws Exception {
		return dao.eliminar(t);
	}

	@Override
	public List<Rol> listar() throws Exception {
		return dao.listar();
	}

	@Override
	public Rol listarPorId(Rol t) throws Exception {
		return dao.listarPorId(t);
	}

	@Override
	public Integer asignar(Usuario us, List<Rol> roles) {		
		List<UsuarioRol> usuario_roles = new ArrayList<>();
		
		//llamar a un query en el dao que traiga el ultimo id
		
		roles.forEach(r -> {
			UsuarioRol ur = new UsuarioRol();
			//ur.setId(//llamando a ese id);
			ur.setUsuario(us);
			ur.setRol(r);
			usuario_roles.add(ur);
		});
		
		return dao.asignar(us, usuario_roles);
	}

}
