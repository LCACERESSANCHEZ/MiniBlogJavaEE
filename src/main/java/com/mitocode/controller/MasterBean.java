package com.mitocode.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.mitocode.model.Usuario;

@Named
@ViewScoped
public class MasterBean implements Serializable{

	public void verificarSesion() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
			
			if(us == null ) {
				context.getExternalContext().redirect("./../index.xhtml");
			}
		}catch(Exception e) {
			context.getExternalContext().redirect("./../index.xhtml");
		}
	}
	
	public void cerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
}
