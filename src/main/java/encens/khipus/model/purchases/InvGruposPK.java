package encens.khipus.model.purchases;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Diego on 09/02/2015.
 */
public class InvGruposPK implements Serializable {
    private String noCia;
    private String codGru;

    @Column(name = "NO_CIA", nullable = false, insertable = true, updatable = true, length = 2)
    @Id
    public String getNoCia() {
        return noCia;
    }

    public void setNoCia(String noCia) {
        this.noCia = noCia;
    }

    @Column(name = "COD_GRU", nullable = false, insertable = true, updatable = true, length = 3)
    @Id
    public String getCodGru() {
        return codGru;
    }

    public void setCodGru(String codGru) {
        this.codGru = codGru;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvGruposPK that = (InvGruposPK) o;

        if (codGru != null ? !codGru.equals(that.codGru) : that.codGru != null) return false;
        if (noCia != null ? !noCia.equals(that.noCia) : that.noCia != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = noCia != null ? noCia.hashCode() : 0;
        result = 31 * result + (codGru != null ? codGru.hashCode() : 0);
        return result;
    }
}
