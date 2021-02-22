package pl.mkwiecien.legacyerp.domain.employee.entity;

import pl.mkwiecien.legacyerp.domain.department.entity.Department;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class EmployeeRequest {

    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Valid email is required")
    private String email;

    private Department department;

    public EmployeeRequest() {
    }

    public EmployeeRequest(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public EmployeeRequest(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public static EmployeeRequest from(Employee employee) {
        return new EmployeeRequest(employee.getId(), employee.getFirstName(), employee.getLastName(),
                employee.getEmail());
    }

    @Override
    public String toString() {
        return "EmployeeRequest{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", department=" + department +
                '}';
    }
}
