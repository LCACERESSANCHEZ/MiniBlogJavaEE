package com.mitocode.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;

import com.mitocode.model.Persona;
import com.mitocode.model.Rol;
import com.mitocode.model.Usuario;
import com.mitocode.service.IPersonaService;
import com.mitocode.service.IRolService;

@Named
@ViewScoped
public class RegistroBean implements Serializable {

	@Inject
	private IPersonaService personaService;
	@Inject
	private IRolService rolService;
	
	private Persona persona;
	private Usuario usuario;

	@PostConstruct
	public void init() {
		this.persona = new Persona();
		this.usuario = new Usuario();
	}

	@Transactional
	public String registrar() {
		String redireccion = "";
		try {			
			String clave = this.usuario.getContrasena();
			String claveHash = BCrypt.hashpw(clave, BCrypt.gensalt());
			this.usuario.setContrasena(claveHash);
			
			this.persona.setUs(this.usuario);
			this.usuario.setPersona(this.persona);
			this.personaService.registrar(this.persona);
			
			List<Rol> roles = new ArrayList<>();
			Rol r = new Rol();
			r.setId(1);
			roles.add(r);
			
			rolService.asignar(this.usuario, roles);
			redireccion = "index?faces-redirect=true";
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return redireccion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
