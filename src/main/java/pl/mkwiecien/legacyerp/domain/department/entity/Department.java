package pl.mkwiecien.legacyerp.domain.department.entity;

import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "DEPARTMENT")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "MANAGER_ID")
    private Long managerId;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "department",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Employee> employees;

    public Department(Long id, Long managerId) {
        this.id = id;
        this.managerId = managerId;
    }

    public Department(Long managerId) {
        this.managerId = managerId;
    }

    public Department() {
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
