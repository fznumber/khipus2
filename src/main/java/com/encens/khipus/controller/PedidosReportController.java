/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.controller;

import com.encens.khipus.ejb.DosificacionFacade;
import com.encens.khipus.model.*;
import com.encens.khipus.util.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
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
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author Diego
 */
@Named(value = "pedidosReportController")
@SessionScoped
public class PedidosReportController implements Serializable {

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
    private Dosificacion dosificacion;
    private Pedidos pedido;
    private List<Pedidos> pedidosElegidos = new ArrayList<>();
    private String tipoEtiquetaFactura = "ORIGINAL";
    private Boolean esCopia = false;

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
        if (pedido.getCliente().getTipocliente().equals("INSTITUCION")) {
            nroDoc = pedido.getCliente().getNit();
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("nroPedido", pedido.getCodigo().getSecuencia().toString());
        paramMap.put("nit", nroDoc);
        paramMap.put("fechaEntrega", pedido.getFechaEntrega());
        paramMap.put("nombreClienteyTerritorio", pedido.getCliente().getNombreCompleto() + "(" + pedido.getCliente().getTerritoriotrabajo().getNombre() + ")");
        paramMap.put("totalLiteral", moneyUtil.Convertir(pedido.getTotalimporte().toString(), true));
        paramMap.put("totalImporte", pedido.getTotalimporte());
        return paramMap;
    }

    private Map<String, Object> getReportParams(String nameClient, long numfac, String etiqueta, String codControl, String keyQR, Pedidos pedido) {

        String filePath = FileCacheLoader.i.getPath("/resources/reportes/qr_inv.png");
        String nroDoc = pedido.getCliente().getNroDoc();
        if (pedido.getCliente().getTipocliente().equals("INSTITUCION")) {
            nroDoc = pedido.getCliente().getNit();
        }
        //todo:completar los datos de la tabla
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("nitEmpresa", "123456022");
        paramMap.put("numFac", numfac);
        paramMap.put("numAutorizacion", dosificacion.getNroautorizacion().intValue());
        paramMap.put("nitCliente", nroDoc);
        paramMap.put("fecha", pedido.getFechaEntrega());
        paramMap.put("nombreCliente", nameClient);//verificar el nombre del cliente
        paramMap.put("fechaLimite", dosificacion.getFechavencimiento());
        paramMap.put("codigoControl", codControl);
        paramMap.put("tipoEtiqueta", etiqueta);
        //verificar por que no requiere el codigo de control

        paramMap.put("llaveQR", keyQR);
        paramMap.put("totalLiteral", moneyUtil.Convertir(pedido.getTotalimporte().toString(), true));
        paramMap.put("total", pedido.getTotalimporte());
        barcodeRenderer.generateQR(keyQR, filePath);
        try {
            BufferedImage img = ImageIO.read(new File(filePath));
            //BufferedImage image = ImageIO.read(getClass().getResource(filePath));
            paramMap.put("imgQR", img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paramMap;
    }

    public void imprimirFactura() throws IOException, JRException {
        if(pedido == null)
        {
            JSFUtil.addWarningMessage("No hay ningun pedido elegido.");
            return;
        }

        HashMap parameters = new HashMap();
        moneyUtil = new MoneyUtil();
        barcodeRenderer = new BarcodeRenderer();
        dosificacion = dosificacionFacade.findByPeriodo(new Date());
        BigInteger numberAuthorization = dosificacion.getNroautorizacion();
        String key = dosificacion.getLlave();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/factura.jasper"));        

        ControlCode controlCode = generateCodControl(pedido, dosificacion.getNumeroactual().intValue(), numberAuthorization, key);
        parameters.putAll(
                getReportParams(
                        pedido.getCliente().getNombreCompleto(), dosificacion.getNumeroactual().intValue(), tipoEtiquetaFactura, controlCode.getCodigoControl(), controlCode.getKeyQR(), pedido));
        guardarFactura(pedido, controlCode.getCodigoControl());
        exportarPDF(parameters, jasper);
    }

    public void cerrar() {
        pedido = null;
    }

    public void guardarFactura(Pedidos pedido, String codControl) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        LoginBean loginBean = (LoginBean) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "loginBean");
        if (pedido.getMovimiento() == null) {
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
            /*movimientoController.setSelected(movimiento);
             movimientoController.create();*/
            pedido.setMovimiento(movimiento);
            if(pedido.getEstado().equals("PENDIENTE"))
            pedido.setEstado("PREPARAR");
            pedidosController.setItems(null);
            pedidosController.setSelected(pedido);
            pedidosController.generalUpdate();
            dosificacion.setNumeroactual(dosificacion.getNumeroactual().intValue() + 1);
            dosificacionController.setSelected(dosificacion);
            dosificacionController.update();
        } else {
            Impresionfactura impresionfactura = new Impresionfactura();
            impresionfactura.setUsuario(loginBean.getUsuario());
            impresionfactura.setFechaimpresion(new Date());
            impresionfactura.setMovimiento(pedido.getMovimiento());
            impresionfactura.setDosificacion(dosificacion);
            impresionfactura.setTipo(tipoEtiquetaFactura);
            impresionfactura.setNroFactura(dosificacion.getNumeroactual());
            pedido.getMovimiento().getImpresionfacturaCollection().add(impresionfactura);
            /*movimientoController.setSelected(pedido.getMovimiento());
            movimientoController.update();*/
            if(pedido.getEstado().equals("PENDIENTE"))
                pedido.setEstado("PREPARAR");
            pedidosController.setSelected(pedido);
            pedidosController.setItems(null);
            pedidosController.generalUpdate();
            dosificacion.setNumeroactual(dosificacion.getNumeroactual().intValue() + 1);
            dosificacionController.setSelected(dosificacion);
            dosificacionController.update();
        }
    }

    public void imprimirFacturas() throws IOException, JRException {
        if(pedidosElegidos.size() == 0)
        {
            JSFUtil.addWarningMessage("No hay ningun pedido pedido elegido.");
            return;
        }
        HashMap parameters = new HashMap();
        moneyUtil = new MoneyUtil();
        barcodeRenderer = new BarcodeRenderer();
        //todo: lanzar un exception en caso que no encuentre una dosificacion valida
        dosificacion = dosificacionFacade.findByPeriodo(new Date());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/factura.jasper"));
        quitarSinFactura();
        JasperPrint jasperPrint;
        parameters.putAll(fijarParmetrosFactura(pedidosElegidos.get(0)));
        try {
            jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, new JRBeanCollectionDataSource(pedidosElegidos.get(0).getArticulosPedidos()));
        } catch (JRException e) {
            e.printStackTrace();
            //todo:mensaje de error
            return;
        }

        for (int i = 1; i < pedidosElegidos.size(); i++) {
            parameters.putAll(fijarParmetrosFactura(pedidosElegidos.get(i)));
            try {
                jasperPrint.getPages().addAll(JasperFillManager.fillReport(jasper.getPath()
                        , parameters
                        , new JRBeanCollectionDataSource(pedidosElegidos.get(i).getArticulosPedidos())).getPages());
            } catch (JRException e) {
                e.printStackTrace();
                //todo:mensaje de error
                return;
            }
        }
        exportarPDF(jasperPrint);
    }

    private void quitarSinFactura() {
        pedidosElegidos = pedidosElegidos.stream().filter(p->p.getCliente().getConfactura() == true).collect(Collectors.toList());
    }

    public void imprimirNota(List<Pedidos> pedidosElegidos) throws IOException, JRException {
        HashMap parameters = new HashMap();
        moneyUtil = new MoneyUtil();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/notaDeEntrega.jasper"));

        JasperPrint jasperPrint;
        parameters.putAll(getReportParams(pedidosElegidos.get(0)));
        try {
            jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, new JRBeanCollectionDataSource(pedidosElegidos.get(0).getArticulosPedidos()));
        } catch (JRException e) {
            e.printStackTrace();
            //todo:mensaje de error
            return;
        }

        for (int i = 1; i < pedidosElegidos.size(); i++) {
            parameters.putAll(getReportParams(pedidosElegidos.get(i)));
            try {
                jasperPrint.getPages().addAll(JasperFillManager.fillReport(jasper.getPath(), parameters, new JRBeanCollectionDataSource(pedidosElegidos.get(i).getArticulosPedidos())).getPages());
            } catch (JRException e) {
                e.printStackTrace();
                //todo:mensaje de error
                return;
            }
        }

        for (Pedidos pedido : pedidosElegidos) {
            if (pedido.getEstado().equals("PENDIENTE")) {
                pedido.setEstado("PREPARAR");
                pedidosController.setSelected(pedido);
                pedidosController.update();
            }
        }

        exportarPDF(jasperPrint);
    }
    
    public Map<String, Object> fijarParmetrosFactura(Pedidos pedido) {
        int numeroActual = dosificacion.getNumeroactual().intValue();
        ControlCode controlCode = generateCodControl(pedido, numeroActual, dosificacion.getNroautorizacion(), dosificacion.getLlave());
        if (pedido.getEstado().equals("PENDIENTE")) {
            pedido.setEstado("PREPARAR");
        }
        guardarFactura(pedido, controlCode.getCodigoControl());
        return getReportParams(
                pedido.getCliente().getNombreCompleto(), numeroActual, tipoEtiquetaFactura, controlCode.getCodigoControl(), controlCode.getKeyQR(), pedido);
    }

    public void exportarPDF(JasperPrint jasperPrint) throws IOException, JRException {

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=DOCUMENTO_ILVA.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void exportarPDF(HashMap parametros, File jasper) throws JRException, IOException {

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(pedido.getArticulosPedidos()));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=jsfReporte.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    private ControlCode generateCodControl(Pedidos pedido, Integer numberInvoice, BigInteger numberAutorization, String key) {
        Double importeBaseCreditFisical = pedido.getTotalimporte() * 0.13;
        String nroDoc = pedido.getCliente().getNroDoc();
        if (pedido.getCliente().getTipocliente().equals("INSTITUCION")) {
            nroDoc = pedido.getCliente().getNit();
        }

        ControlCode controlCode = new ControlCode("123456789012", numberInvoice, numberAutorization.toString(), pedido.getFechaEntrega(), pedido.getTotalimporte(), importeBaseCreditFisical, nroDoc
        );
        moneyUtil.getLlaveQR(controlCode, key);
        controlCode.generarCodigoQR();
        return controlCode;
    }

    public String getTipoEtiquetaFactura() {
        return tipoEtiquetaFactura;
    }

    public void setTipoEtiquetaFactura(String tipoEtiquetaFactura) {
        this.tipoEtiquetaFactura = tipoEtiquetaFactura;
    }

    public Boolean getEsCopia() {
        return esCopia;
    }

    public void setEsCopia(Boolean esCopia) {
        this.esCopia = esCopia;
    }

    public Pedidos getPedido() {
        return pedido;
    }

    public void setPedido(Pedidos pedido) {
        this.pedido = pedido;
    }

    public List<Pedidos> getPedidosElegidos() {
        return pedidosElegidos;
    }

    public void setPedidosElegidos(List<Pedidos> pedidosElegidos) {
        this.pedidosElegidos = pedidosElegidos;
    }
}
