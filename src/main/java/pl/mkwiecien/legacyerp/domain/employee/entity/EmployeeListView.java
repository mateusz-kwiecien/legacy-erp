package pl.mkwiecien.legacyerp.domain.employee.entity;

public class EmployeeListView {
    private Long id;
    private String fullName;
    private String email;
    private String assignedDepartment;
    private String subordinateDepartment;

    public EmployeeListView(Long id, String fullName, String email, String assignedDepartment,
                            String subordinateDepartment) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.assignedDepartment = assignedDepartment;
        this.subordinateDepartment = subordinateDepartment;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getAssignedDepartment() {
        return assignedDepartment;
    }

    public String getSubordinateDepartment() {
        return subordinateDepartment;
    }
}
