package encens.khipus.model.admin;

import javax.persistence.*;

/**
 * Entity for Module
 *
 * @author:
 */

@TableGenerator(schema = encens.khipus.util.Constants.KHIPUS_SCHEMA, name = "Module.tableGenerator",
        table = encens.khipus.util.Constants.SEQUENCE_TABLE_NAME,
        pkColumnName = encens.khipus.util.Constants.SEQUENCE_TABLE_PK_COLUMN_NAME,
        valueColumnName = encens.khipus.util.Constants.SEQUENCE_TABLE_VALUE_COLUMN_NAME,
        pkColumnValue = "modulo",
        allocationSize = encens.khipus.util.Constants.SEQUENCE_ALLOCATION_SIZE)
@Entity
@Table(schema = encens.khipus.util.Constants.KHIPUS_SCHEMA, name = "modulo")
public class SystemModule {

    @Id
    @Column(name = "idmodulo", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Module.tableGenerator")
    private Long id;

    @Column(name = "nombrerecurso", length = 150, nullable = false, unique = true)
    private String resourceName;

    @Column(name = "descripcion")
    @Lob
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
