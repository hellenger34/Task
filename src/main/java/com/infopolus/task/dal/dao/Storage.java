package com.infopolus.task.dal.dao;

import com.infopolus.task.domain.Car;
import com.infopolus.task.domain.Person;
import com.infopolus.task.util.DataProvider;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Scope("singleton")
@RequiredArgsConstructor
public class Storage {

    private static final int NUMBER_OF_DATA = 10;

    @NonNull
    private final DataProvider dataProvider;

    public static final Map<String, Person> USERS_STORAGE = new HashMap<>();
    public static final Map<String, Car> CARS_STORAGE = new HashMap<>();

    @PostConstruct
    private void setUp() {
        List<String> names = dataProvider.providePeopleNames(NUMBER_OF_DATA);
        List<Car> cars = dataProvider.provideCarModels((int) (NUMBER_OF_DATA * 1.5));

        fillInContact(names, cars);
        fillInCars(cars);
    }

    public List<Car> findAllCars() {
        return new ArrayList<>(CARS_STORAGE.values());
    }

    public List<Person> findAllUsers() {
        return new ArrayList<>(USERS_STORAGE.values());
    }

    private void fillInContact(List<String> names, final List<Car> cars) {
        final List<String> carsLeft = cars.stream()
                                          .map(Car::getId)
                                          .collect(Collectors.toList());

        names.forEach(name -> {
            final String id = UUID.randomUUID().toString();
            final List<String> ownedCars = getRandomNumberOfCars(carsLeft);
            USERS_STORAGE.put(id, new Person(id, name, ownedCars));
            ownedCars.forEach(carsLeft::remove);
        });
    }

    private void fillInCars(final List<Car> cars) {
        cars.forEach(car -> CARS_STORAGE.put(car.getId(), car));
    }

    private List<String> getRandomNumberOfCars(final List<String> cars) {
        final int randomNumberOfCars = (int) (Math.random() * 3);

        if (randomNumberOfCars == 0) {
            return List.of();
        }

        return new ArrayList<>(cars.subList(0, Math.min(cars.size(), randomNumberOfCars)));
    }
}
