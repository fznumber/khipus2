package encens.khipus.model.purchases;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Diego on 09/02/2015.
 */
public class InvArticulosPK implements Serializable {
    private String noCia;
    private String codArt;

    @Column(name = "NO_CIA", nullable = false, insertable = true, updatable = true, length = 2)
    @Id
    public String getNoCia() {
        return noCia;
    }

    public void setNoCia(String noCia) {
        this.noCia = noCia;
    }

    @Column(name = "COD_ART", nullable = false, insertable = true, updatable = true, length = 6)
    @Id
    public String getCodArt() {
        return codArt;
    }

    public void setCodArt(String codArt) {
        this.codArt = codArt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvArticulosPK that = (InvArticulosPK) o;

        if (codArt != null ? !codArt.equals(that.codArt) : that.codArt != null) return false;
        if (noCia != null ? !noCia.equals(that.noCia) : that.noCia != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = noCia != null ? noCia.hashCode() : 0;
        result = 31 * result + (codArt != null ? codArt.hashCode() : 0);
        return result;
    }
}
