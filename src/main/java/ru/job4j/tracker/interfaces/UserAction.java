package ru.job4j.tracker.interfaces;

import java.sql.SQLException;
import java.util.function.Consumer;

/**
 * Class UserAction
 *
 * @author Petr B.
 * @since 09.11.2019, 17:17
 */
public interface UserAction {
    int key();
    void execute(Input input, ITracker tracker, Consumer<String> output) throws SQLException;
    String info();
}
