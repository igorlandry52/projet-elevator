package org.paumard.elevator;

import org.paumard.elevator.model.DIRECTION;
import org.paumard.elevator.model.Person;

import java.util.List;

public interface Elevator {

    void startsAtFloor(int initialFloor);

    void peopleWaiting(List<List<Person>> peopleByFloor);

    DIRECTION chooseDirection();

    void loadPeople(List<Person> peopleToLoad);

    void arriveAtFloor(int floor);

    void unloadPeople(List<Person> peopleToUnload);

    void stoppingAtFloor(int currentFloor);
}
