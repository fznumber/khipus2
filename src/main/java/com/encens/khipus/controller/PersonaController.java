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
    private ClienteFacade clienteFacade;
    @EJB
    private InstitucionFacade institucionFacade;
    private List<Persona> items = null;
    private Persona selected;
    private Boolean esPersona;
    private Boolean tieneComision;
    private Boolean tieneGarantia;

    public PersonaController() {
    }

    public Persona getSelected() {
        /*if(selected == null)
            selected = items.get(0);*/
        //initEstados();
        return selected;
    }

    public void initEstados(){
        esPersona = false;
        if(selected.getRazonsocial() != null)
            esPersona = true;
        if(selected.getPorcentajeComision() > 0.0)
            tieneComision = false;
        else
            tieneComision = true;
        if(selected.getPorcentajeGarantia() > 0.0)
            tieneGarantia = false;
        else
            tieneGarantia = true;
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
        tieneComision = false;
        tieneGarantia = false;
        return selected;
    }

    public void prepareEdit() {
        esPersona = 
        tieneComision = selected.getPorcentajeComision() > 0.0;
        tieneGarantia = selected.getPorcentajeGarantia() >0.0;
    }


    public void create() {

        if(esPersona) {
            Cliente cliente = new Cliente();
            cliente.setAm(selected.getAm());
            cliente.setAp(selected.getAp());
            cliente.setNom(selected.getNom());
            cliente.setRazonsocial("");
            cliente.setPorcentajeComision(selected.getPorcentajeComision());
            cliente.setPorcentajeGarantia(selected.getPorcentajeGarantia());
            cliente.setNroDoc(selected.getNroDoc());
            cliente.setDireccion(selected.getDireccion());
            cliente.setNit(selected.getNit());
            cliente.setSexo(selected.getSexo());
            cliente.setTelefono(selected.getTelefono());
            cliente.setTipocliente(selected.getTipocliente());
            cliente.setTerritoriotrabajo(selected.getTerritoriotrabajo());
            clienteFacade.create(cliente);
        }
        else {
            Institucion institucion = new Institucion();
            institucion.setAm("");
            institucion.setAp("");
            institucion.setNom("");
            institucion.setSexo("");
            institucion.setRazonsocial(selected.getRazonsocial());
            institucion.setTerritoriotrabajo(selected.getTerritoriotrabajo());
            if(tieneComision)
                institucion.setPorcentajeComision(selected.getPorcentajeComision());
            else
                institucion.setPorcentajeComision(0.0);
            if(tieneGarantia)
                institucion.setPorcentajeGarantia(selected.getPorcentajeGarantia());
            else
                institucion.setPorcentajeGarantia(0.0);
            institucion.setDireccion(selected.getDireccion());
            institucion.setNit(selected.getNit());
            institucion.setTelefono(selected.getTelefono());
            institucion.setTipocliente(selected.getTipocliente());
            institucionFacade.create(institucion);
        }
        JSFUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClienteCreated"));
        if (!JSFUtil.isValidationFailed()) {
            prepareCreate();
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void cancel(){
        selected = null;
    }

    public void update() {
        if(!tieneComision)
            selected.setPorcentajeComision(0.0);
        if(!tieneGarantia)
            selected.setPorcentajeGarantia(0.0);

        if(esPersona) {
            selected.setTipoPersona("cliente");
            selected.setRazonsocial("");
        }
        else {
            selected.setTipoPersona("institucion");
            selected.setNom("");
            selected.setAp("");
            selected.setAm("");
        }

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
            items = getFacade().findAllClientesPersonaInstitucion();
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

    public Boolean getTieneComision() {
       /* if(selected.getPorcentajeComision() != null)
            tieneComision = true;*/
        return tieneComision;
    }

    public void setTieneComision(Boolean tieneComision) {
        this.tieneComision = tieneComision;
    }

    public Boolean getTieneGarantia() {
       /* if(selected.getPorcentajeGarantia() != null)
            tieneGarantia = true;*/
        return tieneGarantia;
    }

    public void setTieneGarantia(Boolean tieneGarantia) {
        this.tieneGarantia = tieneGarantia;
    }    
}
