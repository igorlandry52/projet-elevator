package org.paumard.elevator.model;

import org.paumard.elevator.Building;
import org.paumard.elevator.model.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WaitingList {

    private static final int MAX_NUMBER_OF_PEOPLE_IN_LINE = 4;
    private static Random random = new Random();
    private static List<String> names = new ArrayList<>();

    static {
        Path path = Path.of("files/first-name.txt");
        try (Stream<String> lines = Files.lines(path)) {
            names = lines.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<List<Person>> peopleWaitingPerFloor;

    public WaitingList() {
        this.peopleWaitingPerFloor = new ArrayList<>();
        for (int floor = 0; floor < Building.MAX_FLOOR; floor++) {
            peopleWaitingPerFloor.add(createWaitingPeople(floor));
        }
    }

    public void print() {
        for (int index = 0; index < peopleWaitingPerFloor.size(); index++) {
            int floor = index + 1;
            System.out.println("People waiting on floor " + floor);
            if (peopleWaitingPerFloor.get(index).isEmpty()) {
                System.out.println("\tNo one");
            } else {
                peopleWaitingPerFloor.get(index).forEach(p -> System.out.println("\t" + p));
            }
        }
    }

    private List<Person> createWaitingPeople(int floor) {
        int numberOfPeopleInLine = random.nextInt(MAX_NUMBER_OF_PEOPLE_IN_LINE);
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < numberOfPeopleInLine; i++) {
            int destinationFloor = random.nextInt(Building.MAX_FLOOR);
            while (destinationFloor == floor) {
                destinationFloor = random.nextInt(Building.MAX_FLOOR);
            }
            String name = names.get(random.nextInt(names.size()));
            Person p = new Person(name, destinationFloor + 1);
            people.add(p);
        }
        return people;
    }

    public int count() {
        return peopleWaitingPerFloor.stream()
                .mapToInt(List::size)
                .sum();
    }

    public List<Person> getListFor(int floor) {
        int index = floor - 1;
        return this.peopleWaitingPerFloor.get(index);
    }

    public List<List<Person>> getLists() {
        List<List<Person>> defensiveCopy =
                peopleWaitingPerFloor.stream().map(ArrayList::new).collect(Collectors.toList());
        return defensiveCopy;
    }
}
