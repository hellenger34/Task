package com.infopolus.task.dal.repositories;

import com.infopolus.task.domain.Car;
import com.infopolus.task.domain.Person;
import com.infopolus.task.domain.api.Contact;
import com.infopolus.task.domain.mappers.ContactMapper;
import com.infopolus.task.dal.dao.Storage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ContactRepository {

    @NonNull
    private final Storage storage;
    @NonNull
    private final ContactMapper contactMapper;

    public List<Contact> findAllContacts() {
        List<Person> people = storage.findAllUsers();
        List<Car> cars = storage.findAllCars();

        return people.stream()
                    .map(person -> contactMapper.toContact(person, findAppropriateCars(person, cars)))
                    .collect(Collectors.toList());
    }

    private List<Car> findAppropriateCars(final Person person, List<Car> cars) {
        return cars.stream()
                   .filter(car -> person.getOwnedCarIds().contains(car.getId()))
                   .collect(Collectors.toList());
    }
}
