package com.mitocode.controller;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.mindrot.jbcrypt.BCrypt;

import com.mitocode.model.Persona;
import com.mitocode.model.Usuario;
import com.mitocode.service.IUsuarioService;

@Named
@ViewScoped
public class UsuarioBean implements Serializable{

	@Inject
	private IUsuarioService service;
	private Usuario usuario;
	private String nombreUsuario;
	private List<Usuario> lista;	
	private String tipoDialog;
	private String contraseniaActual;
	private boolean disableAceptar;
	private String txtVerificar;
	private String contraseniaNueva;
	
	@PostConstruct
	public void init() {
		this.listar();
		this.usuario = new Usuario();
	}
	
	public void listar() {
		try {
			this.lista = this.service.listar();
			lista.forEach(usu -> {
			    usu.setEstado(usu.getEstado().replaceAll("A", "Activo"));
			    usu.setEstado(usu.getEstado().replaceAll("I", "Inactivo"));
			});			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void listarPorUsuario() {
		try {
			this.lista = this.service.listarPorNombreUsuario(nombreUsuario);
			lista.forEach(usu -> {
			    usu.setEstado(usu.getEstado().replaceAll("A", "Activo"));
			    usu.setEstado(usu.getEstado().replaceAll("I", "Inactivo"));
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarData(Usuario usu) {
		this.usuario = usu;
		this.tipoDialog = "Modificando Usuario: " + usu.getUsuario();
		this.disableAceptar = true;
	}
	
	public void verificarContraseniaActual(Usuario usu) {
		boolean estado = false;
		
		if (BCrypt.checkpw(contraseniaActual, usu.getContrasena())) {
			estado = true;
		}
		
		if(estado == true) {
			setTxtVerificar("");
			this.disableAceptar = false;
		}else {
			txtVerificar = "Las contraseñas no coinciden";	
			this.disableAceptar = true;
			estado = false;
		}
		
	}
	
	public void modificarUsuario(Usuario usu) {	
		
		try {
			String clave = contraseniaNueva;
			String claveHash = BCrypt.hashpw(clave, BCrypt.gensalt());
			this.usuario = usu;
			this.usuario.setContrasena(claveHash);
			if(usuario.getEstado().equalsIgnoreCase("activo")) {
				this.usuario.setEstado("A");
			}else {
				this.usuario.setEstado("I");
			}			
			this.service.modificar(usuario);
			this.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public List<Usuario> getLista() {
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public String getTipoDialog() {
		return tipoDialog;
	}

	public void setTipoDialog(String tipoDialog) {
		this.tipoDialog = tipoDialog;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getContraseniaActual() {
		return contraseniaActual;
	}

	public void setContraseniaActual(String contraseniaActual) {
		this.contraseniaActual = contraseniaActual;
	}

	public boolean isDisableAceptar() {
		return disableAceptar;
	}

	public void setDisableAceptar(boolean disableAceptar) {
		this.disableAceptar = disableAceptar;
	}

	public String getTxtVerificar() {
		return txtVerificar;
	}

	public void setTxtVerificar(String txtVerificar) {
		this.txtVerificar = txtVerificar;
	}

	public String getContraseniaNueva() {
		return contraseniaNueva;
	}

	public void setContraseniaNueva(String contraseniaNueva) {
		this.contraseniaNueva = contraseniaNueva;
	}
		
}
