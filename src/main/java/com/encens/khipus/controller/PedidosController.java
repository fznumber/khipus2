package com.encens.khipus.controller;

import com.encens.khipus.ejb.*;
import com.encens.khipus.model.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.encens.khipus.util.JSFUtil;
import com.encens.khipus.util.JSFUtil.PersistAction;
import java.util.AbstractList;
import net.sf.jasperreports.engine.JRException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Inject;
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
    private VentaarticuloFacade ventaarticuloFacade;
    @EJB
    private VentaclienteFacade ventaclienteFacade;
    @Inject
    private ArticulosPedidoController articulosPedidoController;


    private List<ArticulosPedido> articulosPedidos = new ArrayList<>();
    private List<ArticulosPedido> articulosPedidosElegidos = new ArrayList<>();
    private List<InvArticulos> articulos;
    private List<ArticulosPedido> reposiciones = new ArrayList<>();
    private List<Pedidos> items = null;
    private Pedidos selected;
    private List<Persona> personas;
    private List<Persona> distribuidores;
    private Tipopedido tipopedido;
    private Integer importeTotal = 0;
    private InvArticulos articuloElegido;
    private List<Pedidos> pedidosFiltrado;
    private List<Pedidos> pedidosElegidos = new ArrayList<>();
    private List<String> estados;
    private Date fechaEntregaFiltro;
    private Date fechaPedidoFiltro;
    private Persona personaElegida;
    private Boolean conReposicion;
    private Boolean tieneReposicion;
    private Boolean reposicionesYaAgregadas = false;

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
        Ventaarticulo ventaarticulo = ventaarticuloFacade.findByInvArticulo(articuloElegido);

        ArticulosPedido articulosPedido = new ArticulosPedido();
        articulosPedido.setInvArticulos(articuloElegido);
        articulosPedido.setPrecioInv(ventaarticulo.getPrecio());
        articulosPedido.setPrecio(ventaarticulo.getPrecio());
        articulosPedido.setReposicion(0);
        articulosPedido.setPedidos(selected);
        selected.getArticulosPedidos().add(articulosPedido);
        articulos.remove(articuloElegido);
        articuloElegido = null;
    }

    public void reponerArticulo(ArticulosPedido item){
        articulos.add(item.getInvArticulos());
    }

    private PedidosFacade getFacade() {
        return ejbFacade;
    }

    public Pedidos prepareCreate() {
        selected = new Pedidos();
        personaElegida = new Persona();
        personas = personasFacade.findAllClientesPersonaInstitucion();
        articulos = invArticulosFacade.findAllInvArticulos();
        distribuidores = personasFacade.findAlldistribuidores();
        conReposicion = false;
        initializeEmbeddableKey();
        return selected;
    }

    public void prepareEdit(Pedidos ped) {
        selected = ped;
        personas = personasFacade.findAllClientesPersonaInstitucion();
        articulos = invArticulosFacade.findAllInvArticulos();
        distribuidores = personasFacade.findAlldistribuidores();
        conReposicion = ped.getConReposicion();
        tieneReposicion = ped.getConReposicion();
        reposicionesYaAgregadas = ped.getConReposicion();
        personaElegida = ped.getCliente();
        for(ArticulosPedido articulosPedido:ped.getArticulosPedidos())
        {
            articulos.remove(articulosPedido);
        }
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
        selected.setFechaPedido(new Date());
        selected.setConReposicion(false);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PedidosCreated"));
        actualizarReposiciones();
        if (!JSFUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            selected = new Pedidos();
            personaElegida = new Persona();
            personas = personasFacade.findAllClientesPersonaInstitucion();
            articulos = invArticulosFacade.findAllInvArticulos();
            distribuidores = personasFacade.findAlldistribuidores();
            reposiciones = new ArrayList<>();
        }

    }

    private boolean validarCampos() {
        Boolean error = false;
        if(personaElegida.getPiId() == null)
        {
            JSFUtil.addErrorMessage( ResourceBundle.getBundle("/Bundle").getString("ClienteRequerido"));
            error = true;
        }
        if(selected.getFechaEntrega() == null)
        {
            JSFUtil.addErrorMessage( ResourceBundle.getBundle("/Bundle").getString("FechaEntregaRequerido"));
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
        reposiciones = new ArrayList<>();
        selected = null;
        tieneReposicion = false;
    }

    public void update() {
        if(validarReposicion())
            return;

        selected.setConReposicion(conReposicion);
        actualizarReposiciones();
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PedidosUpdated"));
        items = null;
    }
    
    public void generalUpdate() {      
        getFacade().edit(selected);
    }

    private boolean validarReposicion() {
        Boolean error = false;
        if(selected.getArticulosPedidos().size() > 0)
        {
            for(ArticulosPedido articulosPedido:selected.getArticulosPedidos())
            {
                if( articulosPedido.getPorReponer() > articulosPedido.getCantidad()){
                    JSFUtil.addErrorMessage("Eror: El "+articulosPedido.getInvArticulos().getDescri()+" tiene mayor a la reposición que la cantidad.");
                    error = true;
                } 
            }
        }
        return error;
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PedidosDeleted"));
        if (!JSFUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void actualizarReposiciones()
    {
        for(ArticulosPedido articulosPedido:reposiciones)
        {
            articulosPedidoController.setSelected(articulosPedido);
            articulosPedidoController.update();
        }
    }

    public void setItems(List<Pedidos> items) {
        this.items = items;
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

    public List<Pedidos> getPedidosFiltrado() {
        return pedidosFiltrado;
    }

    public void setPedidosFiltrado(List<Pedidos> pedidosFiltrado) {
        this.pedidosFiltrado = pedidosFiltrado;
    }

    public Date getFechaEntregaFiltro() {
        return fechaEntregaFiltro;
    }

    public void setFechaEntregaFiltro(Date fechaEntregaFiltro) {
        this.fechaEntregaFiltro = fechaEntregaFiltro;
    }

    public Date getFechaPedidoFiltro() {
        return fechaPedidoFiltro;
    }

    public void setFechaPedidoFiltro(Date fechaPedidoFiltro) {
        this.fechaPedidoFiltro = fechaPedidoFiltro;
    }

    public List<String> getEstados() {
        if(estados == null) {
            estados = new ArrayList<>();
            estados.add("PENDIENTE");
            estados.add("PREPARAR");
            estados.add("ENTREGADO");
        }
        return estados;
    }

    public void setEstados(List<String> estados) {
        this.estados = estados;
    }

    public Persona getPersonaElegida() {
        return personaElegida;
    }

    public void setPersonaElegida(Persona personaElegida) {   
        if(personaElegida == null)
            return;
        
        if(this.personaElegida.getPiId() != personaElegida.getPiId())
        {
            selected.getArticulosPedidos().clear();
            tieneReposicion = false;                
            conReposicion = false;
            reposicionesYaAgregadas= false;
        }else{
            return;
        }
        
        reposiciones.clear();
        reposiciones = getFacade().findReposicionesPorPersona(personaElegida);
        if(reposiciones.size() >0 && !conReposicion)
        {
            for(ArticulosPedido repo:reposiciones)
            {
                ArticulosPedido articulo = new ArticulosPedido();
                articulo.setPrecio(repo.getPrecio());
                repo.setEstado("REPUESTO");
                articulo.setReposicion(repo.getPorReponer());
                articulo.setPedidos(selected);
                articulo.setInvArticulos(repo.getInvArticulos());
                articulo.setCantidad(0);
                selected.getArticulosPedidos().add(articulo);
                articulos.remove(repo.getInvArticulos());
            }
            tieneReposicion = true;
            conReposicion = true;
            reposicionesYaAgregadas = true;
        }
        this.personaElegida = personaElegida;
    }

    public void quitarReposicion(){

        for(ArticulosPedido repo:reposiciones)
        {
            selected.getArticulosPedidos().remove(repo);
            repo.setEstado("RECHAZADO");
            articulos.add(repo.getInvArticulos());
            reposicionesYaAgregadas = false;
        }
    }

    public void agregarReposicion(){
        for(ArticulosPedido repo:reposiciones)
        {
                ArticulosPedido articulo = new ArticulosPedido();
                articulo.setPrecio(repo.getPrecio());
                repo.setEstado("REPUESTO");
                articulo.setReposicion(repo.getPorReponer());
                articulo.setPedidos(selected);
                articulo.setInvArticulos(repo.getInvArticulos());
                articulo.setCantidad(0);
                selected.getArticulosPedidos().add(articulo);
                reposicionesYaAgregadas = true;
        }
    }

    public Boolean getConReposicion() {
        return conReposicion;
    }

    public void setConReposicion(Boolean conReposicion) {
        if(conReposicion)
        {   if(!reposicionesYaAgregadas)
            agregarReposicion();
        }
        else
            quitarReposicion();
        this.conReposicion = conReposicion;
    }

    public Boolean getTieneReposicion() {
        return tieneReposicion;
    }

    public void setTieneReposicion(Boolean tieneReposicion) {
        this.tieneReposicion = tieneReposicion;
    }

    public List<Pedidos> getPedidosElegidos() {        
        return pedidosElegidos;
    }

    public void setPedidosElegidos(List<Pedidos> pedidosElegidos) {
        this.pedidosElegidos = pedidosElegidos;
    }
}
