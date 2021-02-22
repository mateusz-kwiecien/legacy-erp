package pl.mkwiecien.legacyerp.domain.department.entity;

public class DepartmentRequest {

    private Long id;

    private Long managerId;

    public DepartmentRequest(Long id, Long managerId) {
        this.id = id;
        this.managerId = managerId;
    }

    public DepartmentRequest(Long managerId) {
        this.managerId = managerId;
    }

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
}
