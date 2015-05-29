package com.encens.khipus.controller;

import com.encens.khipus.model.SfTmpenc;
import com.encens.khipus.ejb.SfTmpencFacade;
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

@Named("sfTmpencController")
@SessionScoped
public class SfTmpencController implements Serializable {

    @EJB
    private com.encens.khipus.ejb.SfTmpencFacade ejbFacade;
    private List<SfTmpenc> items = null;
    private SfTmpenc selected;

    public SfTmpencController() {
    }

    public SfTmpenc getSelected() {
        return selected;
    }

    public void setSelected(SfTmpenc selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SfTmpencFacade getFacade() {
        return ejbFacade;
    }

    public SfTmpenc prepareCreate() {
        selected = new SfTmpenc();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SfTmpencCreated"));
        if (!JSFUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SfTmpencUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SfTmpencDeleted"));
        if (!JSFUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SfTmpenc> getItems() {
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

    public SfTmpenc getSfTmpenc(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<SfTmpenc> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SfTmpenc> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public void createGeneral() {
        persist(PersistAction.CREATE, "");
    }


    @FacesConverter(forClass = SfTmpenc.class)
    public static class SfTmpencControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SfTmpencController controller = (SfTmpencController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "sfTmpencController");
            return controller.getSfTmpenc(getKey(value));
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
            if (object instanceof SfTmpenc) {
                SfTmpenc o = (SfTmpenc) object;
                return getStringKey(o.getIdTmpenc());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SfTmpenc.class.getName()});
                return null;
            }
        }

    }

}