package org.paumard.elevator.student;

import org.paumard.elevator.Elevator;
import org.paumard.elevator.model.DIRECTION;
import org.paumard.elevator.model.Person;

import java.util.ArrayList;
import java.util.List;

public class DumbElevator implements Elevator {
    private static String[] directions = {"UP", "STOP"};
    private static int round = 0;

    public DumbElevator(int capacity) {
    }

    @Override
    public void startsAtFloor(int initialFloor) {
    }

    @Override
    public void peopleWaiting(List<List<Person>> peopleByFloor) {

    }

    @Override
    public DIRECTION chooseDirection() {
        return DIRECTION.valueOf(directions[round++]);
    }

    @Override
    public void loadPeople(List<Person> peopleToLoad) {
    }

    @Override
    public void arriveAtFloor(int floor) {
    }

    @Override
    public void unloadPeople(List<Person> peopleToUnload) {
    }

    @Override
    public void stoppingAtFloor(int finalFloor) {
    }
}
