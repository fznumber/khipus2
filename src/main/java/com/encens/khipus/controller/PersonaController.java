package com.encens.khipus.controller;

import com.encens.khipus.ejb.*;
import com.encens.khipus.model.*;
import com.encens.khipus.util.JSFUtil;
import com.encens.khipus.util.JSFUtil.PersistAction;
import java.io.Serializable;
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

@Named("personaController")
@SessionScoped
public class PersonaController implements Serializable {

    @EJB
    private PersonasFacade ejbFacade;
    @EJB
    private RetencionFacade retencionFacade;
    @EJB
    private ClienteFacade clienteFacade;
    @EJB
    private InstitucionFacade institucionFacade;
    private List<Persona> items = null;
    private Persona selected;
    private Boolean esPersona;
    private Boolean tieneRetencion;
    private Boolean tieneDescuento;
    private Retencion retencion;

    public PersonaController() {
    }

    public Persona getSelected() {
        return selected;
    }

    public void setSelected(Persona selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PersonasFacade getFacade() {
        return ejbFacade;
    }

    public Persona prepareCreate() {
        selected = new Persona();
        initializeEmbeddableKey();
        esPersona = true;
        tieneRetencion = false;
        tieneDescuento = false;
        return selected;
    }

    public void create() {

        if(esPersona) {
            Cliente cliente = new Cliente();
            cliente.setAm(selected.getAm());
            cliente.setAp(selected.getAp());
            cliente.setNom(selected.getNom());
            cliente.setDescuento(selected.getDescuento());
            cliente.setNroDoc(selected.getNroDoc());
            cliente.setDireccion(selected.getDireccion());
            cliente.setDepartamento(selected.getDepartamento());
            cliente.setNit(selected.getNit());
            cliente.setSexo(selected.getSexo());
            cliente.setTelefono(selected.getTelefono());
            cliente.setTipocliente(selected.getTipocliente());
            cliente.setRetencion(retencion);
            clienteFacade.create(cliente);
        }
        else {
            Institucion institucion = new Institucion();
            institucion.setRazonsocial(selected.getRazonsocial());
            institucion.setDescuento(selected.getDescuento());
            institucion.setDireccion(selected.getDireccion());
            institucion.setDepartamento(selected.getDepartamento());
            institucion.setNit(selected.getNit());
            institucion.setTelefono(selected.getTelefono());
            institucion.setTipocliente(selected.getTipocliente());
            institucion.setRetencion(retencion);
            institucionFacade.create(institucion);
        }
        //persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PersonasCreated"));
        JSFUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClienteCreated"));
        if (!JSFUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void cambiarRetencion()
    {
        if(tieneRetencion)
            retencion = retencionFacade.findActivo();
        else
            retencion = null;
        selected.setRetencion(retencion);
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PersonasUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PersonasDeleted"));
        if (!JSFUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Persona> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
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

    public Persona getPersonas(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Persona> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Persona> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(value = "convertirPersona",forClass = Persona.class)
    public static class PersonasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonaController controller = (PersonaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personaController");
            return controller.getPersonas(getKey(value));
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
            if (object instanceof Persona) {
                Persona o = (Persona) object;
                if(o.getPiId() == null)
                    return null;
                return getStringKey(o.getPiId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Persona.class.getName()});
                return null;
            }
        }

    }

    public Boolean getEsPersona() {
        return esPersona;
    }

    public void setEsPersona(Boolean esPersona) {
        this.esPersona = esPersona;
    }

    public Boolean getTieneRetencion() {
        return tieneRetencion;
    }

    public void setTieneRetencion(Boolean tieneRetencion) {
        this.tieneRetencion = tieneRetencion;
    }

    public Boolean getTieneDescuento() {
        return tieneDescuento;
    }

    public void setTieneDescuento(Boolean tieneDescuento) {
        this.tieneDescuento = tieneDescuento;
    }

    public Retencion getRetencion() {
        return retencion;
    }

    public void setRetencion(Retencion retencion) {
        this.retencion = retencion;
    }
}
