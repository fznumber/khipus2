package encens.khipus.model.purchases;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Diego on 07/02/2015.
 */
@Entity
public class Institucion {
    private long idinstitucion;
    private String razonsocial;
    private Collection<Cliente> clientesByIdinstitucion;

    @Id
    @Column(name = "IDINSTITUCION", nullable = false, insertable = true, updatable = true)
    public long getIdinstitucion() {
        return idinstitucion;
    }

    public void setIdinstitucion(long idinstitucion) {
        this.idinstitucion = idinstitucion;
    }

    @Basic
    @Column(name = "RAZONSOCIAL", nullable = false, insertable = true, updatable = true, length = 100)
    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Institucion that = (Institucion) o;

        if (idinstitucion != that.idinstitucion) return false;
        if (razonsocial != null ? !razonsocial.equals(that.razonsocial) : that.razonsocial != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idinstitucion ^ (idinstitucion >>> 32));
        result = 31 * result + (razonsocial != null ? razonsocial.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "institucionByIdinstitucion")
    public Collection<Cliente> getClientesByIdinstitucion() {
        return clientesByIdinstitucion;
    }

    public void setClientesByIdinstitucion(Collection<Cliente> clientesByIdinstitucion) {
        this.clientesByIdinstitucion = clientesByIdinstitucion;
    }
}
