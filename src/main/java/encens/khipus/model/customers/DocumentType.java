package encens.khipus.model.customers;


import encens.khipus.model.admin.Company;
import encens.khipus.model.contacts.EntityType;
import org.hibernate.annotations.Filter;

import javax.persistence.*;

/**
 * Entity for Document Types
 *
 * @author:
 * version: 2.7
 */

@TableGenerator(schema = encens.khipus.util.Constants.KHIPUS_SCHEMA, name = "DocumentType.tableGenerator",
        table = encens.khipus.util.Constants.SEQUENCE_TABLE_NAME,
        pkColumnName = encens.khipus.util.Constants.SEQUENCE_TABLE_PK_COLUMN_NAME,
        valueColumnName = encens.khipus.util.Constants.SEQUENCE_TABLE_VALUE_COLUMN_NAME,
        pkColumnValue = "tipodocumento",
        allocationSize = encens.khipus.util.Constants.SEQUENCE_ALLOCATION_SIZE)

@Entity
@Filter(name = encens.khipus.util.Constants.COMPANY_FILTER_NAME)

@Table(schema = encens.khipus.util.Constants.KHIPUS_SCHEMA, name = "tipodocumento", uniqueConstraints = @UniqueConstraint(columnNames = {"idcompania", "nombre"}))
public class DocumentType implements java.io.Serializable {
    @Id
    @Column(name = "idtipodocumento", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "DocumentType.tableGenerator")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String name;

    @Column(name = "APLICABLEA", length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityType entityType;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }
}
