package ru.job4j.profession.builder;

import ru.job4j.profession.engineer.Engineer;

public class Builder extends Engineer {
    private String typeOfConstruction;
    private String[] typeOfMachinery;

    public String getTypeOfConstruction() {
        return typeOfConstruction;
    }

    public String[] getTypeOfMachinery() {
        return typeOfMachinery;
    }
}
