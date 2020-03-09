package ru.job4j.profession.engineer;

import ru.job4j.profession.model.Profession;

public class Engineer extends Profession {
    private String direction;
    private String position;
    private int numberOfPeopleInSubmission;
    private double salary;

    public String getDirection() {
        return direction;
    }

    public String getPosition() {
        return position;
    }

    public int getNumberOfPeopleInSubmission() {
        return numberOfPeopleInSubmission;
    }

    public double getSalary() {
        return salary;
    }
}
