package com.encens.khipus.showcase;

import com.encens.khipus.controller.InvArticulosController;
import com.encens.khipus.ejb.InvArticulosFacade;
import com.encens.khipus.model.InvArticulos;
import com.encens.khipus.model.Pedidos;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Diego on 09/02/2015.
 */
@FacesConverter(value = "convertirArticulos",forClass = InvArticulos.class)
public class InvArticuloControllerConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        InvArticulosController controller = (InvArticulosController) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "invArticulosController");

            return controller.getInvArticulos(value);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null ) {
            return null;
        }
        if (object instanceof InvArticulos) {
            InvArticulos o = (InvArticulos) object;
            if(o.getInvArticulosPK() == null)
                return null;
            return o.getInvArticulosPK().getCodArt();
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), InvArticulos.class.getName()});
            return null;
        }
    }
}
