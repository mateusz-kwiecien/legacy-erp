package pl.mkwiecien.legacyerp.util.services;

import org.springframework.stereotype.Service;
import pl.mkwiecien.legacyerp.domain.employee.entity.EmployeeRequest;
import pl.mkwiecien.legacyerp.domain.employee.ports.CreateEmployeePort;

import java.util.List;
import java.util.Optional;

@Service
public class DataPopulationService {
    private static final Integer DEFAULT_EMPLOYEE_NUMBER = 20;

    private CreateEmployeePort createEmployeePort;

    private DataFactory dataFactory;

    public DataPopulationService(CreateEmployeePort createEmployeePort, DataFactory dataFactory) {
        this.createEmployeePort = createEmployeePort;
        this.dataFactory = dataFactory;
    }

    public void populateWithRandomData(Optional<Integer> numberOfEmployees) {
        createEmployees(numberOfEmployees.orElse(DEFAULT_EMPLOYEE_NUMBER));
    }

    private void createEmployees(Integer employeesAmount) {
        List<EmployeeRequest> generatedRequests = dataFactory.generateEmployeeRequests(employeesAmount);
        createEmployeePort.createAll(generatedRequests);
    }
}
