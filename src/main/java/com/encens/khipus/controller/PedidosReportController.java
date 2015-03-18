/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.controller;

import com.encens.khipus.ejb.DosificacionFacade;
import com.encens.khipus.model.*;
import com.encens.khipus.util.BarcodeRenderer;
import com.encens.khipus.util.GestorImpresion;
import com.encens.khipus.util.MoneyUtil;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

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
    @EJB
    DosificacionFacade dosificacionFacade;

    private MoneyUtil moneyUtil;
    private BarcodeRenderer barcodeRenderer;
    private Boolean imprimirCopia = false;
    private Dosificacion dosificacion;

    public void imprimir(Pedidos pedido){
        String ruta = "/resources/reportes/pedidoFactura.jrxml";
        HashMap parameters = new HashMap();
        moneyUtil = new MoneyUtil();
        barcodeRenderer = new BarcodeRenderer();
        String etiqueta;
        dosificacion = dosificacionFacade.findByPeriodo(new Date());
        BigInteger numberAuthorization = dosificacion.getNroautorizacion();
        String key = dosificacion.getLlave();

        if(imprimirCopia)
            etiqueta = "COPIA";
        else
            etiqueta = "ORIGINAL";

        ControlCode controlCode = generateCodControl(pedido,dosificacion.getNumeroactual().intValue(),numberAuthorization,key);
        parameters.putAll(
                getReportParams(
                        pedido.getCliente().getNombreCompleto()
                        ,dosificacion.getNumeroactual().intValue()
                        ,etiqueta
                        ,controlCode.getCodigoControl()
                        ,controlCode.getKeyQR()
                        ,pedido));

        gestorImpresion.imprimir(new ArrayList<>(pedido.getArticulosPedidos()), ruta, parameters);

    }

    private ControlCode generateCodControl(Pedidos pedido,Integer numberInvoice,BigInteger numberAutorization,String key)
    {
        Double importeBaseCreditFisical = pedido.getTotalImporte() * 0.13;
        String nroDoc = pedido.getCliente().getNroDoc();
        if(pedido.getCliente().getTipocliente().equals("INSTITUCION"))
            nroDoc = pedido.getCliente().getNit();

        ControlCode controlCode = new ControlCode("123456789012"
                ,numberInvoice
                ,numberAutorization.toString()
                ,pedido.getFechaEntrega()
                ,pedido.getTotalImporte()
                ,importeBaseCreditFisical
                ,nroDoc
        );
        moneyUtil.getLlaveQR(controlCode,key);
        controlCode.generarCodigoQR();
        return controlCode;
    }

    private Map<String, Object> getReportParams(String nameClient,long numfac,String etiqueta,String codControl, String keyQR,Pedidos pedido) {

        String filePath = "qr_inv.png";
        String nroDoc = pedido.getCliente().getNroDoc();
        if(pedido.getCliente().getTipocliente().equals("INSTITUCION"))
            nroDoc = pedido.getCliente().getNit();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("nitEmpresa","123456022");
        paramMap.put("numFac",numfac);
        paramMap.put("numAutorizacion",dosificacion.getNroautorizacion());
        paramMap.put("nitCliente",nroDoc);
        paramMap.put("fecha",pedido.getFechaEntrega());
        paramMap.put("nombreCliente",nameClient);//verificar el nombre del cliente
        paramMap.put("fechaLimite",dosificacion.getFechavencimiento());
        paramMap.put("codigoControl",codControl);
        paramMap.put("tipoEtiqueta",etiqueta);
        //verificar por que no requiere el codigo de control

        paramMap.put("llaveQR",keyQR);
        paramMap.put("totalLiteral",moneyUtil.Convertir(pedido.getTotalImporte().toString(), true));
        paramMap.put("total",pedido.getTotalImporte());
        barcodeRenderer.generateQR(keyQR,filePath);
        return paramMap;
    }

    private  void createImpresionFactura(Pedidos pedidos, Dosificacion dosificacion, long numeroFactura, Usuario currentUser,Movimiento movimiento) {
        Impresionfactura impresionfactura = new Impresionfactura();
        impresionfactura.setDosificacion(dosificacion);
        impresionfactura.setMovimiento(movimiento);
        impresionfactura.setFechaimpresion(new Date());
        impresionfactura.setUsuario(currentUser);
        //rePrintsNews.add(rePrints);
       /* try {
            rePrintsService.create(rePrints);
        } catch (EntryDuplicatedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }*/
    }

    public Boolean getImprimirCopia() {
        return imprimirCopia;
    }

    public void setImprimirCopia(Boolean imprimirCopia) {
        this.imprimirCopia = imprimirCopia;
    }
}
