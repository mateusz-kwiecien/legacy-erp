package pl.mkwiecien.legacyerp.domain.department.entity;

import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "DEPARTMENT")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "MANAGER_ID")
    private Long managerId;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "department",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Employee> employees;

    public Department() {
    }

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

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
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

        public Department build() {
            Department department = new Department();
            department.setId(id);
            department.setName(name);
            department.setManagerId(managerId);
            department.setEmployees(employees);
            return department;
        }
    }
}
