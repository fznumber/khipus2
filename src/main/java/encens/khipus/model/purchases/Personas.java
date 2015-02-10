package encens.khipus.model.purchases;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Diego on 07/02/2015.
 */
@Entity
public class Personas {
    private long piId;
    private String nroDoc;
    private String ap;
    private String am;
    private String nom;
    private String sexo;
    private String estCivil;
    private Date fechaNac;
    private String cemCod;
    private String ocuCod;
    private String tdoCod;
    private String sisCod;
    private Cliente clientePersona;

    @Id
    @Column(name = "PI_ID", nullable = false, insertable = true, updatable = true)
    public long getPiId() {
        return piId;
    }

    public void setPiId(long piId) {
        this.piId = piId;
    }

    @Basic
    @Column(name = "NRO_DOC", nullable = true, insertable = true, updatable = true, length = 20)
    public String getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }

    @Basic
    @Column(name = "AP", nullable = true, insertable = true, updatable = true, length = 65)
    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }

    @Basic
    @Column(name = "AM", nullable = true, insertable = true, updatable = true, length = 65)
    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am;
    }

    @Basic
    @Column(name = "NOM", nullable = false, insertable = true, updatable = true, length = 100)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "SEXO", nullable = true, insertable = true, updatable = true, length = 1)
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Basic
    @Column(name = "EST_CIVIL", nullable = true, insertable = true, updatable = true, length = 1)
    public String getEstCivil() {
        return estCivil;
    }

    public void setEstCivil(String estCivil) {
        this.estCivil = estCivil;
    }

    @Basic
    @Column(name = "FECHA_NAC", nullable = true, insertable = true, updatable = true)
    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    @Basic
    @Column(name = "CEM_COD", nullable = true, insertable = true, updatable = true, length = 15)
    public String getCemCod() {
        return cemCod;
    }

    public void setCemCod(String cemCod) {
        this.cemCod = cemCod;
    }

    @Basic
    @Column(name = "OCU_COD", nullable = true, insertable = true, updatable = true, length = 15)
    public String getOcuCod() {
        return ocuCod;
    }

    public void setOcuCod(String ocuCod) {
        this.ocuCod = ocuCod;
    }

    @Basic
    @Column(name = "TDO_COD", nullable = true, insertable = true, updatable = true, length = 15)
    public String getTdoCod() {
        return tdoCod;
    }

    public void setTdoCod(String tdoCod) {
        this.tdoCod = tdoCod;
    }

    @Basic
    @Column(name = "SIS_COD", nullable = true, insertable = true, updatable = true, length = 20)
    public String getSisCod() {
        return sisCod;
    }

    public void setSisCod(String sisCod) {
        this.sisCod = sisCod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personas personas = (Personas) o;

        if (piId != personas.piId) return false;
        if (am != null ? !am.equals(personas.am) : personas.am != null) return false;
        if (ap != null ? !ap.equals(personas.ap) : personas.ap != null) return false;
        if (cemCod != null ? !cemCod.equals(personas.cemCod) : personas.cemCod != null) return false;
        if (estCivil != null ? !estCivil.equals(personas.estCivil) : personas.estCivil != null) return false;
        if (fechaNac != null ? !fechaNac.equals(personas.fechaNac) : personas.fechaNac != null) return false;
        if (nom != null ? !nom.equals(personas.nom) : personas.nom != null) return false;
        if (nroDoc != null ? !nroDoc.equals(personas.nroDoc) : personas.nroDoc != null) return false;
        if (ocuCod != null ? !ocuCod.equals(personas.ocuCod) : personas.ocuCod != null) return false;
        if (sexo != null ? !sexo.equals(personas.sexo) : personas.sexo != null) return false;
        if (sisCod != null ? !sisCod.equals(personas.sisCod) : personas.sisCod != null) return false;
        if (tdoCod != null ? !tdoCod.equals(personas.tdoCod) : personas.tdoCod != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (piId ^ (piId >>> 32));
        result = 31 * result + (nroDoc != null ? nroDoc.hashCode() : 0);
        result = 31 * result + (ap != null ? ap.hashCode() : 0);
        result = 31 * result + (am != null ? am.hashCode() : 0);
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (sexo != null ? sexo.hashCode() : 0);
        result = 31 * result + (estCivil != null ? estCivil.hashCode() : 0);
        result = 31 * result + (fechaNac != null ? fechaNac.hashCode() : 0);
        result = 31 * result + (cemCod != null ? cemCod.hashCode() : 0);
        result = 31 * result + (ocuCod != null ? ocuCod.hashCode() : 0);
        result = 31 * result + (tdoCod != null ? tdoCod.hashCode() : 0);
        result = 31 * result + (sisCod != null ? sisCod.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "IDCLIENTE", referencedColumnName = "IDCLIENTE", nullable = false)
    public Cliente getClientePersona() {
        return clientePersona;
    }

    public void setClientePersona(Cliente clienteByIdcliente) {
        this.clientePersona = clienteByIdcliente;
    }
}
