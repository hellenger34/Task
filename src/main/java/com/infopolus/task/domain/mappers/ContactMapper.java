package com.infopolus.task.domain.mappers;

import com.infopolus.task.domain.Car;
import com.infopolus.task.domain.Person;
import com.infopolus.task.domain.api.Contact;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContactMapper {

    public Contact toContact(final Person person, final List<Car> cars) {
        return Contact.builder()
                .name(person.getName())
                .ownedCars(extractCarNames(cars))
                .build();
    }

    private List<String> extractCarNames(final List<Car> cars) {
        return cars.stream()
                   .map(Car::getModel)
                   .collect(Collectors.toList());
    }
}
