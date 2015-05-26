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
import org.apache.commons.lang.StringUtils;

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
import java.text.*;
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
    VentadirectaController ventadirectaController;
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
    private Ventadirecta ventadirecta;
    private List<Pedidos> pedidosElegidos = new ArrayList<>();
    private List<Ventadirecta> ventaDirectaElegidos = new ArrayList<>();
    private String tipoEtiquetaFactura = "ORIGINAL";
    private Boolean esCopia = false;

    public void imprimirNotaEntrega(Pedidos pedido) throws IOException, JRException {
        if(pedido.getEstado().equals("ANULADO"))
        {
            return;
        }
        this.pedido = pedido;
        HashMap parameters = new HashMap();
        moneyUtil = new MoneyUtil();
        parameters.putAll(getReportParams(pedido));
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/notaDeEntrega.jasper"));
        exportarPDF(parameters, jasper);
        pedido.setEstado("PREPARAR");
        pedidosController.setSelected(pedido);
        pedidosController.generalUpdate();
        pedidosController.setItems(null);
    }

    public void imprimirNotaEntrega(Ventadirecta pedido) throws IOException, JRException {
        if(pedido.getEstado().equals("ANULADO"))
        {
            return;
        }
        this.ventadirecta = pedido;
        HashMap parameters = new HashMap();
        moneyUtil = new MoneyUtil();
        parameters.putAll(getReportParams(ventadirecta));
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/notaDeEntrega.jasper"));
        exportarPDF(parameters, jasper);
        pedido.setEstado("PREPARAR");
        ventadirectaController.setSelected(ventadirecta);
        ventadirectaController.update();
        ventadirectaController.setItems(null);
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
        paramMap.put("REPORT_LOCALE", new java.util.Locale("en", "US"));
        return paramMap;
    }

    private Map<String, Object> getReportParams(Ventadirecta ventadirecta) {
        String nroDoc = ventadirecta.getCliente().getNroDoc();
        if (ventadirecta.getCliente().getTipocliente().equals("INSTITUCION")) {
            nroDoc = ventadirecta.getCliente().getNit();
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("nroPedido", ventadirecta.getCodigo().getSecuencia().toString());
        paramMap.put("nit", nroDoc);
        paramMap.put("fechaEntrega", ventadirecta.getFechaPedido());
        paramMap.put("nombreClienteyTerritorio", ventadirecta.getCliente().getNombreCompleto() + "(" + ventadirecta.getCliente().getTerritoriotrabajo().getNombre() + ")");
        paramMap.put("totalLiteral", moneyUtil.Convertir(ventadirecta.getTotalimporte().toString(), true));
        paramMap.put("totalImporte", ventadirecta.getTotalimporte());
        paramMap.put("REPORT_LOCALE", new java.util.Locale("en", "US"));
        return paramMap;
    }

    private Map<String, Object> getReportParams(String nameClient, long numfac, String etiqueta, String codControl, String keyQR, Pedidos pedido) {

        String filePath = FileCacheLoader.i.getPath("/resources/reportes/qr_inv.png");
        String nroDoc = pedido.getCliente().getNroDoc();
        DateUtil dateUtil = new DateUtil();
        if(StringUtils.isNotEmpty(pedido.getCliente().getNit()))
        {
            nroDoc = pedido.getCliente().getNit();
        }
        //todo:completar los datos de la tabla
        Calendar cal = Calendar.getInstance();
        cal.setTime(pedido.getFechaEntrega());
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        if(pedido.getMovimiento() == null)
        etiqueta = "ORIGINAL";

        String fecha = "Cochambamba, "+dia+" de "+dateUtil.getMes(mes)+" de "+anio;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("nitEmpresa", dosificacion.getNitEmpresa());
        paramMap.put("numFac", numfac);
        paramMap.put("numAutorizacion", dosificacion.getNroautorizacion().intValue());
        paramMap.put("nitCliente", nroDoc);
        paramMap.put("fecha", fecha);
        paramMap.put("nombreCliente", nameClient);
        paramMap.put("fechaLimite", dosificacion.getFechavencimiento());
        paramMap.put("codigoControl", codControl);
        paramMap.put("tipoEtiqueta", etiqueta);
        paramMap.put("etiquetaEmpresa",dosificacion.getEtiquetaEmpresa());
        //verificar por que no requiere el codigo de control
        paramMap.put("llaveQR", keyQR);
        paramMap.put("totalLiteral", moneyUtil.Convertir(pedido.getTotalimporte().toString(), true));
        paramMap.put("total", pedido.getTotalimporte());
        paramMap.put("REPORT_LOCALE", new java.util.Locale("en", "US"));
        barcodeRenderer.generateQR(keyQR, filePath);
        try {
            BufferedImage img = ImageIO.read(new File(filePath));
            paramMap.put("imgQR", img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paramMap;
    }

    private Map<String, Object> getReportParams(String nameClient, long numfac, String etiqueta, String codControl, String keyQR, Ventadirecta venta) {

        String filePath = FileCacheLoader.i.getPath("/resources/reportes/qr_inv.png");
        String nroDoc = venta.getCliente().getNroDoc();
        DateUtil dateUtil = new DateUtil();
        if(StringUtils.isNotEmpty(venta.getCliente().getNit()))
        {
            nroDoc = venta.getCliente().getNit();
        }
        //todo:completar los datos de la tabla
        Calendar cal = Calendar.getInstance();
        cal.setTime(venta.getFechaPedido());
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        if(venta.getMovimiento() == null)
            etiqueta = "ORIGINAL";

        String fecha = "Cochambamba, "+dia+" de "+dateUtil.getMes(mes)+" de "+anio;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("nitEmpresa", dosificacion.getNitEmpresa());
        paramMap.put("numFac", numfac);
        paramMap.put("numAutorizacion", dosificacion.getNroautorizacion().intValue());
        paramMap.put("nitCliente", nroDoc);
        paramMap.put("fecha", fecha);
        paramMap.put("nombreCliente", nameClient);
        paramMap.put("fechaLimite", dosificacion.getFechavencimiento());
        paramMap.put("codigoControl", codControl);
        paramMap.put("tipoEtiqueta", etiqueta);
        paramMap.put("etiquetaEmpresa",dosificacion.getEtiquetaEmpresa());
        //verificar por que no requiere el codigo de control
        paramMap.put("llaveQR", keyQR);
        paramMap.put("totalLiteral", moneyUtil.Convertir(venta.getTotalimporte().toString(), true));
        paramMap.put("total", venta.getTotalimporte());
        paramMap.put("REPORT_LOCALE", new java.util.Locale("en", "US"));
        barcodeRenderer.generateQR(keyQR, filePath);
        try {
            BufferedImage img = ImageIO.read(new File(filePath));
            paramMap.put("imgQR", img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paramMap;
    }

    public void imprimirFactura() throws IOException, JRException {
        dosificacion = dosificacionFacade.findByPeriodo(new Date());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if(dosificacion.getFechaControl().compareTo(pedido.getFechaEntrega()) != 0)
        {
            JSFUtil.addWarningMessage("No se puede generar la factura, por que la fecha de entrega " + df.format(pedido.getFechaEntrega()) + "es diferente a " + df.format(dosificacion.getFechaControl()));
            return;
        }
        if(pedido == null)
        {
            JSFUtil.addWarningMessage("No hay ningun pedido elegido.");
            return;
        }

        if(pedido.getEstado().equals("ANULADO"))
        {
            JSFUtil.addWarningMessage("El pedido fué anulado.");
            return;
        }

        HashMap parameters = new HashMap();
        moneyUtil = new MoneyUtil();
        barcodeRenderer = new BarcodeRenderer();
        dosificacion = dosificacionFacade.findByPeriodo(new Date());
        BigInteger numberAuthorization = dosificacion.getNroautorizacion();
        String key = dosificacion.getLlave();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/factura.jasper"));        
        Integer numeroFactura;
        if (pedido.getMovimiento() == null) {
            numeroFactura = Integer.parseInt(dosificacionFacade.getSiguienteNumeroFactura());
            dosificacion.setNumeroactual(numeroFactura);
        }else{
            numeroFactura = pedido.getMovimiento().getNrofactura();
        }

        
        ControlCode controlCode = generateCodControl(pedido, numeroFactura, numberAuthorization, key,dosificacion.getNitEmpresa());
        parameters.putAll(
                getReportParams(
                        pedido.getCliente().getNombreCompleto(), numeroFactura, tipoEtiquetaFactura, controlCode.getCodigoControl(), controlCode.getKeyQR(), pedido));
        guardarFactura(pedido, controlCode.getCodigoControl());
        exportarPDF(parameters, jasper);
    }

    public void imprimirFacturaVentaDirecta() throws IOException, JRException {
        dosificacion = dosificacionFacade.findByPeriodo(new Date());
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if(dosificacion.getFechaControl().compareTo(ventadirecta.getFechaPedido()) != 0)
        {
            JSFUtil.addWarningMessage("No se puede generar la factura, por que la fecha de entrega " + df.format(ventadirecta.getFechaPedido()) + "es diferente a " + df.format(dosificacion.getFechaControl()));
            return;
        }
        if(ventadirecta == null)
        {
            JSFUtil.addWarningMessage("No hay ninguna venta elegida.");
            return;
        }

        if(ventadirecta.getEstado().equals("ANULADO"))
        {
            JSFUtil.addWarningMessage("La venta fué anulado.");
            return;
        }

        HashMap parameters = new HashMap();
        moneyUtil = new MoneyUtil();
        barcodeRenderer = new BarcodeRenderer();
        dosificacion = dosificacionFacade.findByPeriodo(new Date());
        BigInteger numberAuthorization = dosificacion.getNroautorizacion();
        String key = dosificacion.getLlave();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/factura.jasper"));
        Integer numeroFactura;
        if (ventadirecta.getMovimiento() == null) {
            numeroFactura = Integer.parseInt(dosificacionFacade.getSiguienteNumeroFactura());
            dosificacion.setNumeroactual(numeroFactura);
        }else{
            numeroFactura = ventadirecta.getMovimiento().getNrofactura();
        }


        ControlCode controlCode = generateCodControl(ventadirecta, numeroFactura, numberAuthorization, key,dosificacion.getNitEmpresa());
        parameters.putAll(
                getReportParams(
                        ventadirecta.getCliente().getNombreCompleto(), numeroFactura, tipoEtiquetaFactura, controlCode.getCodigoControl(), controlCode.getKeyQR(), ventadirecta));
        guardarFactura(ventadirecta, controlCode.getCodigoControl());
        exportarPDF(parameters, jasper);
    }

    public void guardarFactura(Ventadirecta venta, String codControl) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        LoginBean loginBean = (LoginBean) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "loginBean");
        if (venta.getMovimiento() == null) {
            Movimiento movimiento = new Movimiento();
            movimiento.setCodestruct(dosificacion.getEstCod());
            movimiento.setEstado("PENDIENTE");
            movimiento.setCodigocontrol(codControl);
            movimiento.setFecharegistro(new Date());
            //todo:virificar estos valores
            movimiento.setGlosa("");
            movimiento.setMoneda("BS");
            movimiento.setTipocambio(6.69);
            movimiento.setNrofactura(dosificacion.getNumeroactual());
            venta.setMovimiento(movimiento);

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
            venta.setMovimiento(movimiento);
            if(venta.getEstado().equals("PENDIENTE"))
                venta.setEstado("PREPARAR");
            ventadirectaController.setItems(null);
            ventadirectaController.setSelected(venta);
            ventadirectaController.generalUpdate();
            // dosificacion.setNumeroactual(dosificacion.getNumeroactual().intValue() + 1);
            dosificacionController.setSelected(dosificacion);
            dosificacionController.update();
        } else {
            Impresionfactura impresionfactura = new Impresionfactura();
            impresionfactura.setUsuario(loginBean.getUsuario());
            impresionfactura.setFechaimpresion(new Date());
            impresionfactura.setMovimiento(venta.getMovimiento());
            impresionfactura.setDosificacion(dosificacion);
            impresionfactura.setTipo(tipoEtiquetaFactura);
            impresionfactura.setNroFactura(venta.getMovimiento().getNrofactura());
            venta.getMovimiento().getImpresionfacturaCollection().add(impresionfactura);
            /*movimientoController.setSelected(venta.getMovimiento());
            movimientoController.update();*/
            if(venta.getEstado().equals("PENDIENTE"))
                venta.setEstado("PREPARAR");
            ventadirectaController.setItems(null);
            ventadirectaController.setSelected(venta);
            ventadirectaController.generalUpdate();
        }
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
            movimiento.setNrofactura(dosificacion.getNumeroactual());
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
            // dosificacion.setNumeroactual(dosificacion.getNumeroactual().intValue() + 1);
            dosificacionController.setSelected(dosificacion);
            dosificacionController.update();
        } else {
            Impresionfactura impresionfactura = new Impresionfactura();
            impresionfactura.setUsuario(loginBean.getUsuario());
            impresionfactura.setFechaimpresion(new Date());
            impresionfactura.setMovimiento(pedido.getMovimiento());
            impresionfactura.setDosificacion(dosificacion);
            impresionfactura.setTipo(tipoEtiquetaFactura);
            impresionfactura.setNroFactura(pedido.getMovimiento().getNrofactura());
            pedido.getMovimiento().getImpresionfacturaCollection().add(impresionfactura);
            /*movimientoController.setSelected(pedido.getMovimiento());
            movimientoController.update();*/
            if(pedido.getEstado().equals("PENDIENTE"))
                pedido.setEstado("PREPARAR");
            pedidosController.setSelected(pedido);
            pedidosController.setItems(null);
            pedidosController.generalUpdate();
        }
    }

    public void imprimirFacturas() throws IOException, JRException {
        if(pedidosElegidos.size() == 0)
        {
            JSFUtil.addWarningMessage("No hay ningun pedido elegido.");
            return;
        }
        quitarAnulados();
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

    public void imprimirFacturasVenta() throws IOException, JRException {
        if(ventaDirectaElegidos.size() == 0)
        {
            JSFUtil.addWarningMessage("No hay ningun pedido elegido.");
            return;
        }
        quitarAnuladosVenta();
        HashMap parameters = new HashMap();
        moneyUtil = new MoneyUtil();
        barcodeRenderer = new BarcodeRenderer();
        //todo: lanzar un exception en caso que no encuentre una dosificacion valida
        dosificacion = dosificacionFacade.findByPeriodo(new Date());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/factura.jasper"));
        quitarSinFacturaVenta();
        JasperPrint jasperPrint;
        parameters.putAll(fijarParmetrosFactura(ventaDirectaElegidos.get(0)));
        try {
            jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parameters, new JRBeanCollectionDataSource(ventaDirectaElegidos.get(0).getArticulosPedidos()));
        } catch (JRException e) {
            e.printStackTrace();
            //todo:mensaje de error
            return;
        }

        for (int i = 1; i < ventaDirectaElegidos.size(); i++) {
            parameters.putAll(fijarParmetrosFactura(ventaDirectaElegidos.get(i)));
            try {
                jasperPrint.getPages().addAll(JasperFillManager.fillReport(jasper.getPath()
                        , parameters
                        , new JRBeanCollectionDataSource(ventaDirectaElegidos.get(i).getArticulosPedidos())).getPages());
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

    private void quitarAnulados() {
        pedidosElegidos = pedidosElegidos.stream().filter(p->p.getEstado().equals("ANULADO") != true).collect(Collectors.toList());
    }

    private void quitarSinFacturaVenta() {
        ventaDirectaElegidos = ventaDirectaElegidos.stream().filter(p->p.getCliente().getConfactura() == true).collect(Collectors.toList());
    }

    private void quitarAnuladosVenta() {
        ventaDirectaElegidos = ventaDirectaElegidos.stream().filter(p->p.getEstado().equals("ANULADO") != true).collect(Collectors.toList());
    }

    public void imprimirNota(List<Pedidos> pedidosElegidos) throws IOException, JRException {
        if(pedidosElegidos.size() == 0)
        {
            JSFUtil.addWarningMessage("No hay ningun pedido elegido.");
            return;
        }
        HashMap parameters = new HashMap();
        moneyUtil = new MoneyUtil();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/reportes/notaDeEntrega.jasper"));
        this.pedidosElegidos = pedidosElegidos;
        quitarAnulados();
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
                pedidosController.setItems(null);
                pedidosController.generalUpdate();
            }
        }

        exportarPDF(jasperPrint);
        this.pedidosElegidos.clear();
    }

    public Map<String, Object> fijarParmetrosFacturaVenta(Ventadirecta venta) {
        Integer numeroFactura;
        if (venta.getMovimiento() == null) {
            numeroFactura = Integer.parseInt(dosificacionFacade.getSiguienteNumeroFactura());
            dosificacion.setNumeroactual(numeroFactura);
        }else{
            numeroFactura = venta.getMovimiento().getNrofactura();
        }
        ControlCode controlCode = generateCodControl(venta, numeroFactura, dosificacion.getNroautorizacion(), dosificacion.getLlave(),dosificacion.getNitEmpresa());
        if (venta.getEstado().equals("PENDIENTE")) {
            venta.setEstado("PREPARAR");
        }
        guardarFactura(venta, controlCode.getCodigoControl());
        return getReportParams(
                venta.getCliente().getNombreCompleto(), numeroFactura, tipoEtiquetaFactura, controlCode.getCodigoControl(), controlCode.getKeyQR(), venta);
    }

    public Map<String, Object> fijarParmetrosFactura(Pedidos pedido) {
        Integer numeroFactura;
        if (pedido.getMovimiento() == null) {
            numeroFactura = Integer.parseInt(dosificacionFacade.getSiguienteNumeroFactura());
            dosificacion.setNumeroactual(numeroFactura);
        }else{
            numeroFactura = pedido.getMovimiento().getNrofactura();
        }
        ControlCode controlCode = generateCodControl(pedido, numeroFactura, dosificacion.getNroautorizacion(), dosificacion.getLlave(),dosificacion.getNitEmpresa());
        if (pedido.getEstado().equals("PENDIENTE")) {
            pedido.setEstado("PREPARAR");
        }
        guardarFactura(pedido, controlCode.getCodigoControl());
        return getReportParams(
                pedido.getCliente().getNombreCompleto(), numeroFactura, tipoEtiquetaFactura, controlCode.getCodigoControl(), controlCode.getKeyQR(), pedido);
    }

    public Map<String, Object> fijarParmetrosFactura(Ventadirecta venta) {
        Integer numeroFactura;
        if (venta.getMovimiento() == null) {
            numeroFactura = Integer.parseInt(dosificacionFacade.getSiguienteNumeroFactura());
            dosificacion.setNumeroactual(numeroFactura);
        }else{
            numeroFactura = venta.getMovimiento().getNrofactura();
        }
        ControlCode controlCode = generateCodControl(venta, numeroFactura, dosificacion.getNroautorizacion(), dosificacion.getLlave(),dosificacion.getNitEmpresa());
        if (venta.getEstado().equals("PENDIENTE")) {
            venta.setEstado("PREPARAR");
        }
        guardarFactura(venta, controlCode.getCodigoControl());
        return getReportParams(
                venta.getCliente().getNombreCompleto(), numeroFactura, tipoEtiquetaFactura, controlCode.getCodigoControl(), controlCode.getKeyQR(), venta);
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
        response.addHeader("Content-disposition", "attachment; filename=DOCUMENTO_ILVA.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }
    //todo: dar formato al total importe y al importeBaseCreditFisical
    private ControlCode generateCodControl(Pedidos pedido, Integer numberInvoice, BigInteger numberAutorization, String key,String nitEmpresa) {
        Double importeBaseCreditFisical = pedido.getTotalimporte() * 0.13;
        String nroDoc = pedido.getCliente().getNroDoc();
        if(StringUtils.isNotEmpty(pedido.getCliente().getNit()))
        {
            nroDoc = pedido.getCliente().getNit();
        }
        pedido.setImpuesto(importeBaseCreditFisical);
        ControlCode controlCode = new ControlCode(nitEmpresa, numberInvoice, numberAutorization.toString(), pedido.getFechaEntrega(), pedido.getTotalimporte(), importeBaseCreditFisical, nroDoc
        );
        moneyUtil.getLlaveQR(controlCode, key);
        controlCode.generarCodigoQR();
        return controlCode;
    }

    private ControlCode generateCodControl(Ventadirecta venta, Integer numberInvoice, BigInteger numberAutorization, String key,String nitEmpresa) {
        Double importeBaseCreditFisical = venta.getTotalimporte() * 0.13;
        String nroDoc = venta.getCliente().getNroDoc();
        if(StringUtils.isNotEmpty(venta.getCliente().getNit()))
        {
            nroDoc = venta.getCliente().getNit();
        }
        venta.setImpuesto(importeBaseCreditFisical);
        ControlCode controlCode = new ControlCode(nitEmpresa, numberInvoice, numberAutorization.toString(), venta.getFechaPedido(), venta.getTotalimporte(), importeBaseCreditFisical, nroDoc);
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

    public Ventadirecta getVentadirecta() {
        return ventadirecta;
    }

    public void setVentadirecta(Ventadirecta ventadirecta) {
        this.ventadirecta = ventadirecta;
    }

    public List<Ventadirecta> getVentaDirectaElegidos() {
        return ventaDirectaElegidos;
    }

    public void setVentaDirectaElegidos(List<Ventadirecta> ventaDirectaElegidos) {
        this.ventaDirectaElegidos = ventaDirectaElegidos;
    }
}
