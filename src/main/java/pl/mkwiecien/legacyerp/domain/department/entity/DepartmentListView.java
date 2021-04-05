package pl.mkwiecien.legacyerp.domain.department.entity;

public class DepartmentListView {
    private Long id;
    private String name;
    private String manager;
    private Long employees;

    public DepartmentListView() {
    }

    public DepartmentListView(Long id, String name, String manager, Long employees) {
        this.id = id;
        this.name = name;
        this.manager = manager;
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getManager() {
        return manager;
    }

    public Long getEmployees() {
        return employees;
    }
}
