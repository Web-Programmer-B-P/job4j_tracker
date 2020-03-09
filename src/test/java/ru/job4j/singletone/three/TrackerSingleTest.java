package ru.job4j.singletone.three;

import org.junit.Test;
import ru.job4j.singletone.two.TrackerSingle;
import ru.job4j.tracker.model.Item;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerSingleTest {
    @Test
    public void singletoneWhithStaticFinalField() {
        TrackerSingle first = TrackerSingle.getInstance();
        TrackerSingle second = TrackerSingle.getInstance();
        first.add(new Item("Проверка", "Проверка", 123434L));
        String expected = first.findAll()[0].getName() + first.hashCode();
        String result = second.findAll()[0].getName() + second.hashCode();
        assertThat(result, is(expected));
    }
}
