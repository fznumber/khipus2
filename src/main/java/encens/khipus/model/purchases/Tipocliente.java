package encens.khipus.model.purchases;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Diego on 07/02/2015.
 */
@Entity
public class Tipocliente {
    private long idtipocliente;
    private String nombre;
    private Collection<Cliente> clientesByIdtipocliente;

    @Id
    @Column(name = "IDTIPOCLIENTE", nullable = false, insertable = true, updatable = true)
    public long getIdtipocliente() {
        return idtipocliente;
    }

    public void setIdtipocliente(long idtipocliente) {
        this.idtipocliente = idtipocliente;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, insertable = true, updatable = true, length = 50)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tipocliente that = (Tipocliente) o;

        if (idtipocliente != that.idtipocliente) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idtipocliente ^ (idtipocliente >>> 32));
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tipoclienteByIdtipocliente")
    public Collection<Cliente> getClientesByIdtipocliente() {
        return clientesByIdtipocliente;
    }

    public void setClientesByIdtipocliente(Collection<Cliente> clientesByIdtipocliente) {
        this.clientesByIdtipocliente = clientesByIdtipocliente;
    }
}
