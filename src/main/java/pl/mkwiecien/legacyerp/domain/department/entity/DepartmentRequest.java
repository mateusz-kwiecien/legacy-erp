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

    private DepartmentRequest(Long id, @NotNull(message = "Department name is required") String name, Long managerId, Set<Employee> employees) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
        this.employees = employees;
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

    public static DepartmentRequest from(Department department) {
        return Builder.builder()
                .id(department.getId())
                .name(department.getName())
                .managerId(department.getManagerId())
                .employees(department.getEmployees())
                .build();
    }

    public static final class Builder {
        private Long id;
        private String name;
        private Long managerId;
        private Set<Employee> employees;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder managerId(Long managerId) {
            this.managerId = managerId;
            return this;
        }

        public Builder employees(Set<Employee> employees) {
            this.employees = employees;
            return this;
        }

        public DepartmentRequest build() {
            return new DepartmentRequest(id, name, managerId, employees);
        }
    }
}
