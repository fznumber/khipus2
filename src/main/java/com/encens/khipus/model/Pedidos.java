/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "pedidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedidos.findAll", query = "SELECT p FROM Pedidos p"),
    @NamedQuery(name = "Pedidos.findByIdpedidos", query = "SELECT p FROM Pedidos p WHERE p.idpedidos = :idpedidos"),
    @NamedQuery(name = "Pedidos.findByDescripcion", query = "SELECT p FROM Pedidos p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Pedidos.findByTipoPedido", query = "SELECT p FROM Pedidos p WHERE p.tipoPedido = :tipoPedido"),
    @NamedQuery(name = "Pedidos.findById", query = "SELECT p FROM Pedidos p WHERE p.id = :id"),
    @NamedQuery(name = "Pedidos.findByFechaPedido", query = "SELECT p FROM Pedidos p WHERE p.fechaPedido = :fechaPedido"),
    @NamedQuery(name = "Pedidos.findByIddireccion", query = "SELECT p FROM Pedidos p WHERE p.iddireccion = :iddireccion"),
    @NamedQuery(name = "Pedidos.findByIdzona", query = "SELECT p FROM Pedidos p WHERE p.idzona = :idzona"),
    @NamedQuery(name = "Pedidos.findById1", query = "SELECT p FROM Pedidos p WHERE p.id1 = :id1"),
    @NamedQuery(name = "Pedidos.findByTotal", query = "SELECT p FROM Pedidos p WHERE p.total = :total"),
    @NamedQuery(name = "Pedidos.findByFechaEntrega", query = "SELECT p FROM Pedidos p WHERE p.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "Pedidos.findByFechaAPagar", query = "SELECT p FROM Pedidos p WHERE p.fechaAPagar = :fechaAPagar"),
    @NamedQuery(name = "Pedidos.findByObservacion", query = "SELECT p FROM Pedidos p WHERE p.observacion = :observacion"),
    @NamedQuery(name = "Pedidos.findByFactura", query = "SELECT p FROM Pedidos p WHERE p.factura = :factura"),
    @NamedQuery(name = "Pedidos.findBySupervisor", query = "SELECT p FROM Pedidos p WHERE p.supervisor = :supervisor"),
    @NamedQuery(name = "Pedidos.findByPorcenDescuento", query = "SELECT p FROM Pedidos p WHERE p.porcenDescuento = :porcenDescuento"),
    @NamedQuery(name = "Pedidos.findByPorcenRetencion", query = "SELECT p FROM Pedidos p WHERE p.porcenRetencion = :porcenRetencion")})
public class Pedidos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "IDPEDIDOS")
    private Long idpedidos;
    @Size(max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "TIPO_PEDIDO")
    private String tipoPedido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_PEDIDO")
    @Temporal(TemporalType.DATE)
    private Date fechaPedido;
    @Column(name = "IDDIRECCION")
    private BigInteger iddireccion;
    @Column(name = "IDZONA")
    private BigInteger idzona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID1")
    private long id1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private double total;
    @Column(name = "FECHA_ENTREGA")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_A_PAGAR")
    @Temporal(TemporalType.DATE)
    private Date fechaAPagar;
    @Size(max = 100)
    @Column(name = "OBSERVACION")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "FACTURA")
    private String factura;
    @Column(name = "SUPERVISOR")
    private BigInteger supervisor;
    @Column(name = "PORCEN_DESCUENTO")
    private Double porcenDescuento;
    @Column(name = "PORCEN_RETENCION")
    private Double porcenRetencion;
    @Column(name = "ESTADO")
    private String estado;
    @JoinColumn(name = "IDCLIENTE", referencedColumnName = "IDPERSONA")
    @ManyToOne(optional = false)
    private Cliente idcliente;
    @JoinColumn(name = "IDDISTRIBUIDOR", referencedColumnName = "IDPERSONA")
    @ManyToOne(optional = false)
    private Distribuidor iddistribuidor;
    @JoinColumn(name = "IDEMPLEADO", referencedColumnName = "IDEMPLEADO")
    @ManyToOne(optional = false)
    private Empleado idempleado;
    @JoinColumn(name = "ESTADO_PEDIDO", referencedColumnName = "IDESTADOPEDIDO")
    @ManyToOne(optional = false)
    private EstadoPedidos estadoPedido;
    @JoinColumn(name = "IDTIPOPEDIDO", referencedColumnName = "IDTIPOPEDIDO")
    @ManyToOne(optional = false)
    private Tipopedido idtipopedido;

    public Pedidos() {
    }

    public Pedidos(Long idpedidos) {
        this.idpedidos = idpedidos;
    }

    public Pedidos(Long idpedidos, String tipoPedido, String id, Date fechaPedido, long id1, double total, Date fechaAPagar, String factura) {
        this.idpedidos = idpedidos;
        this.tipoPedido = tipoPedido;
        this.id = id;
        this.fechaPedido = fechaPedido;
        this.id1 = id1;
        this.total = total;
        this.fechaAPagar = fechaAPagar;
        this.factura = factura;
    }

    public Long getIdpedidos() {
        return idpedidos;
    }

    public void setIdpedidos(Long idpedidos) {
        this.idpedidos = idpedidos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public BigInteger getIddireccion() {
        return iddireccion;
    }

    public void setIddireccion(BigInteger iddireccion) {
        this.iddireccion = iddireccion;
    }

    public BigInteger getIdzona() {
        return idzona;
    }

    public void setIdzona(BigInteger idzona) {
        this.idzona = idzona;
    }

    public long getId1() {
        return id1;
    }

    public void setId1(long id1) {
        this.id1 = id1;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaAPagar() {
        return fechaAPagar;
    }

    public void setFechaAPagar(Date fechaAPagar) {
        this.fechaAPagar = fechaAPagar;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public BigInteger getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(BigInteger supervisor) {
        this.supervisor = supervisor;
    }

    public Double getPorcenDescuento() {
        return porcenDescuento;
    }

    public void setPorcenDescuento(Double porcenDescuento) {
        this.porcenDescuento = porcenDescuento;
    }

    public Double getPorcenRetencion() {
        return porcenRetencion;
    }

    public void setPorcenRetencion(Double porcenRetencion) {
        this.porcenRetencion = porcenRetencion;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    public Distribuidor getIddistribuidor() {
        return iddistribuidor;
    }

    public void setIddistribuidor(Distribuidor iddistribuidor) {
        this.iddistribuidor = iddistribuidor;
    }

    public Empleado getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Empleado idempleado) {
        this.idempleado = idempleado;
    }

    public EstadoPedidos getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedidos estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Tipopedido getIdtipopedido() {
        return idtipopedido;
    }

    public void setIdtipopedido(Tipopedido idtipopedido) {
        this.idtipopedido = idtipopedido;
    }

    public String getEstado() {
        if(estado == null)
        {
            estado = "ACTIVO";
        }
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpedidos != null ? idpedidos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidos)) {
            return false;
        }
        Pedidos other = (Pedidos) object;
        if ((this.idpedidos == null && other.idpedidos != null) || (this.idpedidos != null && !this.idpedidos.equals(other.idpedidos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.encens.khipus.model.Pedidos[ idpedidos=" + idpedidos + " ]";
    }
    
}
