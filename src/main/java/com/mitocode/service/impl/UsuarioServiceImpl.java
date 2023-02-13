package com.mitocode.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.persistence.Query;

import org.mindrot.jbcrypt.BCrypt;

import com.mitocode.dao.IUsuarioDAO;
import com.mitocode.model.Persona;
import com.mitocode.model.Usuario;
import com.mitocode.service.IUsuarioService;

@Named
public class UsuarioServiceImpl implements IUsuarioService, Serializable {

	@EJB
	private IUsuarioDAO dao;

	@Override
	public Usuario login(Usuario us) {
		String clave = us.getContrasena();
		String claveHash = dao.traerPassHashed(us.getUsuario());

		try {
			if (BCrypt.checkpw(clave, claveHash)) {
				return dao.leerPorNombreUsuario(us.getUsuario());
			}
		} catch (Exception e) {
			throw e;
		}

		return new Usuario();
	}

	@Override
	public Integer registrar(Usuario t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer modificar(Usuario usu) throws Exception {		
		return dao.modificar(usu);
	}

	@Override
	public Integer eliminar(Usuario t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listar() throws Exception {
		return dao.listar();
	}
	
	@Override
	public List<Usuario> listarPorNombreUsuario(String usu) throws Exception {
		return dao.listarPorNombreUsuario(usu);
	}

	@Override
	public Usuario listarPorId(Usuario t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
