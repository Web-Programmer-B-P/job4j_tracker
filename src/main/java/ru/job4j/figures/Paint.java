package ru.job4j.figures;

import ru.job4j.figures.shape.Shape;
import ru.job4j.figures.square.Square;
import ru.job4j.figures.triangle.Triangle;

/**
 * Class Paint
 * Класс Paint служит для отрисовки фигур.
 * @author Petr B.
 * @version 1.0
 * @since 15.08.2019
 */
public class Paint {
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }

    public static void main(String[] args) {
        Paint paint = new Paint();
        paint.draw(new Square());
        paint.draw(new Triangle());
    }
}
