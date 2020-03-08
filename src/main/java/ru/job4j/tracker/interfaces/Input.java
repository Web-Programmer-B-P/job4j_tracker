package ru.job4j.tracker.interfaces;

import java.util.List;

/**
 * Class Input
 *
 * @author Petr B.
 * @since 09.11.2019, 17:17
 */
public interface Input {
    String ask(String question);
    int ask(String question, List<Integer> range);
}
