package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Persona;
import com.mitocode.model.Publicacion;

public interface IPublicacionService extends IService<Publicacion> {

	List<Publicacion> listarPublicacionesPorPublicador(Persona publicador) throws Exception;
	List<Publicacion> listarPublicacionesDeSeguidores(Persona per);
}
