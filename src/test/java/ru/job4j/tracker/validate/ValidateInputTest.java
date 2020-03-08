package ru.job4j.tracker.validate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.input.StabInput;
import ru.job4j.tracker.input.ValidateInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class ValidateInputTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream stdout = System.out;

    @Before
    public void loadOut() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void loadOutBack() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenPutIncorrectNumber() {
        List<Integer> range = Collections.singletonList(0);
        ValidateInput input = new ValidateInput(
                new StabInput(Arrays.asList("Неправильный пункт", "0"))
        );
        input.ask("Enter",  range);
        assertThat(
                this.out.toString(),
                is(
                        String.format("Вы ввели строку, а нужно число!%n")
                )
        );
    }

    @Test
    public void whenOutOfRange() {
        List<Integer> range = Collections.singletonList(0);
        ValidateInput input = new ValidateInput(
                new StabInput(Arrays.asList("123", "0"))
        );
        input.ask("Enter",  range);
        assertThat(
                this.out.toString(),
                is(
                        String.format("Вы вышли за диапазон!%n")
                )
        );
    }
}
