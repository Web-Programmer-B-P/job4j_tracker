package ru.job4j.figures.triangle;

import ru.job4j.figures.shape.Shape;

public class Triangle implements Shape {

    public String draw() {
        StringBuffer pic = new StringBuffer();
        pic.append(".......^.......");
        pic.append(System.lineSeparator());
        pic.append("......^^^......");
        pic.append(System.lineSeparator());
        pic.append(".....^^^^^.....");
        pic.append(System.lineSeparator());
        pic.append("....^^^^^^^....");
        pic.append(System.lineSeparator());
        pic.append("...^^^^^^^^^...");
        pic.append(System.lineSeparator());
        pic.append("..^^^^^^^^^^^..");
        pic.append(System.lineSeparator());
        pic.append(".^^^^^^^^^^^^^.");
        pic.append(System.lineSeparator());
        pic.append("^^^^^^^^^^^^^^^");
        return pic.toString();
    }
}
