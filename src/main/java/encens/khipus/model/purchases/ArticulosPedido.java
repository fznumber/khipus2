package encens.khipus.model.purchases;

import javax.persistence.*;

/**
 * Created by Diego on 07/02/2015.
 */
@Entity
@Table(name = "articulos_pedido", schema = "", catalog = "eos")
public class ArticulosPedido {
    private long idarticulospedido;
    private long idCuenta;
    private String codArt;
    private String noCia;
    private long pedido;
    private String id;
    private long id1;
    private Long cantidad;
    private String codAlm;
    private Double precio;
    private Long reposicion;
    private Double total;
    private Long promocion;
    private Long caja;
    private Long precioInv;
    private Long totalInv;
    private Pedidos pedidosByPedido;
    private String nombre;

    @Id
    @Column(name = "IDARTICULOSPEDIDO", nullable = false, insertable = true, updatable = true)
    public long getIdarticulospedido() {
        return idarticulospedido;
    }

    public void setIdarticulospedido(long idarticulospedido) {
        this.idarticulospedido = idarticulospedido;
    }

    @Transient
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }




    @Basic
    @Column(name = "ID_CUENTA", nullable = false, insertable = true, updatable = true)
    public long getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(long idCuenta) {
        this.idCuenta = idCuenta;
    }

    @Basic
    @Column(name = "COD_ART", nullable = false, insertable = true, updatable = true, length = 6)
    public String getCodArt() {
        return codArt;
    }

    public void setCodArt(String codArt) {
        this.codArt = codArt;
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
    @Column(name = "PEDIDO", nullable = false, insertable = true, updatable = true)
    public long getPedido() {
        return pedido;
    }

    public void setPedido(long pedido) {
        this.pedido = pedido;
    }

    @Basic
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 20)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ID1", nullable = false, insertable = true, updatable = true)
    public long getId1() {
        return id1;
    }

    public void setId1(long id1) {
        this.id1 = id1;
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
    @Column(name = "REPOSICION", nullable = true, insertable = true, updatable = true)
    public Long getReposicion() {
        return reposicion;
    }

    public void setReposicion(Long reposicion) {
        this.reposicion = reposicion;
    }

    @Basic
    @Column(name = "TOTAL", nullable = true, insertable = true, updatable = true, precision = 2)
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Basic
    @Column(name = "PROMOCION", nullable = true, insertable = true, updatable = true)
    public Long getPromocion() {
        return promocion;
    }

    public void setPromocion(Long promocion) {
        this.promocion = promocion;
    }

    @Basic
    @Column(name = "CAJA", nullable = true, insertable = true, updatable = true)
    public Long getCaja() {
        return caja;
    }

    public void setCaja(Long caja) {
        this.caja = caja;
    }

    @Basic
    @Column(name = "PRECIO_INV", nullable = true, insertable = true, updatable = true)
    public Long getPrecioInv() {
        return precioInv;
    }

    public void setPrecioInv(Long precioInv) {
        this.precioInv = precioInv;
    }

    @Basic
    @Column(name = "TOTAL_INV", nullable = true, insertable = true, updatable = true)
    public Long getTotalInv() {
        return totalInv;
    }

    public void setTotalInv(Long totalInv) {
        this.totalInv = totalInv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticulosPedido that = (ArticulosPedido) o;

        if (id1 != that.id1) return false;
        if (idCuenta != that.idCuenta) return false;
        if (idarticulospedido != that.idarticulospedido) return false;
        if (pedido != that.pedido) return false;
        if (caja != null ? !caja.equals(that.caja) : that.caja != null) return false;
        if (cantidad != null ? !cantidad.equals(that.cantidad) : that.cantidad != null) return false;
        if (codAlm != null ? !codAlm.equals(that.codAlm) : that.codAlm != null) return false;
        if (codArt != null ? !codArt.equals(that.codArt) : that.codArt != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (noCia != null ? !noCia.equals(that.noCia) : that.noCia != null) return false;
        if (precio != null ? !precio.equals(that.precio) : that.precio != null) return false;
        if (precioInv != null ? !precioInv.equals(that.precioInv) : that.precioInv != null) return false;
        if (promocion != null ? !promocion.equals(that.promocion) : that.promocion != null) return false;
        if (reposicion != null ? !reposicion.equals(that.reposicion) : that.reposicion != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (totalInv != null ? !totalInv.equals(that.totalInv) : that.totalInv != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idarticulospedido ^ (idarticulospedido >>> 32));
        result = 31 * result + (int) (idCuenta ^ (idCuenta >>> 32));
        result = 31 * result + (codArt != null ? codArt.hashCode() : 0);
        result = 31 * result + (noCia != null ? noCia.hashCode() : 0);
        result = 31 * result + (int) (pedido ^ (pedido >>> 32));
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (int) (id1 ^ (id1 >>> 32));
        result = 31 * result + (cantidad != null ? cantidad.hashCode() : 0);
        result = 31 * result + (codAlm != null ? codAlm.hashCode() : 0);
        result = 31 * result + (precio != null ? precio.hashCode() : 0);
        result = 31 * result + (reposicion != null ? reposicion.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (promocion != null ? promocion.hashCode() : 0);
        result = 31 * result + (caja != null ? caja.hashCode() : 0);
        result = 31 * result + (precioInv != null ? precioInv.hashCode() : 0);
        result = 31 * result + (totalInv != null ? totalInv.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "PEDIDO", referencedColumnName = "IDPEDIDOS", nullable = false)
    public Pedidos getPedidosByPedido() {
        return pedidosByPedido;
    }

    public void setPedidosByPedido(Pedidos pedidosByPedido) {
        this.pedidosByPedido = pedidosByPedido;
    }
}
