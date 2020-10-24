package org.paumard.elevator.model;

import org.paumard.elevator.ShadowElevator;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public enum DIRECTION {

    UP(
            ShadowElevator::loadPeopleGoingUpFromFloor,
            ShadowElevator::moveUp),
    STOP(
            ShadowElevator::dontLoad,
            ShadowElevator::dontMove),
    DOWN(
            ShadowElevator::loadPeopleGoingDownFromFloor,
            ShadowElevator::moveDown);

    private BiFunction<ShadowElevator, List<Person>, List<Person>> loadPeopleGoingTo;
    private Consumer<ShadowElevator> move;

    DIRECTION(BiFunction<ShadowElevator, List<Person>, List<Person>> loadPeopleGoingTo, Consumer<ShadowElevator> move) {
        this.loadPeopleGoingTo = loadPeopleGoingTo;
        this.move = move;
    }

    public BiFunction<ShadowElevator, List<Person>, List<Person>> getLoadPeopleGoingTo() {
        return this.loadPeopleGoingTo;
    }

    public void move(ShadowElevator shadowElevator) {
        this.move.accept(shadowElevator);
    }
}
