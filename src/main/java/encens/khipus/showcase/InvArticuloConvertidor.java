package encens.khipus.showcase;

import encens.khipus.model.purchases.InvArticulos;
import encens.khipus.services.purchases.ServiceInvArticulos;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Created by Diego on 09/02/2015.
 */
@FacesConverter("convertidorArticulos")
public class InvArticuloConvertidor implements Converter {
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                ServiceInvArticulos service = (ServiceInvArticulos) fc.getExternalContext().getApplicationMap().get("serviceInvArticulos");
                return service.finByCodArt(value);
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Articulo no disponible."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
        if(object != null) {
            return String.valueOf(((InvArticulos) object).getCodArt());
        }
        else {
            return null;
        }
    }
}
