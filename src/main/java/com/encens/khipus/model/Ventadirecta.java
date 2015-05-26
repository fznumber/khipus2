/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "ventadirecta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ventadirecta.findAll", query = "SELECT v FROM Ventadirecta v"),
    @NamedQuery(name = "Ventadirecta.findByIdventadirecta", query = "SELECT v FROM Ventadirecta v WHERE v.idventadirecta = :idventadirecta"),
    @NamedQuery(name = "Ventadirecta.findByDescripcion", query = "SELECT v FROM Ventadirecta v WHERE v.descripcion = :descripcion"),
    @NamedQuery(name = "Ventadirecta.findByFechaPedido", query = "SELECT v FROM Ventadirecta v WHERE v.fechaPedido = :fechaPedido"),
    @NamedQuery(name = "Ventadirecta.findByObservacion", query = "SELECT v FROM Ventadirecta v WHERE v.observacion = :observacion"),
    @NamedQuery(name = "Ventadirecta.findByTotal", query = "SELECT v FROM Ventadirecta v WHERE v.total = :total"),
    @NamedQuery(name = "Ventadirecta.findByEstado", query = "SELECT v FROM Ventadirecta v WHERE v.estado = :estado"),
    @NamedQuery(name = "Ventadirecta.findByTotalimporte", query = "SELECT v FROM Ventadirecta v WHERE v.totalimporte = :totalimporte"),
    @NamedQuery(name = "Ventadirecta.findByImpuesto", query = "SELECT v FROM Ventadirecta v WHERE v.impuesto = :impuesto"),
    @NamedQuery(name = "Ventadirecta.findByCodigo", query = "SELECT v FROM Ventadirecta v WHERE v.codigo = :codigo")})
public class Ventadirecta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @TableGenerator(name = "Ventadirecta_Gen"
            ,table="ID_GEN"
            ,pkColumnName = "GEN_NAME"
            ,valueColumnName = "GEN_VAL"
            ,initialValue = 1
            ,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Ventadirecta_Gen")
    @Column(name = "IDVENTADIRECTA")
    private Long idventadirecta;
    @Size(max = 255)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "FECHA_PEDIDO")
    @Temporal(TemporalType.DATE)
    private Date fechaPedido;
    @Size(max = 255)
    @Column(name = "OBSERVACION")
    private String observacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TOTAL")
    private Double total;
    @Size(max = 15)
    @Column(name = "ESTADO")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTALIMPORTE")
    private Double totalimporte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IMPUESTO")
    private double impuesto;
    @OneToOne
    @JoinColumn(name="codigo")
    private CodigoPedidoSecuencia codigo;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuario;
    @JoinColumn(name = "IDCLIENTE", referencedColumnName = "IDPERSONACLIENTE")
    @ManyToOne(optional = false)
    private Persona cliente;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "ventadirecta")
    private Collection<ArticulosPedido> articulosPedidos = new ArrayList<>();
    @JoinColumn(name = "IDMOVIMIENTO",referencedColumnName = "IDMOVIMIENTO")
    @ManyToOne(optional = true)
    private Movimiento movimiento;

    public Ventadirecta() {
    }

    public Ventadirecta(Long idventadirecta) {
        this.idventadirecta = idventadirecta;
    }

    public Ventadirecta(Long idventadirecta, double totalimporte, double impuesto) {
        this.idventadirecta = idventadirecta;
        this.totalimporte = totalimporte;
        this.impuesto = impuesto;
    }

    public Long getIdventadirecta() {
        return idventadirecta;
    }

    public void setIdventadirecta(Long idventadirecta) {
        this.idventadirecta = idventadirecta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaPedido() {
        if(fechaPedido == null)
            fechaPedido = new Date();
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getTotalimporte() {
        totalimporte = 0.0;
        if(articulosPedidos != null)
            for(ArticulosPedido articulosPedido:articulosPedidos)
            {
                totalimporte += articulosPedido.getImporte();
            }
        return totalimporte;
    }

    public void setTotalimporte(Double totalimporte) {
        this.totalimporte = totalimporte;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public CodigoPedidoSecuencia getCodigo() {
        return codigo;
    }

    public void setCodigo(CodigoPedidoSecuencia codigo) {
        this.codigo = codigo;
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idventadirecta != null ? idventadirecta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ventadirecta)) {
            return false;
        }
        Ventadirecta other = (Ventadirecta) object;
        if ((this.idventadirecta == null && other.idventadirecta != null) || (this.idventadirecta != null && !this.idventadirecta.equals(other.idventadirecta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.encens.khipus.model.Ventadirecta[ idventadirecta=" + idventadirecta + " ]";
    }

    public Persona getCliente() {
        return cliente;
    }

    public void setCliente(Persona cliente) {
        this.cliente = cliente;
    }

    public Collection<ArticulosPedido> getArticulosPedidos() {

        return articulosPedidos;
    }

    public void setArticulosPedidos(Collection<ArticulosPedido> articulosPedidos) {
        this.articulosPedidos = articulosPedidos;
    }
}
