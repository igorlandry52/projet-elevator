package org.paumard.elevator;

import org.paumard.elevator.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShadowElevator {

    private final int elevatorCapacity;
    private int floor = 1;
    private List<Person> people = new ArrayList<>();

    public ShadowElevator(int elevatorCapacity) {
        this.elevatorCapacity = elevatorCapacity;
    }

    public int getFloor() {
        return floor;
    }

    public List<Person> getPeople() {
        return new ArrayList<>(people);
    }

    public List<Person> unLoadPeople() {
        List<Person> peopleToUnload = people.stream().filter(p -> p.getDestinationFloor() == floor).collect(Collectors.toList());
        people.removeAll(peopleToUnload);
        return peopleToUnload;
    }

    public List<Person> loadPeopleGoingDownFromFloor(List<Person> waitingPeople) {
        List<Person> peopleGoingDown = waitingPeople.stream().filter(p -> p.getDestinationFloor() < floor).collect(Collectors.toList());
        if (!peopleGoingDown.isEmpty()) {
            int peopleToLoad = Integer.min(availableLoad(), peopleGoingDown.size());
            peopleGoingDown = peopleGoingDown.subList(0, peopleToLoad);
            people.addAll(peopleGoingDown);
        }
        return peopleGoingDown;
    }

    public List<Person> loadPeopleGoingUpFromFloor(List<Person> waitingPeople) {
        List<Person> peopleGoingUp = waitingPeople.stream().filter(p -> p.getDestinationFloor() > floor).collect(Collectors.toList());
        if (!peopleGoingUp.isEmpty()) {
            int peopleToLoad = Integer.min(availableLoad(), peopleGoingUp.size());
            peopleGoingUp = peopleGoingUp.subList(0, peopleToLoad);
            people.addAll(peopleGoingUp);
        }
        return peopleGoingUp;
    }

    public List<Person> dontLoad(List<Person> people) {
        return List.of();
    }

    private int availableLoad() {
        return elevatorCapacity - people.size();
    }

    public void moveDown() {
        this.floor--;
    }

    public void moveUp() {
        this.floor++;
    }

    public void dontMove() {
    }

    public void printPeople() {
        if (people.isEmpty()) {
            System.out.println("\tNo one");
        } else {
            people.forEach(p -> System.out.println("\t" + p));
        }
    }
}
