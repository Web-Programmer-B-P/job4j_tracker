package ru.job4j.tracker;

import ru.job4j.tracker.input.ConsoleInput;
import ru.job4j.tracker.input.ValidateInput;
import ru.job4j.tracker.interfaces.ITracker;
import ru.job4j.tracker.interfaces.Input;
import ru.job4j.tracker.menu.MenuTracker;
import ru.job4j.tracker.storage.TrackerSQL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class StartUI {
    private final ITracker tracker;
    private boolean exit = true;
    private final Input input;
    private int key;
    private final Consumer<String> output;

    public StartUI(Input input, ITracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    public void init() throws SQLException {
        MenuTracker menu = new MenuTracker(input, tracker, output);
        List<Integer> range = new ArrayList<>();
        menu.fillActions(this);
        for (int index = 0; index < menu.getActionsLentgh(); index++) {
            range.add(index);
        }
        do {
            menu.show();
            key = input.ask("Выберите пунткт: ", range);
            menu.select(key);
        } while (exit);
    }

    public void exit() {
        exit = false;
    }

    /**
     * Точка входа в консольное приложение
     * @param args
     */
    public static void main(String[] args) throws SQLException {
        TrackerSQL trackerSQL = new TrackerSQL();
        trackerSQL.init();
        new StartUI(new ValidateInput(new ConsoleInput()), trackerSQL, System.out::println).init();
    }
}
