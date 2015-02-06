package encens.khipus.model.admin;

import javax.persistence.*;

/**
 * Entity for Function
 *
 * @author:
 */
@Entity
@Table(schema = encens.khipus.util.Constants.KHIPUS_SCHEMA, name = "funcionalidad")
public class SystemFunction {

    @Id
    @Column(name = "idfuncionalidad", nullable = false)
    private Long id;

    @Column(name = "permiso", nullable = false)
    private Byte permission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idmodulo", referencedColumnName = "idmodulo", nullable = false)
    private SystemModule module;

    @Column(name = "idmodulo", insertable = false, updatable = false)
    private Long moduleId;

    @Column(name = "nombrerecurso", length = 100, nullable = false, unique = true)
    private String resourceName;

    @Column(name = "codigo", length = 40, nullable = false, unique = true)
    private String code;

    @Column(name = "descripcion")
    @Lob
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getPermission() {
        return permission;
    }

    public void setPermission(Byte permission) {
        this.permission = permission;
    }

    public SystemModule getModule() {
        return module;
    }

    public void setModule(SystemModule module) {
        this.module = module;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }
}
