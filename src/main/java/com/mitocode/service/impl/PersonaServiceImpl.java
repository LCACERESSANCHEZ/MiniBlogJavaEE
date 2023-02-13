package com.mitocode.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import com.mitocode.dao.IPersonaDAO;
import com.mitocode.model.Persona;
import com.mitocode.service.IPersonaService;

@Named
public class PersonaServiceImpl implements IPersonaService, Serializable{

	@EJB //@Inject
	private IPersonaDAO dao;
	
	/*public PersonaServiceImpl() {
		dao = new PersonaDAOImpl();
	}*/
	
	@Override
	public Integer registrar(Persona per) throws Exception {
		return dao.registrar(per);
	}

	@Override
	public Integer modificar(Persona per) throws Exception {
		return dao.modificar(per);
	}

	@Override
	public Integer eliminar(Persona per) throws Exception {		
		return dao.eliminar(per);
	}

	@Override
	public List<Persona> listar() throws Exception {
		return dao.listar();
	}

	@Override
	public Persona listarPorId(Persona per) throws Exception {
		return dao.listarPorId(per);
	}

}
