package ru.job4j.figures.square;

import ru.job4j.figures.shape.Shape;

public class Square implements Shape {

    public String draw() {
        StringBuffer pic = new StringBuffer();
        pic.append("xxxxxxxx");
        pic.append(System.lineSeparator());
        pic.append("xccccccx");
        pic.append(System.lineSeparator());
        pic.append("xccccccx");
        pic.append(System.lineSeparator());
        pic.append("xccccccx");
        pic.append(System.lineSeparator());
        pic.append("xxxxxxxx");
        return pic.toString();
    }
}
