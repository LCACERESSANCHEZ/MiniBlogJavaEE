package com.mitocode.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.mitocode.dao.IRolDAO;
import com.mitocode.model.Rol;
import com.mitocode.model.Usuario;
import com.mitocode.model.UsuarioRol;

@Stateless
public class RolDAOImpl implements IRolDAO, Serializable{
	
	@PersistenceContext(unitName = "blogPU")
	private EntityManager em;

	@Override
	public Integer registrar(Rol t) throws Exception {		
		em.persist(t);
		return t.getId();
	}

	@Override
	public Integer modificar(Rol t) throws Exception {
		em.merge(t);
		return t.getId();
	}

	@Override
	public Integer eliminar(Rol t) throws Exception {
		em.remove(em.merge(t));
		return 1;
	}

	@Override
	public List<Rol> listar() throws Exception {
		//JPQL Java Persistence Query Language
		Query q = em.createQuery("SELECT r FROM Rol r");
		List<Rol> lista = (List<Rol>) q.getResultList();
		return lista;
	}

	@Override
	public Rol listarPorId(Rol t) throws Exception {
		Query q = em.createQuery("FROM Rol r WHERE r.id = ?1");
		q.setParameter(1, t.getId());	
		List<Rol> lista = (List<Rol>) q.getResultList();
		
		return lista != null && !lista.isEmpty() ? lista.get(0) : new Rol();
	}

	@Override
	public Integer asignar(Usuario us, List<UsuarioRol> roles) {
		Query q = em.createNativeQuery("DELETE FROM usuario_rol ur where ur.id_usuario =?1");
		q.setParameter(1, us.getPersona().getIdPersona());
		q.executeUpdate();
		
		int[] i = { 0 };
		roles.forEach(r -> {
			em.persist(r);
			if(i[0] % 100 == 0) {
				em.flush();
				em.clear();
			}
			i[0]++;
		});
		
		return i[0];
	}

}
