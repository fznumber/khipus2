package encens.khipus.model.purchases;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Diego on 07/02/2015.
 */
@Entity
public class Tipopedido {
    private long idtipopedido;
    private String nombre;
    private Collection<Pedidos> pedidosesByIdtipopedido;

    @Id
    @Column(name = "IDTIPOPEDIDO", nullable = false, insertable = true, updatable = true)
    public long getIdtipopedido() {
        return idtipopedido;
    }

    public void setIdtipopedido(long idtipopedido) {
        this.idtipopedido = idtipopedido;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, insertable = true, updatable = true, length = 20)
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

        Tipopedido that = (Tipopedido) o;

        if (idtipopedido != that.idtipopedido) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idtipopedido ^ (idtipopedido >>> 32));
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tipoPedido")
    public Collection<Pedidos> getPedidosesByIdtipopedido() {
        return pedidosesByIdtipopedido;
    }

    public void setPedidosesByIdtipopedido(Collection<Pedidos> pedidosesByIdtipopedido) {
        this.pedidosesByIdtipopedido = pedidosesByIdtipopedido;
    }
}
