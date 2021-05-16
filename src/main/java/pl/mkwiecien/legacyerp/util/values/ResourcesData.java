package pl.mkwiecien.legacyerp.util.values;

public class ResourcesData {
    private final Long employeesNumber;
    private final Long unassignedEmployeesNumber;
    private final Long departmentsNumber;

    public ResourcesData(Long employeesNumber, Long unassignedEmployeesNumber, Long departmentsNumber) {
        this.employeesNumber = employeesNumber;
        this.unassignedEmployeesNumber = unassignedEmployeesNumber;
        this.departmentsNumber = departmentsNumber;
    }

    public Long getEmployeesNumber() {
        return employeesNumber;
    }

    public Long getDepartmentsNumber() {
        return departmentsNumber;
    }

    public Long getUnassignedEmployeesNumber() {
        return unassignedEmployeesNumber;
    }
}
