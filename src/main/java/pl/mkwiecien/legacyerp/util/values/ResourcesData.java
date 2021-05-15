package pl.mkwiecien.legacyerp.util.values;

public class ResourcesData {
    private Long employeesNumber;
    private Long departmentsNumber;

    public ResourcesData() {
    }

    public ResourcesData(Long employeesNumber, Long departmentsNumber) {
        this.employeesNumber = employeesNumber;
        this.departmentsNumber = departmentsNumber;
    }

    public Long getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(Long employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public Long getDepartmentsNumber() {
        return departmentsNumber;
    }

    public void setDepartmentsNumber(Long departmentsNumber) {
        this.departmentsNumber = departmentsNumber;
    }
}
