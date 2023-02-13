package com.mitocode.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mitocode.model.Publicacion;
import com.mitocode.model.Usuario;
import com.mitocode.service.IPublicacionService;

@Named
@ViewScoped
public class PrincipalBean implements Serializable {

	@Inject
	private IPublicacionService service;
	private List<Publicacion> publicaciones;
	private Usuario us;

	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		this.us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
		this.listarPublicaciones();
	}

	public void listarPublicaciones() {
		try {
			this.publicaciones = this.service.listarPublicacionesDeSeguidores(us.getPersona());
		} catch (Exception e) {

		}
	}

	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}

}
