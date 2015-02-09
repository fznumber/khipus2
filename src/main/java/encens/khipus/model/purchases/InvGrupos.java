package encens.khipus.model.purchases;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Diego on 09/02/2015.
 */
@Entity
@Table(name = "inv_grupos", schema = "", catalog = "eos")
@IdClass(InvGruposPK.class)
public class InvGrupos {
    private String noCia;
    private String codGru;
    private String cuentaInv;
    private String descri;
    private Long version;
    private String tipo;
    private Collection<InvArticulos> invArticuloses;

    @Id
    @Column(name = "NO_CIA", nullable = false, insertable = true, updatable = true, length = 2)
    public String getNoCia() {
        return noCia;
    }

    public void setNoCia(String noCia) {
        this.noCia = noCia;
    }

    @Id
    @Column(name = "COD_GRU", nullable = false, insertable = true, updatable = true, length = 3)
    public String getCodGru() {
        return codGru;
    }

    public void setCodGru(String codGru) {
        this.codGru = codGru;
    }

    @Basic
    @Column(name = "CUENTA_INV", nullable = true, insertable = true, updatable = true, length = 31)
    public String getCuentaInv() {
        return cuentaInv;
    }

    public void setCuentaInv(String cuentaInv) {
        this.cuentaInv = cuentaInv;
    }

    @Basic
    @Column(name = "DESCRI", nullable = true, insertable = true, updatable = true, length = 100)
    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    @Basic
    @Column(name = "VERSION", nullable = true, insertable = true, updatable = true)
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Basic
    @Column(name = "TIPO", nullable = true, insertable = true, updatable = true, length = 50)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvGrupos invGrupos = (InvGrupos) o;

        if (codGru != null ? !codGru.equals(invGrupos.codGru) : invGrupos.codGru != null) return false;
        if (cuentaInv != null ? !cuentaInv.equals(invGrupos.cuentaInv) : invGrupos.cuentaInv != null) return false;
        if (descri != null ? !descri.equals(invGrupos.descri) : invGrupos.descri != null) return false;
        if (noCia != null ? !noCia.equals(invGrupos.noCia) : invGrupos.noCia != null) return false;
        if (tipo != null ? !tipo.equals(invGrupos.tipo) : invGrupos.tipo != null) return false;
        if (version != null ? !version.equals(invGrupos.version) : invGrupos.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = noCia != null ? noCia.hashCode() : 0;
        result = 31 * result + (codGru != null ? codGru.hashCode() : 0);
        result = 31 * result + (cuentaInv != null ? cuentaInv.hashCode() : 0);
        result = 31 * result + (descri != null ? descri.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "invGrupos")
    public Collection<InvArticulos> getInvArticuloses() {
        return invArticuloses;
    }

    public void setInvArticuloses(Collection<InvArticulos> invArticuloses) {
        this.invArticuloses = invArticuloses;
    }
}
