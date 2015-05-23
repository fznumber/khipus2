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
    @Basic(optional = false)
    @NotNull
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
    private double totalimporte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IMPUESTO")
    private double impuesto;
    @Column(name = "CODIGO")
    private BigInteger codigo;
    @JoinColumn(name = "IDCLIENTE", referencedColumnName = "IDPERSONACLIENTE")
    @ManyToOne
    private Cliente idcliente;
    @JoinColumn(name = "IDMOVIMIENTO", referencedColumnName = "IDMOVIMIENTO")
    @ManyToOne
    private Movimiento idmovimiento;
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario idusuario;

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

    public double getTotalimporte() {
        return totalimporte;
    }

    public void setTotalimporte(double totalimporte) {
        this.totalimporte = totalimporte;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public BigInteger getCodigo() {
        return codigo;
    }

    public void setCodigo(BigInteger codigo) {
        this.codigo = codigo;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    public Movimiento getIdmovimiento() {
        return idmovimiento;
    }

    public void setIdmovimiento(Movimiento idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
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
    
}
