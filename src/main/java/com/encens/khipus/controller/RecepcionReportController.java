/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.controller;

import com.encens.khipus.ejb.DosificacionFacade;
import com.encens.khipus.ejb.PedidosFacade;
import com.encens.khipus.model.*;
import com.encens.khipus.util.*;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;

/**
 *
 * @author Diego
 */
@Named(value = "recepcionReportController")
@SessionScoped
public class RecepcionReportController implements Serializable {

    /**
     * Creates a new instance of PedidosReportController
     */
    @EJB
    private PedidosFacade pedidosFacade;
    private Date fechaEntrega;
    private Territoriotrabajo territoriotrabajo;
    private Integer cantidadPedidos;


    public void imprimirReporte() throws IOException, JRException {

        HashMap parameters = new HashMap();

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/recepcionPedidos.jasper"));

        parameters.putAll(getReportParams());
        /*List<PedidosFacade.RecepcionPedido> resultado;
        if(fechaEntrega == null && territoriotrabajo == null)
        {
            resultado = pedidosFacade.recepcionDePedidosTodos();
        }else{
            resultado = pedidosFacade.recepcionDePedidosPorFechaTerritorio(fechaEntrega, territoriotrabajo);
        }
        cantidadPedidos = resultado.size();*/
        exportarPDF(parameters,jasper);
    }

    private Map<String, Object> getReportParams() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String fecha;
        if(fechaEntrega == null)
            fecha = "(TODOS LOS PENDIENTES)";
        else
            fecha = fechaEntrega.toString();

        paramMap.put("fecha",fecha);
        paramMap.put("cantidad",cantidadPedidos);

        return paramMap;
    }

    public void exportarPDF(HashMap parametros, File jasper) throws JRException, IOException {

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, createDataSource());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=RecepcionPedios.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    private JRDataSource createDataSource() {
        List<Object[]> resultado;
        if(fechaEntrega == null && territoriotrabajo == null)
        {
            resultado = pedidosFacade.recepcionDePedidos();
        }else{
            resultado = pedidosFacade.recepcionDePedidos(fechaEntrega,territoriotrabajo);
        }
        cantidadPedidos = pedidosFacade.getTotalPedidos(fechaEntrega,territoriotrabajo);

        DRDataSource dataSource = new DRDataSource("cliente", "producto", "cantidad", "distribuidor");
        for (Object[] obj : resultado) {
            dataSource.add((String)obj[0],(String)obj[1],(Integer)obj[2],(String)obj[3]);
        }
        return dataSource;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Territoriotrabajo getTerritoriotrabajo() {
        return territoriotrabajo;
    }

    public void setTerritoriotrabajo(Territoriotrabajo territoriotrabajo) {
        this.territoriotrabajo = territoriotrabajo;
    }
}
