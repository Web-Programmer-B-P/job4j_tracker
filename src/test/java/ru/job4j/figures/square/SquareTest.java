package ru.job4j.figures.square;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SquareTest {
    @Test
    public void whenTestDrawSquare() {
        Square square = new Square();
        assertThat(square.draw(),
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
                                append("xxxxxxxx").toString()
                )
        );
    }
}
