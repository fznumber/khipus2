/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.controller;

import com.encens.khipus.util.GestorImpresion;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Diego
 */
@Named(value = "pedidosReportController")
@RequestScoped
public class PedidosReportController {

    /**
     * Creates a new instance of PedidosReportController
     */
    @EJB
    GestorImpresion gestorImpresion;
    public void imprimir(){
        String ruta = "/resources/reportes/";
    }
    public PedidosReportController() {
    }
    
}
