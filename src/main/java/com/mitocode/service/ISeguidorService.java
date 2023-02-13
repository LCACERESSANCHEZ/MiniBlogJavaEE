package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Persona;
import com.mitocode.model.PublicadorSeguidor;
import com.mitocode.util.ReporteSeguidor;

public interface ISeguidorService {
	
	Integer registrarPublicadoresSeguidores(List<Persona> seguidores, List<Persona> publicadores);
	List<PublicadorSeguidor> listarSeguidores(Persona per);
	Integer dejarSeguir(List<Persona> seguidores, List<Persona> publicadores);
	List<ReporteSeguidor> listarSeguidores();
	List<PublicadorSeguidor> listarSeguidos(Persona per);
}
