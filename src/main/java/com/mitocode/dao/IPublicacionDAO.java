package com.mitocode.dao;

import java.util.List;

import javax.ejb.Local;

import com.mitocode.model.Persona;
import com.mitocode.model.Publicacion;

@Local
public interface IPublicacionDAO extends ICRUD<Publicacion>{
	
	List<Publicacion> listarPublicacionesPorPublicador(Persona publicador) throws Exception;
	List<Publicacion> listarPublicacionesDeSeguidores(Persona per);

}
