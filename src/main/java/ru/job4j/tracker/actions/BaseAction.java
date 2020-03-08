package ru.job4j.tracker.actions;

import ru.job4j.tracker.interfaces.ITracker;
import ru.job4j.tracker.interfaces.Input;
import ru.job4j.tracker.interfaces.UserAction;

import java.sql.SQLException;
import java.util.function.Consumer;

public abstract class BaseAction implements UserAction {
    private final int key;
    private final String name;

    protected BaseAction(final int key, final String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public int key() {
        return key;
    }

    @Override
    public String info() {
        return String.format("%s. %s", key, name);
    }

    public abstract void execute(Input input, ITracker tracker, Consumer<String> output) throws SQLException;
}
