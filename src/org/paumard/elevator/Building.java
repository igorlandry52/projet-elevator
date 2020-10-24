package org.paumard.elevator;

import org.paumard.elevator.model.DIRECTION;
import org.paumard.elevator.model.Person;
import org.paumard.elevator.model.WaitingList;
import org.paumard.elevator.student.DumbElevator;

import java.util.List;
import java.util.function.BiFunction;

public class Building {

    public static final int ELEVATOR_CAPACITY = 5;
    public static final int MAX_FLOOR = 4;

    public static void main(String[] args) {

        Elevator elevator = new DumbElevator(ELEVATOR_CAPACITY);
        ShadowElevator shadowElevator = new ShadowElevator(ELEVATOR_CAPACITY);

        WaitingList peopleWaitingPerFloor = new WaitingList();
        int totalNumberOfPeople = peopleWaitingPerFloor.count();
        System.out.println("# people waiting = " + totalNumberOfPeople);
        peopleWaitingPerFloor.print();

        elevator.startsAtFloor(1);
        elevator.peopleWaiting(peopleWaitingPerFloor.getLists());

        // ask which direction
        DIRECTION direction = elevator.chooseDirection();

        while (direction != DIRECTION.STOP) {

            System.out.println("\nGoing " + direction);
            int currentFloor = shadowElevator.getFloor();

            List<Person> peopleAtCurrentFloor = peopleWaitingPerFloor.getListFor(currentFloor);
            BiFunction<ShadowElevator, List<Person>, List<Person>> loadPeopleGoingTo = direction.getLoadPeopleGoingTo();
            List<Person> peopleToLoad = loadPeopleGoingTo.apply(shadowElevator, peopleAtCurrentFloor);
            peopleWaitingPerFloor.getListFor(currentFloor).removeAll(peopleToLoad);
            System.out.println("Loading ");
            printPeople(peopleToLoad);
            elevator.loadPeople(peopleToLoad);

            direction.move(shadowElevator);
            currentFloor = shadowElevator.getFloor();
            System.out.println("Arriving at floor " + currentFloor);
            elevator.arriveAtFloor(currentFloor);

            System.out.println("Unloading ");
            List<Person> peopleToUnload = shadowElevator.unLoadPeople();
            printPeople(peopleToUnload);
            elevator.unloadPeople(peopleToUnload);

            System.out.println("In the elevator:");
            shadowElevator.printPeople();

            direction = elevator.chooseDirection();
        }

        int currentFloor = shadowElevator.getFloor();
        elevator.stoppingAtFloor(currentFloor);
        System.out.println("\nSTOPPING at floor " + currentFloor);

        System.out.println("Unloading ");
        List<Person> peopleToUnload = shadowElevator.unLoadPeople();
        printPeople(peopleToUnload);
        elevator.unloadPeople(peopleToUnload);

        totalNumberOfPeople = peopleWaitingPerFloor.count();
        System.out.println("# people waiting = " + totalNumberOfPeople);
        peopleWaitingPerFloor.print();
        System.out.println("In the elevator:");
        shadowElevator.printPeople();
    }

    public static void printPeople(List<Person> people) {
        if (people.isEmpty()) {
            System.out.println("\tNo one");
        } else {
            people.forEach(p -> System.out.println("\t" + p));
        }
    }
}
