package com.encens.khipus.controller;

import com.encens.khipus.ejb.SfConfencFacade;
import com.encens.khipus.model.Pago;

import com.encens.khipus.ejb.PagoFacade;
import com.encens.khipus.model.Persona;
import com.encens.khipus.model.SfConfenc;
import com.encens.khipus.model.SfTmpenc;
import com.encens.khipus.util.JSFUtil.*;
import com.encens.khipus.util.JSFUtil;
import java.io.Serializable;
import java.sql.Timestamp;
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

@Named("pagoController")
@SessionScoped
public class PagoController implements Serializable {

    @EJB
    private com.encens.khipus.ejb.PagoFacade ejbFacade;
    @EJB
    SfConfencFacade sfConfencFacade;
    private List<Pago> items = null;
    private Pago selected;
    private Persona personaElegida;

    public PagoController() {
    }

    public Pago getSelected() {
        return selected;
    }

    public void setSelected(Pago selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PagoFacade getFacade() {
        return ejbFacade;
    }

    public Pago prepareCreate() {
        selected = new Pago();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        SfConfenc operacion= sfConfencFacade.getOperacion("PAGO");
        if(operacion == null)
        {
            JSFUtil.addErrorMessage("No se encuentra una operación registrada");
            return;
        }
        FacesContext facesContext = FacesContext.getCurrentInstance();
        LoginBean loginBean = (LoginBean) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "loginBean");
        selected.setPersona(personaElegida);
        selected.setFecha(new Timestamp(new Date().getTime()));
        selected.setUsuario(loginBean.getUsuario());

        SfTmpenc asiento = new SfTmpenc();
        asiento.setFecha(new Date());
        asiento.setNombreCliente(personaElegida.getNombreCompleto());
        asiento.setCliente(personaElegida);
        asiento.setGlosa(operacion.getGlosa()+" "+selected.getDescripcion());

        persist(PersistAction.CREATE, "El pago se registro correctamente");
        if (!JSFUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            personaElegida = null;
            selected = new Pago();
        }
    }

    public void update() {
        persist(PersistAction.UPDATE,"El pago se actualizo correctamente");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "El pago se elimino correctamente");
        if (!JSFUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Pago> getItems() {
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

    public Pago getPago(long id) {
        return getFacade().find(id);
    }

    public List<Pago> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Pago> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Pago.class)
    public static class PagoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PagoController controller = (PagoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pagoController");
            return controller.getPago(getKey(value));
        }

        long getKey(String value) {
            long key;
            key = Long.parseLong(value);
            return key;
        }

        String getStringKey(long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Pago) {
                Pago o = (Pago) object;
                return getStringKey(o.getIdPago());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Pago.class.getName()});
                return null;
            }
        }

    }

    public Persona getPersonaElegida() {
        return personaElegida;
    }

    public void setPersonaElegida(Persona personaElegida) {
        this.personaElegida = personaElegida;
    }
}
