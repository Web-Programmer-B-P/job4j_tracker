package ru.job4j.coffee;

import org.junit.Test;
import ru.job4j.coffe.CoffeeMashine;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeeMashineTest {
    @Test
    public void testAddManyCoins() {
        CoffeeMashine customer = new CoffeeMashine();
        int[] input = customer.changes(50, 35);
        int[] expected = {10, 5};
        System.out.println(Arrays.toString(input));
        assertThat(input, is(expected));
    }
}
