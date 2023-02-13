package com.mitocode.dao;

import java.util.List;

import javax.ejb.Local;

import com.mitocode.model.Usuario;

@Local
public interface IUsuarioDAO extends ICRUD<Usuario>{

	String traerPassHashed(String us);
	Usuario leerPorNombreUsuario(String us);
	List<Usuario> listarPorNombreUsuario(String usu);
}
