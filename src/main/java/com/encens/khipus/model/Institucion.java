/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.model;

import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Diego
 */
@Entity
@XmlRootElement
@DiscriminatorValue("institucion")
@NamedQueries({
        @NamedQuery(name = "Institucion.findAll", query = "SELECT i FROM Institucion i"),
        @NamedQuery(name = "Institucion.findByRazonsocial", query = "SELECT i FROM Institucion i WHERE i.razonsocial = :razonsocial")})
public class Institucion extends Persona {
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDPERSONA")
    private Long idpersona;
    @Size(max = 255)
    @Column(name = "AM")
    private String am;
    @Size(max = 255)
    @Column(name = "AP")
    private String ap;
    @Size(max = 255)
    @Column(name = "CEM_COD")
    private String cemCod;
    @Size(max = 255)
    @Column(name = "EST_CIVIL")
    private String estCivil;
    @Column(name = "FECHA_NAC")
    @Temporal(TemporalType.DATE)
    private Date fechaNac;
    @Size(max = 255)
    @Column(name = "NOM")
    private String nom;
    @Size(max = 255)
    @Column(name = "NRO_DOC")
    private String nroDoc;
    @Size(max = 255)
    @Column(name = "OCU_COD")
    private String ocuCod;
    @Size(max = 255)
    @Column(name = "SEXO")
    private String sexo;
    @Size(max = 255)
    @Column(name = "SIS_COD")
    private String sisCod;
    @Size(max = 255)
    @Column(name = "TDO_COD")
    private String tdoCod;
    @Size(max = 45)
    @Column(name = "TIPO_PERSONA")
    private String tipoPersona;
    @Size(max = 100)
    @Column(name = "DIRECCION")
    private String direccion;
    @Size(max = 15)
    @Column(name = "TELEFONO")
    private String telefono;
    @Size(max = 20)
    @Column(name = "NIT")
    private String nit;
    @Size(max = 10)
    @Column(name = "CODIGOCLIENTE")
    private String codigocliente;
    @Size(max = 100)
    @Column(name = "OBSERVACION")
    private String observacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "descuento")
    private Double descuento;
    @JoinColumn(name = "IDDEPARTAMENTO", referencedColumnName = "IDDEPARTAMENTO")
    @ManyToOne
    private Departamento iddepartamento;
    @JoinColumn(name = "IDRETENCION", referencedColumnName = "IDRETENCION")
    @ManyToOne
    private Retencion idretencion;
    @JoinColumn(name = "IDTIPOCLIENTE", referencedColumnName = "IDTIPOCLIENTE")
    @ManyToOne
    private Tipocliente idtipocliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
    private Collection<Ventacliente> ventaclienteCollection;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "RAZONSOCIAL")
    private String razonsocial;

    public Institucion() {
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    @Override
    public String toString() {
        return razonsocial;
    }

    @Override
    public String getNombreCompleto()
    {
        return razonsocial;
    }

    public Institucion(Long idpersona) {
        this.idpersona = idpersona;
    }

    public Long getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Long idpersona) {
        this.idpersona = idpersona;
    }

    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am;
    }

    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }

    public String getCemCod() {
        return cemCod;
    }

    public void setCemCod(String cemCod) {
        this.cemCod = cemCod;
    }

    public String getEstCivil() {
        return estCivil;
    }

    public void setEstCivil(String estCivil) {
        this.estCivil = estCivil;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }

    public String getOcuCod() {
        return ocuCod;
    }

    public void setOcuCod(String ocuCod) {
        this.ocuCod = ocuCod;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSisCod() {
        return sisCod;
    }

    public void setSisCod(String sisCod) {
        this.sisCod = sisCod;
    }

    public String getTdoCod() {
        return tdoCod;
    }

    public void setTdoCod(String tdoCod) {
        this.tdoCod = tdoCod;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCodigocliente() {
        return codigocliente;
    }

    public void setCodigocliente(String codigocliente) {
        this.codigocliente = codigocliente;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Departamento getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(Departamento iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public Retencion getIdretencion() {
        return idretencion;
    }

    public void setIdretencion(Retencion idretencion) {
        this.idretencion = idretencion;
    }

    public Tipocliente getIdtipocliente() {
        return idtipocliente;
    }

    public void setIdtipocliente(Tipocliente idtipocliente) {
        this.idtipocliente = idtipocliente;
    }

    @XmlTransient
    public Collection<Ventacliente> getVentaclienteCollection() {
        return ventaclienteCollection;
    }

    public void setVentaclienteCollection(Collection<Ventacliente> ventaclienteCollection) {
        this.ventaclienteCollection = ventaclienteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpersona != null ? idpersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Institucion)) {
            return false;
        }
        Institucion other = (Institucion) object;
        if ((this.idpersona == null && other.idpersona != null) || (this.idpersona != null && !this.idpersona.equals(other.idpersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.encens.khipus.model.Institucion[ idpersona=" + idpersona + " ]";
    }

}
