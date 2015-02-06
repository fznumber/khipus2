package encens.khipus.model.contacts;


import encens.khipus.model.admin.Company;
import encens.khipus.model.customers.DocumentType;
import org.hibernate.annotations.Filter;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Entity for Limit
 *
 * @author
 * @version 2.7
 */

@TableGenerator(schema = encens.khipus.util.Constants.KHIPUS_SCHEMA, name = "Extension.tableGenerator",
        table = encens.khipus.util.Constants.SEQUENCE_TABLE_NAME,
        pkColumnName = encens.khipus.util.Constants.SEQUENCE_TABLE_PK_COLUMN_NAME,
        valueColumnName = encens.khipus.util.Constants.SEQUENCE_TABLE_VALUE_COLUMN_NAME,
        pkColumnValue = "EXTTIPODOCUMENTO",
        allocationSize = encens.khipus.util.Constants.SEQUENCE_ALLOCATION_SIZE)

@Entity
@Filter(name = encens.khipus.util.Constants.COMPANY_FILTER_NAME)
@Table(schema = encens.khipus.util.Constants.KHIPUS_SCHEMA, name = "EXTTIPODOCUMENTO", uniqueConstraints = @UniqueConstraint(columnNames = {"IDTIPODOCUMENTO", "IDEXTTIPODOCUMENTO"}))
public class Extension implements java.io.Serializable {

    @Id
    @Column(name = "IDEXTTIPODOCUMENTO", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Extension.tableGenerator")
    private Long id;

    @Column(name = "EXTENSION", nullable = false, length = 100)
    private String extension;

    @ManyToOne
    @JoinColumn(name = "IDTIPODOCUMENTO", referencedColumnName = "idtipodocumento", nullable = false)
    private DocumentType documentType;

    @Version
    @Column(name = "VERSION", nullable = false)
    private long version;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCOMPANIA", nullable = false, updatable = false, insertable = true)
    private Company company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}