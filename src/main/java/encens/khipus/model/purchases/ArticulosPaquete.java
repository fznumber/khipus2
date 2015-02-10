package encens.khipus.model.purchases;

import javax.persistence.*;

/**
 * Created by Diego on 07/02/2015.
 */
@Entity
@Table(name = "articulos_paquete", schema = "", catalog = "eos")
public class ArticulosPaquete {
    private long idarticulospaquete;
    private Long idCuenta;
    private String codArt;
    private String noCia;
    private Long cantidad;
    private String codAlm;
    private Double precio;
    private Double total;
    private Cliente cliente;
    private Paquetes paquete;

    @Id
    @Column(name = "IDARTICULOSPAQUETE", nullable = false, insertable = true, updatable = true)
    public long getIdarticulospaquete() {
        return idarticulospaquete;
    }

    public void setIdarticulospaquete(long idarticulospaquete) {
        this.idarticulospaquete = idarticulospaquete;
    }

    @Basic
    @Column(name = "ID_CUENTA", nullable = true, insertable = true, updatable = true)
    public Long getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
    }

    @Basic
    @Column(name = "COD_ART", nullable = true, insertable = true, updatable = true, length = 6)
    public String getCodArt() {
        return codArt;
    }

    public void setCodArt(String codArt) {
        this.codArt = codArt;
    }

    @Basic
    @Column(name = "NO_CIA", nullable = true, insertable = true, updatable = true, length = 2)
    public String getNoCia() {
        return noCia;
    }

    public void setNoCia(String noCia) {
        this.noCia = noCia;
    }

    @Basic
    @Column(name = "CANTIDAD", nullable = true, insertable = true, updatable = true)
    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    @Basic
    @Column(name = "COD_ALM", nullable = true, insertable = true, updatable = true, length = 6)
    public String getCodAlm() {
        return codAlm;
    }

    public void setCodAlm(String codAlm) {
        this.codAlm = codAlm;
    }

    @Basic
    @Column(name = "PRECIO", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Basic
    @Column(name = "TOTAL", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticulosPaquete that = (ArticulosPaquete) o;

        if (idarticulospaquete != that.idarticulospaquete) return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;
        if (codAlm != null ? !codAlm.equals(that.codAlm) : that.codAlm != null) return false;
        if (codArt != null ? !codArt.equals(that.codArt) : that.codArt != null) return false;
        if (idCuenta != null ? !idCuenta.equals(that.idCuenta) : that.idCuenta != null) return false;
        if (noCia != null ? !noCia.equals(that.noCia) : that.noCia != null) return false;
        if (precio != null ? !precio.equals(that.precio) : that.precio != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idarticulospaquete ^ (idarticulospaquete >>> 32));
        result = 31 * result + (idCuenta != null ? idCuenta.hashCode() : 0);
        result = 31 * result + (codArt != null ? codArt.hashCode() : 0);
        result = 31 * result + (noCia != null ? noCia.hashCode() : 0);
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        result = 31 * result + (codAlm != null ? codAlm.hashCode() : 0);
        result = 31 * result + (precio != null ? precio.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "IDCLIENTE", referencedColumnName = "IDCLIENTE", nullable = false)
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente clienteByIdcliente) {
        this.cliente = clienteByIdcliente;
    }

    @ManyToOne
    @JoinColumn(name = "IDPAQUETES", referencedColumnName = "IDPAQUETES")
    public Paquetes getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquetes paquetesByIdpaquetes) {
        this.paquete = paquetesByIdpaquetes;
    }
}
