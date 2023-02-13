package com.mitocode.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mitocode.model.Persona;
import com.mitocode.model.Publicacion;
import com.mitocode.model.Usuario;
import com.mitocode.service.IPublicacionService;

@Named
@ViewScoped
public class PublicacionBean implements Serializable {

	@Inject
	private IPublicacionService service;
	@Inject
	private PushBean push;

	private List<Publicacion> publicaciones;
	private Publicacion publicacion;
	private Usuario us;

	@PostConstruct
	public void init() {
		this.publicacion = new Publicacion();
		this.us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		this.listarPublicaciones();
	}

	public void publicar() {
		try {
			Persona p = new Persona();
			p.setIdPersona(this.us.getPersona().getIdPersona());

			this.publicacion.setPublicador(p);
			this.service.registrar(publicacion);
			push.sendMessage();
		} catch (Exception e) {

		}
	}
	
	public void listarPublicaciones() {
		try {
			this.publicaciones = this.service.listarPublicacionesPorPublicador(this.us.getPersona());
		}catch(Exception e) {
			
		}
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}

}
