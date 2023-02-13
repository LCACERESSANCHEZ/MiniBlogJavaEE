package com.mitocode.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.mitocode.dao.IPersonaDAO;
import com.mitocode.model.Persona;

@Stateless //@Named
public class PersonaDAOImpl implements IPersonaDAO, Serializable{

	@PersistenceContext(unitName = "blogPU")
	private EntityManager em;
	//private EntityManagerFactory emf;
	//private EntityManager em;

	/*@PostConstruct
	public void init() {
		emf = Persistence.createEntityManagerFactory("blogPU");
		em = emf.createEntityManager();
	}*/

	
	@Override
	public Integer registrar(Persona per) throws Exception {	
		try {
			//em.getTransaction().begin();
			em.persist(per);
			//em.getTransaction().commit();	
		}catch(Exception e) {
			System.out.println(e.getMessage());
			//em.getTransaction().rollback();
		}
		
		return per.getIdPersona();
	}

	@Override
	public Integer modificar(Persona per) throws Exception {
		em.merge(per);
		return 1;
	}

	@Override
	public Integer eliminar(Persona per) throws Exception {
		return null;
	}

	@Override
	public List<Persona> listar() throws Exception {
		List<Persona> lista = new ArrayList<Persona>();

		try {			
			Query query = em.createQuery("SELECT p FROM Persona p");
			lista = (List<Persona>) query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}

	@Override
	public Persona listarPorId(Persona persona) throws Exception {
		Persona per = new Persona();
		List<Persona> lista = new ArrayList<>();
		try {
			Query query = em.createQuery("FROM Persona p where p.idPersona =?1");
			query.setParameter(1, persona.getIdPersona());

			lista = (List<Persona>) query.getResultList();

			if (lista != null && !lista.isEmpty()) {
				per = lista.get(0);
			}

		} catch (Exception e) {
			throw e;
		}
		return per;
	}

}
