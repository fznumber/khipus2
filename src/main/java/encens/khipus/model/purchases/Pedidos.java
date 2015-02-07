package encens.khipus.model.purchases;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by Diego on 07/02/2015.
 */
@Entity
public class Pedidos {
    private long idpedidos;
    private String descripcion;
    private long estadoPedido;
    private String tipoPedido;
    private String id;
    private Date fechaPedido;
    private long idempleado;
    private Long iddireccion;
    private Long idzona;
    private long id1;
    private double total;
    private Date fechaEntrega;
    private Date fechaAPagar;
    private String observacion;
    private String factura;
    private Long supervisor;
    private Long distribuidor;
    private Long porcenDescuento;
    private Long porcenRetencion;
    private long tipopedidoIdtipopedido;
    private long idcliente;
    private Collection<ArticulosPedido> articulosPedidosByIdpedidos;
    private Cliente clienteByIdcliente;
    private Empleado empleadoByIdempleado;
    private EstadoPedidos estadoPedidosByEstadoPedido;
    private Tipopedido tipopedidoByTipopedidoIdtipopedido;

    @Id
    @Column(name = "IDPEDIDOS", nullable = false, insertable = true, updatable = true)
    public long getIdpedidos() {
        return idpedidos;
    }

    public void setIdpedidos(long idpedidos) {
        this.idpedidos = idpedidos;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = true, insertable = true, updatable = true, length = 200)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "ESTADO_PEDIDO", nullable = false, insertable = true, updatable = true)
    public long getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(long estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    @Basic
    @Column(name = "TIPO_PEDIDO", nullable = false, insertable = true, updatable = true, length = 10)
    public String getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
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
    @Column(name = "FECHA_PEDIDO", nullable = false, insertable = true, updatable = true)
    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    @Basic
    @Column(name = "IDEMPLEADO", nullable = false, insertable = true, updatable = true)
    public long getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(long idempleado) {
        this.idempleado = idempleado;
    }

    @Basic
    @Column(name = "IDDIRECCION", nullable = true, insertable = true, updatable = true)
    public Long getIddireccion() {
        return iddireccion;
    }

    public void setIddireccion(Long iddireccion) {
        this.iddireccion = iddireccion;
    }

    @Basic
    @Column(name = "IDZONA", nullable = true, insertable = true, updatable = true)
    public Long getIdzona() {
        return idzona;
    }

    public void setIdzona(Long idzona) {
        this.idzona = idzona;
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
    @Column(name = "TOTAL", nullable = false, insertable = true, updatable = true, precision = 2)
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Basic
    @Column(name = "FECHA_ENTREGA", nullable = true, insertable = true, updatable = true)
    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    @Basic
    @Column(name = "FECHA_A_PAGAR", nullable = false, insertable = true, updatable = true)
    public Date getFechaAPagar() {
        return fechaAPagar;
    }

    public void setFechaAPagar(Date fechaAPagar) {
        this.fechaAPagar = fechaAPagar;
    }

    @Basic
    @Column(name = "OBSERVACION", nullable = true, insertable = true, updatable = true, length = 100)
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Basic
    @Column(name = "FACTURA", nullable = false, insertable = true, updatable = true, length = 2)
    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    @Basic
    @Column(name = "SUPERVISOR", nullable = true, insertable = true, updatable = true)
    public Long getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Long supervisor) {
        this.supervisor = supervisor;
    }

    @Basic
    @Column(name = "DISTRIBUIDOR", nullable = true, insertable = true, updatable = true)
    public Long getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(Long distribuidor) {
        this.distribuidor = distribuidor;
    }

    @Basic
    @Column(name = "PORCEN_DESCUENTO", nullable = true, insertable = true, updatable = true)
    public Long getPorcenDescuento() {
        return porcenDescuento;
    }

    public void setPorcenDescuento(Long porcenDescuento) {
        this.porcenDescuento = porcenDescuento;
    }

    @Basic
    @Column(name = "PORCEN_RETENCION", nullable = true, insertable = true, updatable = true)
    public Long getPorcenRetencion() {
        return porcenRetencion;
    }

    public void setPorcenRetencion(Long porcenRetencion) {
        this.porcenRetencion = porcenRetencion;
    }

    @Basic
    @Column(name = "TIPOPEDIDO_IDTIPOPEDIDO", nullable = false, insertable = true, updatable = true)
    public long getTipopedidoIdtipopedido() {
        return tipopedidoIdtipopedido;
    }

    public void setTipopedidoIdtipopedido(long tipopedidoIdtipopedido) {
        this.tipopedidoIdtipopedido = tipopedidoIdtipopedido;
    }

    @Basic
    @Column(name = "IDCLIENTE", nullable = false, insertable = true, updatable = true)
    public long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pedidos pedidos = (Pedidos) o;

        if (estadoPedido != pedidos.estadoPedido) return false;
        if (id1 != pedidos.id1) return false;
        if (idcliente != pedidos.idcliente) return false;
        if (idempleado != pedidos.idempleado) return false;
        if (idpedidos != pedidos.idpedidos) return false;
        if (tipopedidoIdtipopedido != pedidos.tipopedidoIdtipopedido) return false;
        if (Double.compare(pedidos.total, total) != 0) return false;
        if (descripcion != null ? !descripcion.equals(pedidos.descripcion) : pedidos.descripcion != null) return false;
        if (distribuidor != null ? !distribuidor.equals(pedidos.distribuidor) : pedidos.distribuidor != null)
            return false;
        if (factura != null ? !factura.equals(pedidos.factura) : pedidos.factura != null) return false;
        if (fechaAPagar != null ? !fechaAPagar.equals(pedidos.fechaAPagar) : pedidos.fechaAPagar != null) return false;
        if (fechaEntrega != null ? !fechaEntrega.equals(pedidos.fechaEntrega) : pedidos.fechaEntrega != null)
            return false;
        if (fechaPedido != null ? !fechaPedido.equals(pedidos.fechaPedido) : pedidos.fechaPedido != null) return false;
        if (id != null ? !id.equals(pedidos.id) : pedidos.id != null) return false;
        if (iddireccion != null ? !iddireccion.equals(pedidos.iddireccion) : pedidos.iddireccion != null) return false;
        if (idzona != null ? !idzona.equals(pedidos.idzona) : pedidos.idzona != null) return false;
        if (observacion != null ? !observacion.equals(pedidos.observacion) : pedidos.observacion != null) return false;
        if (porcenDescuento != null ? !porcenDescuento.equals(pedidos.porcenDescuento) : pedidos.porcenDescuento != null)
            return false;
        if (porcenRetencion != null ? !porcenRetencion.equals(pedidos.porcenRetencion) : pedidos.porcenRetencion != null)
            return false;
        if (supervisor != null ? !supervisor.equals(pedidos.supervisor) : pedidos.supervisor != null) return false;
        if (tipoPedido != null ? !tipoPedido.equals(pedidos.tipoPedido) : pedidos.tipoPedido != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (idpedidos ^ (idpedidos >>> 32));
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (int) (estadoPedido ^ (estadoPedido >>> 32));
        result = 31 * result + (tipoPedido != null ? tipoPedido.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (fechaPedido != null ? fechaPedido.hashCode() : 0);
        result = 31 * result + (int) (idempleado ^ (idempleado >>> 32));
        result = 31 * result + (iddireccion != null ? iddireccion.hashCode() : 0);
        result = 31 * result + (idzona != null ? idzona.hashCode() : 0);
        result = 31 * result + (int) (id1 ^ (id1 >>> 32));
        temp = Double.doubleToLongBits(total);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (fechaEntrega != null ? fechaEntrega.hashCode() : 0);
        result = 31 * result + (fechaAPagar != null ? fechaAPagar.hashCode() : 0);
        result = 31 * result + (observacion != null ? observacion.hashCode() : 0);
        result = 31 * result + (factura != null ? factura.hashCode() : 0);
        result = 31 * result + (supervisor != null ? supervisor.hashCode() : 0);
        result = 31 * result + (distribuidor != null ? distribuidor.hashCode() : 0);
        result = 31 * result + (porcenDescuento != null ? porcenDescuento.hashCode() : 0);
        result = 31 * result + (porcenRetencion != null ? porcenRetencion.hashCode() : 0);
        result = 31 * result + (int) (tipopedidoIdtipopedido ^ (tipopedidoIdtipopedido >>> 32));
        result = 31 * result + (int) (idcliente ^ (idcliente >>> 32));
        return result;
    }

    @OneToMany(mappedBy = "pedidosByPedido")
    public Collection<ArticulosPedido> getArticulosPedidosByIdpedidos() {
        return articulosPedidosByIdpedidos;
    }

    public void setArticulosPedidosByIdpedidos(Collection<ArticulosPedido> articulosPedidosByIdpedidos) {
        this.articulosPedidosByIdpedidos = articulosPedidosByIdpedidos;
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
    @JoinColumn(name = "IDEMPLEADO", referencedColumnName = "IDEMPLEADO", nullable = false)
    public Empleado getEmpleadoByIdempleado() {
        return empleadoByIdempleado;
    }

    public void setEmpleadoByIdempleado(Empleado empleadoByIdempleado) {
        this.empleadoByIdempleado = empleadoByIdempleado;
    }

    @ManyToOne
    @JoinColumn(name = "ESTADO_PEDIDO", referencedColumnName = "IDESTADOPEDIDO", nullable = false)
    public EstadoPedidos getEstadoPedidosByEstadoPedido() {
        return estadoPedidosByEstadoPedido;
    }

    public void setEstadoPedidosByEstadoPedido(EstadoPedidos estadoPedidosByEstadoPedido) {
        this.estadoPedidosByEstadoPedido = estadoPedidosByEstadoPedido;
    }

    @ManyToOne
    @JoinColumn(name = "TIPOPEDIDO_IDTIPOPEDIDO", referencedColumnName = "IDTIPOPEDIDO", nullable = false)
    public Tipopedido getTipopedidoByTipopedidoIdtipopedido() {
        return tipopedidoByTipopedidoIdtipopedido;
    }

    public void setTipopedidoByTipopedidoIdtipopedido(Tipopedido tipopedidoByTipopedidoIdtipopedido) {
        this.tipopedidoByTipopedidoIdtipopedido = tipopedidoByTipopedidoIdtipopedido;
    }
}
