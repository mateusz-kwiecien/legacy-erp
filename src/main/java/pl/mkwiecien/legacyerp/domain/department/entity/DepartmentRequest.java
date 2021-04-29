package pl.mkwiecien.legacyerp.domain.department.entity;

import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class DepartmentRequest {

    private Long id;

    @NotNull(message = "Department name is required")
    private String name;

    private Long managerId;

    private Set<Employee> employees;

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

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
