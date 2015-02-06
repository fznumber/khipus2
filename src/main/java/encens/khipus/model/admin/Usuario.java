package encens.khipus.model.admin;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Diego on 06/02/2015.
 */
@Entity
public class Usuario {
    private long idusuario;
    private Timestamp fechacreacion;
    private String email;
    private String numerousuario;
    private Integer usuariofinanzas;
    private String clave;
    private String usuario;
    private Long version;
    private Compania companiaByIdcompania;

    @Id
    @Column(name = "IDUSUARIO", nullable = false, insertable = true, updatable = true)
    public long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(long idusuario) {
        this.idusuario = idusuario;
    }

    @Basic
    @Column(name = "FECHACREACION", nullable = false, insertable = true, updatable = true)
    public Timestamp getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Timestamp fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    @Basic
    @Column(name = "EMAIL", nullable = true, insertable = true, updatable = true, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "NUMEROUSUARIO", nullable = true, insertable = true, updatable = true, length = 4)
    public String getNumerousuario() {
        return numerousuario;
    }

    public void setNumerousuario(String numerousuario) {
        this.numerousuario = numerousuario;
    }

    @Basic
    @Column(name = "USUARIOFINANZAS", nullable = true, insertable = true, updatable = true)
    public Integer getUsuariofinanzas() {
        return usuariofinanzas;
    }

    public void setUsuariofinanzas(Integer usuariofinanzas) {
        this.usuariofinanzas = usuariofinanzas;
    }

    @Basic
    @Column(name = "CLAVE", nullable = true, insertable = true, updatable = true, length = 200)
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Basic
    @Column(name = "USUARIO", nullable = true, insertable = true, updatable = true, length = 50)
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Basic
    @Column(name = "VERSION", nullable = true, insertable = true, updatable = true)
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario1 = (Usuario) o;

        if (idusuario != usuario1.idusuario) return false;
        if (clave != null ? !clave.equals(usuario1.clave) : usuario1.clave != null) return false;
        if (email != null ? !email.equals(usuario1.email) : usuario1.email != null) return false;
        if (fechacreacion != null ? !fechacreacion.equals(usuario1.fechacreacion) : usuario1.fechacreacion != null)
            return false;
        if (numerousuario != null ? !numerousuario.equals(usuario1.numerousuario) : usuario1.numerousuario != null)
            return false;
        if (usuario != null ? !usuario.equals(usuario1.usuario) : usuario1.usuario != null) return false;
        if (usuariofinanzas != null ? !usuariofinanzas.equals(usuario1.usuariofinanzas) : usuario1.usuariofinanzas != null)
            return false;
        if (version != null ? !version.equals(usuario1.version) : usuario1.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idusuario ^ (idusuario >>> 32));
        result = 31 * result + (fechacreacion != null ? fechacreacion.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (numerousuario != null ? numerousuario.hashCode() : 0);
        result = 31 * result + (usuariofinanzas != null ? usuariofinanzas.hashCode() : 0);
        result = 31 * result + (clave != null ? clave.hashCode() : 0);
        result = 31 * result + (usuario != null ? usuario.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "IDCOMPANIA", referencedColumnName = "IDCOMPANIA")
    public Compania getCompaniaByIdcompania() {
        return companiaByIdcompania;
    }

    public void setCompaniaByIdcompania(Compania companiaByIdcompania) {
        this.companiaByIdcompania = companiaByIdcompania;
    }
}
