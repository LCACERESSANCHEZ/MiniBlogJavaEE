package com.mitocode.controller;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.chart.PieChartModel;

import com.mitocode.service.ISeguidorService;
import com.mitocode.util.ReporteSeguidor;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Named
@ViewScoped
public class ReporteBean implements Serializable {

	@Inject
	private ISeguidorService seguidorservice;

	private List<ReporteSeguidor> lista;
	private PieChartModel pieModel1;

	@PostConstruct
	public void init() {
		this.listarSeguidores();
		this.crearPieModel();
	}

	public void crearPieModel() {
		pieModel1 = new PieChartModel();

		this.lista.forEach(x -> {
			this.pieModel1.set(x.getPublicador(), x.getCantidad());
		});

		this.pieModel1.setTitle("Cantidad de Seguidores");
		this.pieModel1.setLegendPosition("w");
		this.pieModel1.setShowDataLabels(true);
	}

	public void listarSeguidores() {
		try {
			lista = seguidorservice.listarSeguidores();
		} catch (Exception e) {

		}
	}

	public void generarReporte() {
		try {
			Map<String, Object> parametros = new HashMap<String, Object>();
			//parametros.put("", ""); 
			
			File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/mini-blog.jasper"));
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(this.lista));
			
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			response.addHeader("Content-disposition", "attachment; filename=mini-blog.pdf");
			ServletOutputStream stream = response.getOutputStream();
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
			
			stream.flush();
			stream.close();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {

		}
	}
	
	public void verPDF() {
		try {

			File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reports/mini-blog.jasper"));

			byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), null, new JRBeanCollectionDataSource(this.lista));
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);

			ServletOutputStream outStream = response.getOutputStream();
			outStream.write(bytes, 0, bytes.length);
			outStream.flush();
			outStream.close();

			FacesContext.getCurrentInstance().responseComplete();

		} catch (Exception e) {

		}
	}

	public List<ReporteSeguidor> getLista() {
		return lista;
	}

	public void setLista(List<ReporteSeguidor> lista) {
		this.lista = lista;
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}

}
