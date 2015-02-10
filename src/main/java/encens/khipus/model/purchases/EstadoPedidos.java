package encens.khipus.model.purchases;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Diego on 07/02/2015.
 */
@Entity
@Table(name = "estado_pedidos", schema = "", catalog = "eos")
public class EstadoPedidos {
    private long idestadopedido;
    private String estado;
    private Collection<Pedidos> pedidos;

    @Id
    @Column(name = "IDESTADOPEDIDO", nullable = false, insertable = true, updatable = true)
    public long getIdestadopedido() {
        return idestadopedido;
    }

    public void setIdestadopedido(long idestadopedido) {
        this.idestadopedido = idestadopedido;
    }

    @Basic
    @Column(name = "ESTADO", nullable = false, insertable = true, updatable = true, length = 10)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstadoPedidos that = (EstadoPedidos) o;

        if (idestadopedido != that.idestadopedido) return false;
        if (estado != null ? !estado.equals(that.estado) : that.estado != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idestadopedido ^ (idestadopedido >>> 32));
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "estadoPedidos")
    public Collection<Pedidos> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Collection<Pedidos> pedidosesByIdestadopedido) {
        this.pedidos = pedidosesByIdestadopedido;
    }
}
