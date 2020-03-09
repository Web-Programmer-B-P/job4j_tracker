package ru.job4j.figures;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.figures.square.Square;
import ru.job4j.figures.triangle.Triangle;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Для этич тестов необходино убрать символ, переноса строки в циклах отрисовки фигур.
 */
public class PaintTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenDrawTriangle() {
        Paint triangle = new Paint();
        triangle.draw(new Triangle());
        assertThat(
                this.out.toString(),
                is(
                    new StringBuffer().
                        append(".......^.......").
                        append(System.lineSeparator()).
                        append("......^^^......").
                        append(System.lineSeparator()).
                        append(".....^^^^^.....").
                        append(System.lineSeparator()).
                        append("....^^^^^^^....").
                        append(System.lineSeparator()).
                        append("...^^^^^^^^^...").
                        append(System.lineSeparator()).
                        append("..^^^^^^^^^^^..").
                        append(System.lineSeparator()).
                        append(".^^^^^^^^^^^^^.").
                        append(System.lineSeparator()).
                        append("^^^^^^^^^^^^^^^").
                        append(System.lineSeparator()).toString()
                )
        );
    }

    @Test
    public void whenDrawSquare() {
        new Paint().draw(new Square());
        assertThat(
                this.out.toString(),
                is(
                    new StringBuffer().
                        append("xxxxxxxx").
                        append(System.lineSeparator()).
                        append("xccccccx").
                        append(System.lineSeparator()).
                        append("xccccccx").
                        append(System.lineSeparator()).
                        append("xccccccx").
                        append(System.lineSeparator()).
                        append("xxxxxxxx").
                        append(System.lineSeparator()).toString()
                )
        );
    }
}