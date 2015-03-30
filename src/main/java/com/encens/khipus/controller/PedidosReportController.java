/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.controller;

import com.encens.khipus.ejb.DosificacionFacade;
import com.encens.khipus.model.*;
import com.encens.khipus.util.BarcodeRenderer;
import com.encens.khipus.util.FileCacheLoader;
import com.encens.khipus.util.GestorImpresion;
import com.encens.khipus.util.MoneyUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Inject
    GestorImpresion gestorImpresion;
    @EJB
    DosificacionFacade dosificacionFacade;
    @Inject
    PedidosController pedidosController;
    @Inject
    MovimientoController movimientoController;
    @Inject
    DosificacionController dosificacionController;
    @Inject
    ImpresionfacturaController impresionfacturaController;

    private MoneyUtil moneyUtil;
    private BarcodeRenderer barcodeRenderer;
    private Boolean imprimirCopia = false;
    private Dosificacion dosificacion;
    private Pedidos pedido;
    private String tipoEtiquetaFatura;

    public void imprimirNotaEntrega(Pedidos pedido) throws IOException, JRException {
        this.pedido = pedido;
        HashMap parameters = new HashMap();
        moneyUtil = new MoneyUtil();
        parameters.putAll(getReportParams(pedido));
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/notaDeEntrega.jasper"));
        exportarPDF(parameters, jasper);
    }

    private Map<String, Object> getReportParams(Pedidos pedido) {
        String nroDoc = pedido.getCliente().getNroDoc();
        if(pedido.getCliente().getTipocliente().equals("INSTITUCION"))
            nroDoc = pedido.getCliente().getNit();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("nroPedido",pedido.getCodigo().getSecuencia().toString());
        paramMap.put("nit",nroDoc);
        paramMap.put("fechaEntrega",pedido.getFechaEntrega());
        paramMap.put("nombreClienteyTerritorio",pedido.getCliente().getNombreCompleto()+"("+pedido.getCliente().getTerritoriotrabajo().getNombre()+")");
        paramMap.put("totalLiteral",moneyUtil.Convertir(pedido.getTotalimporte().toString(), true));
        paramMap.put("totalImporte",pedido.getTotalimporte());
        return paramMap;
    }

    private Map<String, Object> getReportParams(String nameClient,long numfac,String etiqueta,String codControl, String keyQR,Pedidos pedido) {

        String filePath = FileCacheLoader.i.getPath("/resources/reportes/qr_inv.png");
        String nroDoc = pedido.getCliente().getNroDoc();
        if(pedido.getCliente().getTipocliente().equals("INSTITUCION"))
            nroDoc = pedido.getCliente().getNit();
        //todo:completar los datos de la tabla
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("nitEmpresa","123456022");
        paramMap.put("numFac",numfac);
        paramMap.put("numAutorizacion",dosificacion.getNroautorizacion().intValue());
        paramMap.put("nitCliente",nroDoc);
        paramMap.put("fecha",pedido.getFechaEntrega());
        paramMap.put("nombreCliente",nameClient);//verificar el nombre del cliente
        paramMap.put("fechaLimite", dosificacion.getFechavencimiento());
        paramMap.put("codigoControl", codControl);
        paramMap.put("tipoEtiqueta",etiqueta);
        //verificar por que no requiere el codigo de control

        paramMap.put("llaveQR",keyQR);
        paramMap.put("totalLiteral",moneyUtil.Convertir(pedido.getTotalimporte().toString(), true));
        paramMap.put("total",pedido.getTotalimporte());
        barcodeRenderer.generateQR(keyQR,filePath);
        try {
            BufferedImage img = ImageIO.read(new File(filePath));
            //BufferedImage image = ImageIO.read(getClass().getResource(filePath));
            paramMap.put("imgQR",img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paramMap;
    }

    public void imprimirFactura(Pedidos pedido) throws IOException, JRException {
        this.pedido = pedido;
        HashMap parameters = new HashMap();
        moneyUtil = new MoneyUtil();
        barcodeRenderer = new BarcodeRenderer();
        String etiqueta;
        dosificacion = dosificacionFacade.findByPeriodo(new Date());
        BigInteger numberAuthorization = dosificacion.getNroautorizacion();
        String key = dosificacion.getLlave();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/factura.jasper"));
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
        exportarPDF(parameters,jasper);
    }

    public void guardarFactura(Pedidos pedido,String codControl){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        LoginBean loginBean = (LoginBean) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "loginBean");
        if(pedido.getMovimiento() == null)
        {
           Movimiento movimiento = new Movimiento();
            movimiento.setCodestruct(dosificacion.getEstCod());
            movimiento.setEstado("PENDIENTE");
            movimiento.setCodigocontrol(codControl);
            movimiento.setFecharegistro(new Date());
            //todo:virificar estos valores
            movimiento.setGlosa("");
            movimiento.setMoneda("BS");
            movimiento.setTipocambio(6.69);
            pedido.setMovimiento(movimiento);

            Impresionfactura impresionfactura = new Impresionfactura();
            impresionfactura.setUsuario(loginBean.getUsuario());
            impresionfactura.setFechaimpresion(new Date());
            impresionfactura.setMovimiento(movimiento);
            impresionfactura.setDosificacion(dosificacion);
            impresionfactura.setTipo("ORIGINAL");
            impresionfactura.setNroFactura(dosificacion.getNumeroactual());
            movimiento.getImpresionfacturaCollection().add(impresionfactura);
            movimientoController.setSelected(movimiento);
            movimientoController.create();
            dosificacion.getNumeroactual().add(BigInteger.ONE);
            dosificacionController.setSelected(dosificacion);
            dosificacionController.update();
        }else{
            Impresionfactura impresionfactura = new Impresionfactura();
            impresionfactura.setUsuario(loginBean.getUsuario());
            impresionfactura.setFechaimpresion(new Date());
            impresionfactura.setMovimiento(pedido.getMovimiento());
            impresionfactura.setDosificacion(dosificacion);
            impresionfactura.setTipo(tipoEtiquetaFatura);
            impresionfactura.setNroFactura(dosificacion.getNumeroactual());
            pedido.getMovimiento().getImpresionfacturaCollection().add(impresionfactura);
            movimientoController.setSelected(pedido.getMovimiento());
            movimientoController.update();
            dosificacion.getNumeroactual().add(BigInteger.ONE);
            dosificacionController.setSelected(dosificacion);
            dosificacionController.update();
        }
    }

    public void imprimirFactura(List<Pedidos> pedidosElegidos) throws IOException, JRException {
        HashMap parameters = new HashMap();
        moneyUtil = new MoneyUtil();
        barcodeRenderer = new BarcodeRenderer();
        dosificacion = dosificacionFacade.findByPeriodo(new Date());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/factura.jasper"));


        JasperPrint jasperPrint ;
        parameters.putAll(fijarParmetrosFactura(pedidosElegidos.get(0), jasper));
        try {
            jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, new JRBeanCollectionDataSource(pedidosElegidos.get(0).getArticulosPedidos()));
        } catch (JRException e) {
            e.printStackTrace();
            //todo:mensaje de error
            return;
        }

        for(int i= 1;i<pedidosElegidos.size();i++){
            parameters.putAll(fijarParmetrosFactura(pedidosElegidos.get(i),jasper));
            try {
                jasperPrint.getPages().addAll(JasperFillManager.fillReport(jasper.getPath(), parameters, new JRBeanCollectionDataSource(pedidosElegidos.get(i).getArticulosPedidos())).getPages());
            } catch (JRException e) {
                e.printStackTrace();
                //todo:mensaje de error
                return;
            }
        }

        for(Pedidos pedido:pedidosElegidos){
            if(pedido.getEstado().equals("PENDIENTE"))
            {
                pedido.setEstado("PREPARAR");
                pedidosController.setSelected(pedido);
                pedidosController.update();
            }
        }

        exportarPDF(jasperPrint);
    }

    public void imprimirNota(List<Pedidos> pedidosElegidos) throws IOException, JRException {
        HashMap parameters = new HashMap();
        moneyUtil = new MoneyUtil();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/notaDeEntrega.jasper"));


        JasperPrint jasperPrint ;
        parameters.putAll(getReportParams(pedidosElegidos.get(0)));
        try {
            jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, new JRBeanCollectionDataSource(pedidosElegidos.get(0).getArticulosPedidos()));
        } catch (JRException e) {
            e.printStackTrace();
            //todo:mensaje de error
            return;
        }

        for(int i= 1;i<pedidosElegidos.size();i++){
            parameters.putAll(getReportParams(pedidosElegidos.get(i)));
            try {
                jasperPrint.getPages().addAll(JasperFillManager.fillReport(jasper.getPath(), parameters, new JRBeanCollectionDataSource(pedidosElegidos.get(i).getArticulosPedidos())).getPages());
            } catch (JRException e) {
                e.printStackTrace();
                //todo:mensaje de error
                return;
            }
        }

        for(Pedidos pedido:pedidosElegidos){
            if(pedido.getEstado().equals("PENDIENTE"))
            {
                pedido.setEstado("PREPARAR");
                pedidosController.setSelected(pedido);
                pedidosController.update();
            }
        }

        exportarPDF(jasperPrint);
    }

    public Map<String, Object> fijarParmetrosFactura(Pedidos pedido,File jasper){
        String etiqueta;
        if(imprimirCopia)
            etiqueta = "COPIA";
        else
            etiqueta = "ORIGINAL";
        ControlCode controlCode = generateCodControl(pedido,dosificacion.getNumeroactual().intValue(),dosificacion.getNroautorizacion(),dosificacion.getLlave());

        return getReportParams(
                        pedido.getCliente().getNombreCompleto()
                        ,dosificacion.getNumeroactual().intValue()
                        ,etiqueta
                        ,controlCode.getCodigoControl()
                        ,controlCode.getKeyQR()
                        ,pedido);
    }

    public void exportarPDF(JasperPrint jasperPrint) throws IOException, JRException {


        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition","attachment; filename=jsfReporte.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void exportarPDF(HashMap parametros,File jasper) throws JRException, IOException {

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(pedido.getArticulosPedidos()));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition","attachment; filename=jsfReporte.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    private ControlCode generateCodControl(Pedidos pedido,Integer numberInvoice,BigInteger numberAutorization,String key)
    {
        Double importeBaseCreditFisical = pedido.getTotalimporte() * 0.13;
        String nroDoc = pedido.getCliente().getNroDoc();
        if(pedido.getCliente().getTipocliente().equals("INSTITUCION"))
            nroDoc = pedido.getCliente().getNit();

        ControlCode controlCode = new ControlCode("123456789012"
                ,numberInvoice
                ,numberAutorization.toString()
                ,pedido.getFechaEntrega()
                ,pedido.getTotalimporte()
                ,importeBaseCreditFisical
                ,nroDoc
        );
        moneyUtil.getLlaveQR(controlCode,key);
        controlCode.generarCodigoQR();
        return controlCode;
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

    public String getTipoEtiquetaFatura() {
        return tipoEtiquetaFatura;
    }

    public void setTipoEtiquetaFatura(String tipoEtiquetaFatura) {
        this.tipoEtiquetaFatura = tipoEtiquetaFatura;
    }
}
