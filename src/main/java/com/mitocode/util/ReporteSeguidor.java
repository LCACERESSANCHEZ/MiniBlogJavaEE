package com.mitocode.util;

import java.io.Serializable;

public class ReporteSeguidor implements Serializable {

	private int cantidad;
	private String publicador;

	public ReporteSeguidor() {}
	
	public ReporteSeguidor(int cantidad, String publicador) {
		this.cantidad = cantidad;
		this.publicador = publicador;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getPublicador() {
		return publicador;
	}

	public void setPublicador(String publicador) {
		this.publicador = publicador;
	}

}
