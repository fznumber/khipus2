package encens.khipus.model.purchases;

import javax.persistence.*;

/**
 * Created by Diego on 09/02/2015.
 */
@Entity
public class Ventaarticulo {
    private long idventaarticulo;
    private Long precio;
    private Long precioespecial;
    private long idcliente;
    private String noCia;
    private String codArt;
    private Cliente clienteByIdcliente;
    private InvArticulos invArticulos;

    @Id
    @Column(name = "IDVENTAARTICULO", nullable = false, insertable = true, updatable = true)
    public long getIdventaarticulo() {
        return idventaarticulo;
    }

    public void setIdventaarticulo(long idventaarticulo) {
        this.idventaarticulo = idventaarticulo;
    }

    @Basic
    @Column(name = "PRECIO", nullable = true, insertable = true, updatable = true)
    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    @Basic
    @Column(name = "PRECIOESPECIAL", nullable = true, insertable = true, updatable = true)
    public Long getPrecioespecial() {
        return precioespecial;
    }

    public void setPrecioespecial(Long precioespecial) {
        this.precioespecial = precioespecial;
    }

    @Basic
    @Column(name = "IDCLIENTE", nullable = false, insertable = true, updatable = true)
    public long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
    }

    @Basic
    @Column(name = "NO_CIA", nullable = false, insertable = true, updatable = true, length = 2)
    public String getNoCia() {
        return noCia;
    }

    public void setNoCia(String noCia) {
        this.noCia = noCia;
    }

    @Basic
    @Column(name = "COD_ART", nullable = false, insertable = true, updatable = true, length = 6)
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

        Ventaarticulo that = (Ventaarticulo) o;

        if (idcliente != that.idcliente) return false;
        if (idventaarticulo != that.idventaarticulo) return false;
        if (codArt != null ? !codArt.equals(that.codArt) : that.codArt != null) return false;
        if (noCia != null ? !noCia.equals(that.noCia) : that.noCia != null) return false;
        if (precio != null ? !precio.equals(that.precio) : that.precio != null) return false;
        if (precioespecial != null ? !precioespecial.equals(that.precioespecial) : that.precioespecial != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idventaarticulo ^ (idventaarticulo >>> 32));
        result = 31 * result + (precio != null ? precio.hashCode() : 0);
        result = 31 * result + (precioespecial != null ? precioespecial.hashCode() : 0);
        result = 31 * result + (int) (idcliente ^ (idcliente >>> 32));
        result = 31 * result + (noCia != null ? noCia.hashCode() : 0);
        result = 31 * result + (codArt != null ? codArt.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "IDCLIENTE", referencedColumnName = "IDCLIENTE", nullable = false)
    public Cliente getClienteByIdcliente() {
        return clienteByIdcliente;
    }

    public void setClienteByIdcliente(Cliente clienteByIdcliente) {
        this.clienteByIdcliente = clienteByIdcliente;
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "NO_CIA", referencedColumnName = "NO_CIA", nullable = false), @JoinColumn(name = "COD_ART", referencedColumnName = "COD_ART", nullable = false)})
    public InvArticulos getInvArticulos() {
        return invArticulos;
    }

    public void setInvArticulos(InvArticulos invArticulos) {
        this.invArticulos = invArticulos;
    }
}
