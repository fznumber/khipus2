package encens.khipus.model.contacts;


import encens.khipus.model.admin.Company;
import org.hibernate.annotations.Filter;

import javax.persistence.*;
import javax.persistence.Entity;


/**
 * @author
 */
@TableGenerator(schema = encens.khipus.util.Constants.KHIPUS_SCHEMA, name = "Departament.tableGenerator",
        table = encens.khipus.util.Constants.SEQUENCE_TABLE_NAME,
        pkColumnName = encens.khipus.util.Constants.SEQUENCE_TABLE_PK_COLUMN_NAME,
        valueColumnName = encens.khipus.util.Constants.SEQUENCE_TABLE_VALUE_COLUMN_NAME,
        pkColumnValue = "departamento",
        allocationSize = encens.khipus.util.Constants.SEQUENCE_ALLOCATION_SIZE)

@Entity
@Filter(name = encens.khipus.util.Constants.COMPANY_FILTER_NAME)
@Table(schema = encens.khipus.util.Constants.KHIPUS_SCHEMA, name = "departamento", uniqueConstraints = {@UniqueConstraint(columnNames = {"idcompania", "idpais", "nombre"})})
public class Department implements java.io.Serializable {

    @Id
    @Column(name = "iddepartamento", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Departament.tableGenerator")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idpais", nullable = false)
    private Country country;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idcompania", nullable = false, updatable = false, insertable = true)
    private Company company;

    @Version
    @Column(name = "version", nullable = false)
    private long version;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
