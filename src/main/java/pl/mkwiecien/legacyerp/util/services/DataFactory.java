package pl.mkwiecien.legacyerp.util.services;

import org.springframework.stereotype.Component;
import pl.mkwiecien.legacyerp.domain.employee.entity.EmployeeRequest;

import java.util.*;

@Component
public class DataFactory {
    private static final String EMAIL_SUFIX = "@example.com";
    private static final List<String> MALE_FIRST_NAMES = Arrays.asList("James", "John", "Robert", "Michael", "William", "David", "Richard", "Joseph",
            "Thomas", "Charles", "Christopher", "Daniel", "Matthew", "Anthony", "Donald", "Mark", "Paul", "Steven", "Andrew", "Kenneth");
    private static final List<String> FEMALE_FIRST_NAMES = Arrays.asList("Donna", "Emily", "Kimberly", "Dorothy", "Ashley", "Sandra", "Betty", "Margaret",
            "Lisa", "Nancy", "Karen", "Sarah", "Jessica", "Susan", "Barbara", "Elizabeth", "Linda", "Jennifer", "Patricia", "Mary");
    private static final List<String> LAST_NAMES = Arrays.asList("Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez",
            "Martinez", "Hernandez", "Lopez", "Gonzales", "Wilson", "Anderson", "Thomas", "Taylor", "Moore", "Jackson", "Martin", "Lee", "Perez", "Thompson",
            "White", "Harris", "Sanchez", "Clark", "Ramirez", "Lewis", "Robinson");

    List<EmployeeRequest> generateEmployeeRequests(int numberOfRequests) {
        List<EmployeeRequest> requests = new ArrayList<>();
        for (int i = 0; i < numberOfRequests; i++) {
            requests.add(newRandomRequest());
        }
        return requests;
    }

    private EmployeeRequest newRandomRequest() {
        Gender gender = new Random().nextBoolean() ? Gender.MALE : Gender.FEMALE;
        List<String> firstNames = gender.equals(Gender.MALE) ? MALE_FIRST_NAMES : FEMALE_FIRST_NAMES;
        String firstName = pickRandomElementFrom(firstNames);
        String lastName = pickRandomElementFrom(LAST_NAMES);
        String email = firstName.toLowerCase().concat(".").concat(lastName.toLowerCase()).concat(EMAIL_SUFIX);
        return new EmployeeRequest(firstName, lastName, email);
    }

    private String pickRandomElementFrom(List<String> givenList) {
        int randomIndex = new Random().nextInt(givenList.size());
        return givenList.get(randomIndex);
    }

    enum Gender {
        MALE, FEMALE
    }
}
