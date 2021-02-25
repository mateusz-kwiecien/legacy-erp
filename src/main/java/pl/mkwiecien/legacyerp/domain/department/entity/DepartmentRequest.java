package pl.mkwiecien.legacyerp.domain.department.entity;

import javax.validation.constraints.NotNull;

public class DepartmentRequest {

    private Long id;

    @NotNull(message = "Department name is required")
    private String name;

    private Long managerId;

    public DepartmentRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
