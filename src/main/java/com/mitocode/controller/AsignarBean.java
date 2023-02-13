package com.mitocode.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import com.mitocode.model.Persona;
import com.mitocode.model.Rol;
import com.mitocode.model.Usuario;
import com.mitocode.service.IPersonaService;
import com.mitocode.service.IRolService;

@Named
@ViewScoped
public class AsignarBean implements Serializable {

	@Inject
	private IPersonaService personaService;
	@Inject
	private IRolService rolService;

	private List<Persona> personas;
	private Persona persona;
	private DualListModel<Rol> dual;

	@PostConstruct
	public void init() {
		this.listarPersonas();
		this.listarRoles();
	}

	public void listarPersonas() {
		try {
			this.personas = this.personaService.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listarRoles() {
		try {
			List<Rol> roles = rolService.listar();
			this.dual = new DualListModel<Rol>(roles, new ArrayList<Rol>());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void asignar() {
		try {
			Usuario us = new Usuario();
			us.setId(this.persona.getIdPersona());
			us.setPersona(this.persona);
			
			rolService.asignar(us, this.dual.getTarget());
		}catch(Exception e) {
			
		}
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public DualListModel<Rol> getDual() {
		return dual;
	}

	public void setDual(DualListModel<Rol> dual) {
		this.dual = dual;
	}

}
