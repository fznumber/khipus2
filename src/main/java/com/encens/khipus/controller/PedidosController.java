package com.encens.khipus.controller;

import com.encens.khipus.ejb.DistribuidorFacade;
import com.encens.khipus.ejb.InvArticulosFacade;
import com.encens.khipus.ejb.PedidosFacade;
import com.encens.khipus.ejb.PersonasFacade;
import com.encens.khipus.model.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.encens.khipus.util.JSFUtil;
import com.encens.khipus.util.JSFUtil.PersistAction;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("pedidosController")
@SessionScoped
public class PedidosController implements Serializable {

    @EJB
    private PedidosFacade ejbFacade;
    @EJB
    private InvArticulosFacade invArticulosFacade;
    @EJB
    private PersonasFacade personasFacade;
    @EJB
    private DistribuidorFacade distribuidorFacade;

    private List<ArticulosPedido> articulosPedidos = new ArrayList<>();
    private List<ArticulosPedido> articulosPedidosElegidos = new ArrayList<>();
    private List<InvArticulos> articulos;
    private List<Pedidos> items = null;
    private Pedidos selected;
    private Persona personaElegido;
    private Persona distribuidorElegido;
    private List<Persona> personas;
    private List<Persona> distribuidores;
    private Distribuidor distribuidor;
    private Tipopedido tipopedido;
    private Integer importeTotal = 0;
    private InvArticulos articuloElegido;
    public PedidosController() {
    }

    public Pedidos getSelected() {
        return selected;
    }

    public void setSelected(Pedidos selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
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

    public List<Persona> completarCliente(String query) {
        List<Persona> clientesFiltrados = new ArrayList<>();
        for(Persona persona: personas) {

            if(persona.getNombreCompleto().toLowerCase().contains(query)) {
                clientesFiltrados.add(persona);
            }
        }

        return clientesFiltrados;
    }

    public List<Persona> completarDistribuidor(String query) {
        List<Persona> distribuidoresFiltrados = new ArrayList<>();
        for(Persona persona: distribuidores) {

            if(persona.getNombreCompleto().toLowerCase().contains(query)) {
                distribuidoresFiltrados.add(persona);
            }
        }

        return distribuidoresFiltrados;
    }

    public void agregarArticulo()
    {
        if(articuloElegido == null)
        return;
        ArticulosPedido articulosPedido = new ArticulosPedido();
        articulosPedido.setInvArticulos(articuloElegido);
        articulosPedido.setCantidad(BigInteger.ZERO);
        articulosPedido.setPrecio(0.0);
        articulosPedido.setReposicion(BigInteger.ZERO);
        articulosPedido.setTotal(0.0);
        articulosPedido.setTotalInv(BigInteger.ZERO);
        articulosPedidosElegidos.add(articulosPedido);
        articuloElegido = null;
    }

    private PedidosFacade getFacade() {
        return ejbFacade;
    }

    public Pedidos prepareCreate() {
        selected = new Pedidos();
        personas = personasFacade.findAllClientesPersonaInstitucion();
        articulos = invArticulosFacade.findAllInvArticulos();
        distribuidores = personasFacade.findAlldistribuidores();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PedidosCreated"));
        if (!JSFUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PedidosUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PedidosDeleted"));
        if (!JSFUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Pedidos> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    public String reinit() {
        articuloElegido = new InvArticulos();
        return null;
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

    public Pedidos getPedidos(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Pedidos> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Pedidos> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Pedidos.class)
    public static class PedidosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PedidosController controller = (PedidosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pedidosController");
            return controller.getPedidos(getKey(value));
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
            if (object instanceof Pedidos) {
                Pedidos o = (Pedidos) object;
                return getStringKey(o.getIdpedidos());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Pedidos.class.getName()});
                return null;
            }
        }

    }

    public Persona getPersonaElegido() {
        return personaElegido;
    }

    public void setPersonaElegido(Persona personaElegido) {
        this.personaElegido = personaElegido;
    }

    public Distribuidor getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(Distribuidor distribuidor) {
        this.distribuidor = distribuidor;
    }

    public Tipopedido getTipopedido() {
        return tipopedido;
    }

    public void setTipopedido(Tipopedido tipopedido) {
        this.tipopedido = tipopedido;
    }

    public List<ArticulosPedido> getArticulosPedidos() {
        return articulosPedidos;
    }

    public void setArticulosPedidos(List<ArticulosPedido> articulosPedidos) {
        this.articulosPedidos = articulosPedidos;
    }

    public List<ArticulosPedido> getArticulosPedidosElegidos() {
        return articulosPedidosElegidos;
    }

    public void setArticulosPedidosElegidos(List<ArticulosPedido> articulosPedidosElegidos) {
        this.articulosPedidosElegidos = articulosPedidosElegidos;
    }

    public List<InvArticulos> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<InvArticulos> articulos) {
        this.articulos = articulos;
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

    public Persona getDistribuidorElegido() {
        return distribuidorElegido;
    }

    public void setDistribuidorElegido(Persona distribuidorElegido) {
        this.distribuidorElegido = distribuidorElegido;
    }
}
