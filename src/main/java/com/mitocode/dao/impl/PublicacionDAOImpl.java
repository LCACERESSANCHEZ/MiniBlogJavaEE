package com.mitocode.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.mitocode.dao.IPublicacionDAO;
import com.mitocode.model.Persona;
import com.mitocode.model.Publicacion;
import com.mitocode.model.PublicadorSeguidor;

@Stateless
public class PublicacionDAOImpl implements IPublicacionDAO, Serializable{

	@PersistenceContext(unitName = "blogPU")
	private EntityManager em;
	
	@Override
	public Integer registrar(Publicacion t) throws Exception {
		int rpta = 0;
		try {
			em.persist(t);
			em.flush();
			rpta = t.getId();
		}catch(Exception e) {
			rpta = 0;
		}
		return rpta;				
	}

	@Override
	public Integer modificar(Publicacion t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer eliminar(Publicacion t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Publicacion> listar() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Publicacion listarPorId(Publicacion t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Publicacion> listarPublicacionesPorPublicador(Persona publicador) throws Exception {
		List<Publicacion> lista = new ArrayList<>();
		try {
			Query query = em.createQuery("FROM Publicacion p where p.publicador.idPersona =?1");
			query.setParameter(1, publicador.getIdPersona());

			lista = (List<Publicacion>) query.getResultList();

		} catch (Exception e) {
			
		}
		return lista;
	}

	@Override
	public List<Publicacion> listarPublicacionesDeSeguidores(Persona per) {
		List<PublicadorSeguidor> listaSeguidores = new ArrayList<>();
		List<Publicacion> listaPublicaciones = new ArrayList<>();
		try {
			Query query = em.createQuery("FROM PublicadorSeguidor p where p.seguidor.idPersona =?1");
			query.setParameter(1, per.getIdPersona());

			listaSeguidores = (List<PublicadorSeguidor>) query.getResultList();
			
			listaSeguidores.forEach(s -> {
				Query q = em.createQuery("FROM Publicacion pu where pu.publicador.idPersona =?1");
				q.setParameter(1, s.getPublicador().getIdPersona());	
				List<Publicacion> lista = (List<Publicacion>)q.getResultList();
				listaPublicaciones.addAll(lista);
			});								

		} catch (Exception e) {
			
		}
		return listaPublicaciones;
	}

}
