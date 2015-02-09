package encens.khipus.model.purchases;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Diego on 08/02/2015.
 */
@Entity
public class Distribuidor {
    private long iddistribuidor;
    private String observacion;
    private Collection<Pedidos> pedidosesByIddistribuidor;

    @Id
    @Column(name = "IDDISTRIBUIDOR", nullable = false, insertable = true, updatable = true)
    public long getIddistribuidor() {
        return iddistribuidor;
    }

    public void setIddistribuidor(long iddistribuidor) {
        this.iddistribuidor = iddistribuidor;
    }

    @Basic
    @Column(name = "OBSERVACION", nullable = true, insertable = true, updatable = true, length = 50)
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Distribuidor that = (Distribuidor) o;

        if (iddistribuidor != that.iddistribuidor) return false;
        if (observacion != null ? !observacion.equals(that.observacion) : that.observacion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (iddistribuidor ^ (iddistribuidor >>> 32));
        result = 31 * result + (observacion != null ? observacion.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "distribuidorByIddistribuidor")
    public Collection<Pedidos> getPedidosesByIddistribuidor() {
        return pedidosesByIddistribuidor;
    }

    public void setPedidosesByIddistribuidor(Collection<Pedidos> pedidosesByIddistribuidor) {
        this.pedidosesByIddistribuidor = pedidosesByIddistribuidor;
    }
}
