package pl.mkwiecien.legacyerp.domain.employee;

import pl.mkwiecien.legacyerp.domain.employee.entity.Employee;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class EmployeeMotherObject {
    public static final String EMAIL_SUFFIX = "@example.com";

    public static Employee aRandomEmployee() {
        String firstName = firstNames().get(new Random().nextInt(firstNames().size()));
        String lastName = lastNames().get(new Random().nextInt(lastNames().size()));
        String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + EMAIL_SUFFIX;
        return new Employee(firstName, lastName, email);
    }

    public static List<Employee> anUniqueRandomEmployeeList(int employeesNumber) {
        return Collections.emptyList();
    }

    private static List<String> firstNames() {
        return Arrays.asList("Adam", "John", "Emil", "Robert", "Conan", "Patrick", "Matthew", "Donald", "Arnold", "Michael");
    }

    private static List<String> lastNames() {
        return Arrays.asList("Smith", "Doe", "Miller", "Trump", "April", "Black", "Ford", "Corleone", "Craig", "Danielson");
    }

    public static class EmployeeUriResolver {
        private static final String BASIC_EMPLOYEE_URI = "/employee";
        private static final String NEW_EMPLOYEE_URI = BASIC_EMPLOYEE_URI + "/new";
        private static final String EMPLOYEE_LIST_URI = BASIC_EMPLOYEE_URI + "/list";
        private static final String DELETE_EMPLOYEE_URI = BASIC_EMPLOYEE_URI + "/delete";
        private static final String UPDATE_EMPLOYEE_URI = BASIC_EMPLOYEE_URI + "/update";

        public static String getDeleteEmployeeUriFor(Long id) {
            return DELETE_EMPLOYEE_URI + "/" + id;
        }

        public static String getEmployeeUriFor(Long id) {
            return BASIC_EMPLOYEE_URI + "/" + id;
        }

        public static String getEmployeeUpdateUriFor(Long id) {
            return UPDATE_EMPLOYEE_URI + "/" + id;
        }

        public static String getBasicEmployeeUri() {
            return BASIC_EMPLOYEE_URI;
        }

        public static String getNewEmployeeUri() {
            return NEW_EMPLOYEE_URI;
        }

        public static String getEmployeeListUri() {
            return EMPLOYEE_LIST_URI;
        }
    }
}
