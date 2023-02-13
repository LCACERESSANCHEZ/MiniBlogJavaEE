package com.mitocode.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mitocode.model.Persona;
import com.mitocode.service.IPersonaService;

@WebServlet("/imagen/*")
public class ImageServlet extends HttpServlet{

	@Inject
	private IPersonaService service;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		try {
			String captura = req.getPathInfo().substring(1);  // /imagen/1
			if (captura != null && !captura.equalsIgnoreCase("")) {
				int id = Integer.parseInt(captura);
				Persona per = new Persona();
				per.setIdPersona(id);
				per = service.listarPorId(per);
				if(per.getFoto() != null) {
					resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
					resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
					resp.setDateHeader("Expires", 0); // Proxies.
					
					resp.setContentType(getServletContext().getMimeType("image/jpg"));
					resp.setContentLength(per.getFoto().length);
					resp.getOutputStream().write(per.getFoto());	
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
