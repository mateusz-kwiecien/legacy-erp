package pl.mkwiecien.legacyerp.domain.employee.entity;

import pl.mkwiecien.legacyerp.domain.department.entity.Department;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class EmployeeRequest {

    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Valid email is required")
    private String email;

    private String departmentName;

    public EmployeeRequest() {
    }

    private EmployeeRequest(Long id, String firstName, String lastName, String email, String departmentName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentName = departmentName;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public static EmployeeRequest from(Employee employee) {

        String departmentName = employee.getDepartment() != null
                ? employee.getDepartment().getName()
                : null;

        return Builder.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .departmentName(departmentName)
                .build();
    }

    public static final class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String departmentName;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder departmentName(String departmentName) {
            this.departmentName = departmentName;
            return this;
        }

        public EmployeeRequest build() {
            return new EmployeeRequest(id, firstName, lastName, email, departmentName);
        }
    }
}
