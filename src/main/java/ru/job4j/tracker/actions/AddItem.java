package ru.job4j.tracker.actions;

import ru.job4j.tracker.interfaces.ITracker;
import ru.job4j.tracker.interfaces.Input;
import ru.job4j.tracker.model.Item;

import java.sql.SQLException;
import java.util.function.Consumer;

public class AddItem extends BaseAction {

    public AddItem(final int key, final String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, ITracker tracker, Consumer<String> output) throws SQLException {
        output.accept("\n------------ Добавление новой заявки --------------");
        String name = input.ask("Введите имя заявки: ");
        String desc = input.ask("Введите описание заявки: ");
        Item item = new Item(name, desc);
        output.accept("Новая заявка с getId : " + tracker.add(item));
    }
}
