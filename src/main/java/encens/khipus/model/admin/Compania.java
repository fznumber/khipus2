package encens.khipus.model.admin;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Diego on 06/02/2015.
 */
@Entity
public class Compania {
    private long idcompania;
    private String codigo;
    private Collection<Usuario> usuariosByIdcompania;

    @Id
    @Column(name = "IDCOMPANIA", nullable = false, insertable = true, updatable = true)
    public long getIdcompania() {
        return idcompania;
    }

    public void setIdcompania(long idcompania) {
        this.idcompania = idcompania;
    }

    @Basic
    @Column(name = "CODIGO", nullable = true, insertable = true, updatable = true, length = 255)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Compania compania = (Compania) o;

        if (idcompania != compania.idcompania) return false;
        if (codigo != null ? !codigo.equals(compania.codigo) : compania.codigo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idcompania ^ (idcompania >>> 32));
        result = 31 * result + (codigo != null ? codigo.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "companiaByIdcompania")
    public Collection<Usuario> getUsuariosByIdcompania() {
        return usuariosByIdcompania;
    }

    public void setUsuariosByIdcompania(Collection<Usuario> usuariosByIdcompania) {
        this.usuariosByIdcompania = usuariosByIdcompania;
    }
}
