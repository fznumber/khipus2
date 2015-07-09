package com.encens.khipus.controller;



import com.encens.khipus.ejb.*;
import com.encens.khipus.model.*;
import com.encens.khipus.util.JSFUtil;
import com.encens.khipus.util.JSFUtil.PersistAction;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("ventadirectaController")
@SessionScoped
public class VentadirectaController implements Serializable {

    @EJB
    private PersonasFacade personasFacade;
    @EJB
    private InvArticulosFacade invArticulosFacade;
    @EJB
    private com.encens.khipus.ejb.VentadirectaFacade ejbFacade;
    @EJB
    SfConfencFacade sfConfencFacade;
    @EJB
    private VentaarticuloFacade ventaarticuloFacade;
    @Inject
    private PedidosReportController pedidosReportController;
    @Inject
    private SfTmpdetController sfTmpdetController;
    @EJB
    private SfTmpencFacade sfTmpencFacade;
    @Inject
    private SfTmpencController sfTmpencController;

    private List<Ventadirecta> items = null;
    private List<Ventadirecta> ventasElegidas = new ArrayList<>();
    private Ventadirecta selected;
    private Date fechaVentaFiltro;
    private List<String> estados;
    private List<Persona> personas;
    private List<InvArticulos> articulos;
    private Persona personaElegida;
    private Integer importeTotal = 0;
    private InvArticulos articuloElegido;
    private List<Ventadirecta> ventaDirectaFiltrado;
    private List<Ventadirecta> ventaDirectaElegidos = new ArrayList<>();
    private byte[] nota;
    private String codNota;

    public VentadirectaController() {
    }

    public Ventadirecta getSelected() {
        return selected;
    }

    public void setSelected(Ventadirecta selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private VentadirectaFacade getFacade() {
        return ejbFacade;
    }

    public Ventadirecta prepareCreate() {
        selected = new Ventadirecta();
        personaElegida = new Persona();
        personas = personasFacade.findAllClientesPersonaInstitucion();
        articulos = invArticulosFacade.findAllInvArticulos();
        initializeEmbeddableKey();
        return selected;
    }

    public void prepareEdit(Ventadirecta ventadirecta) {
        selected = ventadirecta;
        personas = personasFacade.findAllClientesPersonaInstitucion();
        articulos = invArticulosFacade.findAllInvArticulos();
        personaElegida = ventadirecta.getCliente();

        for(ArticulosPedido articulosPedido:ventadirecta.getArticulosPedidos())
        {
            articulos.remove(articulosPedido.getInvArticulos());
        }
    }

    public List<InvArticulos> completarArticulo(String query) {
        List<InvArticulos> articulosFiltrados = new ArrayList<>();
        for(InvArticulos articulo:articulos) {

            if(articulo.getDescri().toLowerCase().contains(query)) {
                articulosFiltrados.add(articulo);
            }
        }

        return articulosFiltrados;
    }

    public void reponerArticulo(ArticulosPedido item){
        articulos.add(item.getInvArticulos());
    }

    public List<Persona> completarCliente(String query) {
        List<Persona> clientesFiltrados = new ArrayList<>();
        for(Persona persona: personas) {

            if(persona.getNombreCompleto().toLowerCase().contains(query)) {
                clientesFiltrados.add(persona);
            }
        }

        return clientesFiltrados;
    }

    public void agregarArticulo()
    {
        if(articuloElegido == null)
            return;
        Ventaarticulo ventaarticulo = ventaarticuloFacade.findByInvArticulo(articuloElegido);

        ArticulosPedido articulosPedido = new ArticulosPedido();
        articulosPedido.setInvArticulos(articuloElegido);
        articulosPedido.setPrecioInv(ventaarticulo.getPrecio());
        articulosPedido.setPrecio(ventaarticulo.getPrecio());
        articulosPedido.setReposicion(0);
        articulosPedido.setVentadirecta(selected);
        articulosPedido.setTipo(ventaarticulo.getTipo());
        selected.getArticulosPedidos().add(articulosPedido);
        articulos.remove(articuloElegido);
        articuloElegido = null;
    }

    public ActionListener createActionListener() throws IOException, JRException {
        //registrar();
        return new ActionListener() {
            @Override
            public void processAction(ActionEvent event) throws AbortProcessingException {
                try {
                    pedidosReportController.imprimirNotaEntrega(selected);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JRException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void registrarImprimirNota() throws IOException, JRException {
        if(validarCampos())
        {
            return;
        }
        SfConfenc operacion= sfConfencFacade.getOperacion("PEDIDOSINFACTURA");
        if(operacion == null)
        {
            JSFUtil.addErrorMessage("No se encuentra una operación registrada");
        }
        FacesContext facesContext = FacesContext.getCurrentInstance();
        LoginBean loginBean = (LoginBean) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "loginBean");
        selected.setUsuario(loginBean.getUsuario());
        selected.setCliente(personaElegida);
        selected.setCodigo(getFacade().getSiguienteNumeroVenta());
        selected.setEstado("PREPARAR");
        selected.setDocumento(pedidosReportController.generarNotaEntrega(selected));
        persist(PersistAction.CREATE, "La venta se registro correctamente.");

        if (!JSFUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            nota = selected.getDocumento();
            codNota = "NOTA_"+selected.getCodigo().toString();
            String nomcliente = "";
            if(!StringUtils.isEmpty(selected.getCliente().getRazonsocial()))
                nomcliente = selected.getCliente().getRazonsocial();
            else
                nomcliente = selected.getCliente().getNombreCompleto();

            crearAsientoNota(selected.getTotalimporte(), nomcliente,operacion);
            selected = new Ventadirecta();
            personaElegida = new Persona();
            personas = personasFacade.findAllClientesPersonaInstitucion();
            articulos = invArticulosFacade.findAllInvArticulos();
        }
    }

    private void crearAsientoNota(Double totalimporte, String nomcliente, SfConfenc operacion) {

        SfTmpenc sfTmpenc = new SfTmpenc();
        sfTmpenc.setNoCia("01");
        sfTmpenc.setFecha(new Date());
        sfTmpenc.setDescri(operacion.getGlosa() + " por la venta Nro: " + selected.getCodigo() + " " + nomcliente);
        sfTmpenc.setTipoDoc(operacion.getTipoDoc());
        sfTmpenc.setGlosa(operacion.getGlosa() + " por la venta Nro: " + selected.getCodigo() + " " + nomcliente);
        sfTmpenc.setEstado("PEN");
        sfTmpenc.setCliente(selected.getCliente());
        sfTmpenc.setDebe(selected.getTotalimporte());
        sfTmpenc.setNombreCliente(selected.getCliente().getNombreCompleto());
        FacesContext facesContext = FacesContext.getCurrentInstance();
        LoginBean loginBean = (LoginBean) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "loginBean");
        sfTmpenc.setUsuario(loginBean.getUsuario());
        String nroTrans = sfTmpencFacade.getSiguienteNumeroTransacccion();
        sfTmpenc.setNoTrans(nroTrans);

        List<SfConfdet> asientos = new ArrayList<>(operacion.getAsientos());
        SfConfdet ventaProductos = asientos.get(0);
        SfConfdet cajaGeneral = asientos.get(1);
        /////
        SfTmpdet asientoVentaProductos = new SfTmpdet();
        asientoVentaProductos.setNoCia("01");
        asientoVentaProductos.setCuenta(ventaProductos.getCuenta().getCuenta());
        asientoVentaProductos.setNoTrans(nroTrans);
        setDebeOHaber(ventaProductos, asientoVentaProductos,totalimporte);
        sfTmpenc.getAsientos().add(asientoVentaProductos);
        /////
        SfTmpdet asientoCajaGeneral = new SfTmpdet();
        asientoCajaGeneral.setNoCia("01");
        asientoCajaGeneral.setCuenta(cajaGeneral.getCuenta().getCuenta());
        asientoCajaGeneral.setNoTrans(nroTrans);
        setDebeOHaber(cajaGeneral, asientoCajaGeneral,totalimporte);
        sfTmpenc.getAsientos().add(asientoCajaGeneral);

        selected.setAsiento(sfTmpenc);
    }

    private void setDebeOHaber(SfConfdet ope,SfTmpdet asiento,Double monto){
        if(ope.getTipomovimiento().equals("DEBE"))
            asiento.setDebe(new BigDecimal(monto));
        else
            asiento.setHaber(new BigDecimal(monto));
    }

    private void crearAsientoFactura(Double totalimporte, String nomcliente, Integer codigo) {
        Double iva = totalimporte * 0.13;
        Double it = totalimporte * 0.03;
        Double importe = totalimporte - iva -it;
        String glosa = "Venta al contado con factura ("+codigo.toString()+") "+nomcliente;
        SfTmpenc sfTmpenc = new SfTmpenc();
        sfTmpenc.setAgregarCtaProv("SI");
        sfTmpenc.setNoCia("01");
        sfTmpenc.setFecha(new Date());
        sfTmpenc.setDescri(glosa);
        sfTmpenc.setTipoDoc("CI");
        sfTmpenc.setFormulario("CI");
        sfTmpenc.setGlosa(glosa);
        sfTmpenc.setEstado("PEN");
        sfTmpenc.setNoUsr("ADM");
        sfTmpenc.setCuenta("111");
        String nroTrans = sfTmpencFacade.getSiguienteNumeroTransacccion();
        sfTmpenc.setNoTrans(nroTrans);
        sfTmpencController.setSelected(sfTmpenc);
        sfTmpencController.createGeneral();
        ///
        SfTmpdet sfTmpdet = new SfTmpdet();
        sfTmpdet.setNoCia("01");
        sfTmpdet.setCuenta("1110110100");
        sfTmpdet.setNoTrans(nroTrans);
        sfTmpdet.setDebe(new BigDecimal(totalimporte));
        sfTmpdet.setTc(new BigDecimal(1.0));
        sfTmpdetController.setSelected(sfTmpdet);
        sfTmpdetController.createGeneral();

        SfTmpdet sfTmpdet1 = new SfTmpdet();
        sfTmpdet1.setNoCia("01");
        sfTmpdet1.setCuenta("5420110201");
        sfTmpdet1.setNoTrans(nroTrans);
        sfTmpdet1.setHaber(new BigDecimal(importe));
        sfTmpdet1.setTc(new BigDecimal(1.0));
        sfTmpdetController.setSelected(sfTmpdet1);
        sfTmpdetController.createGeneral();

        SfTmpdet sfTmpdet2 = new SfTmpdet();
        sfTmpdet2.setNoCia("01");
        sfTmpdet2.setCuenta("2420410200");
        sfTmpdet2.setNoTrans(nroTrans);
        sfTmpdet2.setHaber(new BigDecimal(iva));
        sfTmpdet2.setTc(new BigDecimal(1.0));
        sfTmpdetController.setSelected(sfTmpdet2);
        sfTmpdetController.createGeneral();

        SfTmpdet sfTmpdet3 = new SfTmpdet();        
        sfTmpdet3.setNoCia("01");
        sfTmpdet3.setCuenta("2420410100");
        sfTmpdet3.setNoTrans(nroTrans);        
        sfTmpdet3.setHaber(new BigDecimal(it));
        sfTmpdet3.setTc(new BigDecimal(1.0));
        sfTmpdetController.setSelected(sfTmpdet3);
        sfTmpdetController.createGeneral();
    }

    public void registrarImprimirNotaFactura() throws IOException, JRException {
        if(validarCampos())
        {
            return;
        }
        FacesContext facesContext = FacesContext.getCurrentInstance();
        LoginBean loginBean = (LoginBean) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "loginBean");
        selected.setUsuario(loginBean.getUsuario());
        selected.setCliente(personaElegida);
        selected.setCodigo(getFacade().getSiguienteNumeroVenta());
        selected.setEstado("PREPARAR");
        selected.setDocumento(pedidosReportController.generarFacturaNotaVentaDirecta(selected));
        persist(PersistAction.CREATE, "La venta se registro correctamente.");

        if (!JSFUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            nota = selected.getDocumento();
            codNota = "NOTAFAC_"+selected.getCodigo().toString();
            String nomcliente = "";
            if(!StringUtils.isEmpty(selected.getCliente().getRazonsocial()))
                nomcliente = selected.getCliente().getRazonsocial();
            else
                nomcliente = selected.getCliente().getNombreCompleto();

            crearAsientoFactura(selected.getTotalimporte(), nomcliente, selected.getCodigo());
            selected = new Ventadirecta();
            personaElegida = new Persona();
            personas = personasFacade.findAllClientesPersonaInstitucion();
            articulos = invArticulosFacade.findAllInvArticulos();
        }
    }

    public StreamedContent getNotaEntrega(){
        if(nota == null)
            return null;
        return new DefaultStreamedContent(new ByteArrayInputStream(nota),"application/pdf", codNota+".pdf");
    }

    public void verImprimir() throws IOException, JRException {
        registrarImprimirNota();
        RequestContext.getCurrentInstance().openDialog("PdfDisplayRedirect");
    }

    public void create() {
        if(validarCampos())
        {
            return;
        }
        FacesContext facesContext = FacesContext.getCurrentInstance();
        LoginBean loginBean = (LoginBean) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "loginBean");
        selected.setUsuario(loginBean.getUsuario());
        selected.setCliente(personaElegida);
        selected.setCodigo(getFacade().getSiguienteNumeroVenta());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PedidosCreated"));
        if (!JSFUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            selected = new Ventadirecta();
            personaElegida = new Persona();
            personas = personasFacade.findAllClientesPersonaInstitucion();
            articulos = invArticulosFacade.findAllInvArticulos();
        }

    }

    public boolean validarCampos() {
        Boolean error = false;
        if(personaElegida.getPiId() == null)
        {
            JSFUtil.addErrorMessage( ResourceBundle.getBundle("/Bundle").getString("ClienteRequerido"));
            error = true;
        }
        if(selected.getArticulosPedidos().size() == 0)
        {
            JSFUtil.addErrorMessage( ResourceBundle.getBundle("/Bundle").getString("ArticulosRequerido"));
            error = true;
        }
        if(selected.getArticulosPedidos().size() > 0)
        {
            for(ArticulosPedido articulosPedido:selected.getArticulosPedidos())
            {
                if(articulosPedido.getImporte() == 0.0 && articulosPedido.getReposicion() == 0.0){
                    JSFUtil.addErrorMessage("Eror: El "+articulosPedido.getInvArticulos().getDescri()+" tiene el importe en cero.");
                    error = true;
                }

            }
        }
        return error;
    }

    public void cancel(){
        selected = null;
        nota = null;
    }

    public void anularVenta(){
        if(validarAnulacion())
        {
            return;
        }
        selected.setEstado("ANULADO");
        persist(PersistAction.UPDATE, "El pedido fue anulado con exito.");
        items = null;
    }

    private boolean validarAnulacion() {
        Boolean error = false;
        if(StringUtils.isEmpty(selected.getObservacion())){
            JSFUtil.addErrorMessage( "Es necesario agregar una descripción.");
            error = true;
        }
        return error;
    }

    public void generalUpdate() {
        getFacade().edit(selected);
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VentadirectaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("VentadirectaDeleted"));
        if (!JSFUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Ventadirecta> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void setItems(List<Ventadirecta> items) {
        this.items = items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JSFUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JSFUtil.addErrorMessage(msg);
                } else {
                    JSFUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JSFUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Ventadirecta getVentadirecta(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Ventadirecta> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Ventadirecta> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Ventadirecta.class)
    public static class VentadirectaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VentadirectaController controller = (VentadirectaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ventadirectaController");
            return controller.getVentadirecta(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Ventadirecta) {
                Ventadirecta o = (Ventadirecta) object;
                return getStringKey(o.getIdventadirecta());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Ventadirecta.class.getName()});
                return null;
            }
        }

    }

    public List<String> getEstados() {
        if(estados == null) {
            estados = new ArrayList<>();
            estados.add("PENDIENTE");
            estados.add("PREPARAR");
            estados.add("ENTREGADO");
            estados.add("ANULADO");
        }
        return estados;
    }

    public void setEstados(List<String> estados) {
        this.estados = estados;
    }

    public List<Ventadirecta> getVentasElegidas() {
        return ventasElegidas;
    }

    public void setVentasElegidas(List<Ventadirecta> ventasElegidas) {
        this.ventasElegidas = ventasElegidas;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public List<InvArticulos> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<InvArticulos> articulos) {
        this.articulos = articulos;
    }

    public Persona getPersonaElegida() {
        return personaElegida;
    }

    public void setPersonaElegida(Persona personaElegida) {
        this.personaElegida = personaElegida;
    }

    public Integer getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(Integer importeTotal) {
        this.importeTotal = importeTotal;
    }

    public InvArticulos getArticuloElegido() {
        return articuloElegido;
    }

    public void setArticuloElegido(InvArticulos articuloElegido) {
        this.articuloElegido = articuloElegido;
    }

    public List<Ventadirecta> getVentaDirectaFiltrado() {
        return ventaDirectaFiltrado;
    }

    public void setVentaDirectaFiltrado(List<Ventadirecta> ventaDirectaFiltrado) {
        this.ventaDirectaFiltrado = ventaDirectaFiltrado;
    }

    public List<Ventadirecta> getVentaDirectaElegidos() {
        return ventaDirectaElegidos;
    }

    public void setVentaDirectaElegidos(List<Ventadirecta> ventaDirectaElegidos) {
        this.ventaDirectaElegidos = ventaDirectaElegidos;
    }

    public Date getFechaVentaFiltro() {
        return fechaVentaFiltro;
    }

    public void setFechaVentaFiltro(Date fechaVentaFiltro) {
        this.fechaVentaFiltro = fechaVentaFiltro;
    }

    public String getCodNota() {
        return codNota;
    }

    public void setCodNota(String codNota) {
        this.codNota = codNota;
    }
}
