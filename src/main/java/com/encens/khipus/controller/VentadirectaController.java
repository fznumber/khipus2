package com.encens.khipus.controller;



import com.encens.khipus.ejb.InvArticulosFacade;
import com.encens.khipus.ejb.PersonasFacade;
import com.encens.khipus.ejb.VentaarticuloFacade;
import com.encens.khipus.ejb.VentadirectaFacade;
import com.encens.khipus.model.*;
import com.encens.khipus.util.JSFUtil;
import com.encens.khipus.util.JSFUtil.PersistAction;
import com.encens.khipus.util.JSFUtil;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

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
    private VentaarticuloFacade ventaarticuloFacade;

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
    private Double pago = 0.0;
    private Double cambio = 0.0;

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

    public void registrar() throws IOException, JRException {
        selected.setEstado("PENDIENTE");
        create();
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
        selected.setCodigo(new CodigoPedidoSecuencia());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PedidosCreated"));
        if (!JSFUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            selected = new Ventadirecta();
            personaElegida = new Persona();
            personas = personasFacade.findAllClientesPersonaInstitucion();
            articulos = invArticulosFacade.findAllInvArticulos();
        }

    }

    private boolean validarCampos() {
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

    public Double getPago() {
        return pago;
    }

    public void setPago(Double pago) {
        this.pago = pago;
    }

    public Double getCambio() {
        cambio = pago - selected.getTotalimporte();
        if(pago <= selected.getTotalimporte())
            cambio = 0.0;
        return cambio;
    }

    public void setCambio(Double cambio) {
        this.cambio = cambio;
    }
}
