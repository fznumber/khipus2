/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.encens.khipus.model;

import java.io.Serializable;
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
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_persona")
@DiscriminatorValue(value = "persona")
@TableGenerator(name = "Persona_Gen"
                ,table="ID_GEN"
                ,pkColumnName = "GEN_NAME"
                ,valueColumnName = "GEN_VAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personas.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Personas.findByPiId", query = "SELECT p FROM Persona p WHERE p.piId = :piId"),
    @NamedQuery(name = "Personas.findByNroDoc", query = "SELECT p FROM Persona p WHERE p.nroDoc = :nroDoc"),
    @NamedQuery(name = "Personas.findByAp", query = "SELECT p FROM Persona p WHERE p.ap = :ap"),
    @NamedQuery(name = "Personas.findByAm", query = "SELECT p FROM Persona p WHERE p.am = :am"),
    @NamedQuery(name = "Personas.findByNom", query = "SELECT p FROM Persona p WHERE p.nom = :nom"),
    @NamedQuery(name = "Personas.findBySexo", query = "SELECT p FROM Persona p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "Personas.findByEstCivil", query = "SELECT p FROM Persona p WHERE p.estCivil = :estCivil"),
    @NamedQuery(name = "Personas.findByFechaNac", query = "SELECT p FROM Persona p WHERE p.fechaNac = :fechaNac"),
    @NamedQuery(name = "Personas.findByCemCod", query = "SELECT p FROM Persona p WHERE p.cemCod = :cemCod"),
    @NamedQuery(name = "Personas.findByOcuCod", query = "SELECT p FROM Persona p WHERE p.ocuCod = :ocuCod"),
    @NamedQuery(name = "Personas.findByTdoCod", query = "SELECT p FROM Persona p WHERE p.tdoCod = :tdoCod"),
    @NamedQuery(name = "Personas.findBySisCod", query = "SELECT p FROM Persona p WHERE p.sisCod = :sisCod")})
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Persona_Gen")
    @Column(name = "IDPERSONA")
    private Long piId;
    @Size(max = 20)
    @Column(name = "NRO_DOC")
    private String nroDoc;
    @Size(max = 65)
    @Column(name = "AP")
    private String ap;
    @Size(max = 65)
    @Column(name = "AM")
    private String am;
    @Basic(optional = false)
    @Size( max = 100)
    @Column(name = "NOM")
    private String nom;
    @Column(name = "SEXO")
    private String sexo;
    @Size(max = 1)
    @Column(name = "EST_CIVIL")
    private String estCivil;
    @Column(name = "FECHA_NAC")
    @Temporal(TemporalType.DATE)
    private Date fechaNac;
    @Size(max = 15)
    @Column(name = "CEM_COD")
    private String cemCod;
    @Size(max = 15)
    @Column(name = "OCU_COD")
    private String ocuCod;
    @Size(max = 15)
    @Column(name = "TDO_COD")
    private String tdoCod;
    @Size(max = 20)
    @Column(name = "SIS_COD")
    private String sisCod;
    @Column(name="tipo_persona")
    private String tipoPersona;


    public Persona() {
    }

    public Persona(Long piId) {
        this.piId = piId;
    }

    public Persona(Long piId, String nom) {
        this.piId = piId;
        this.nom = nom;
    }

    public Long getPiId() {
        return piId;
    }

    public void setPiId(Long piId) {
        this.piId = piId;
    }

    public String getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }

    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }

    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSexo() {
        if(this.sexo == null)
            this.sexo = "MASCULINO";
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public String getCemCod() {
        return cemCod;
    }

    public void setCemCod(String cemCod) {
        this.cemCod = cemCod;
    }

    public String getOcuCod() {
        return ocuCod;
    }

    public void setOcuCod(String ocuCod) {
        this.ocuCod = ocuCod;
    }

    public String getTdoCod() {
        return tdoCod;
    }

    public void setTdoCod(String tdoCod) {
        this.tdoCod = tdoCod;
    }

    public String getSisCod() {
        return sisCod;
    }

    public void setSisCod(String sisCod) {
        this.sisCod = sisCod;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piId != null ? piId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.piId == null && other.piId != null) || (this.piId != null && !this.piId.equals(other.piId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.encens.khipus.model.Personas[ piId=" + piId + " ]";
    }

    public String getNombreCompleto(){
        return nom+" "+ap+" "+am;
    }
    
}
