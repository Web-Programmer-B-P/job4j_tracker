package ru.job4j.tracker.actions;

import ru.job4j.tracker.interfaces.ITracker;
import ru.job4j.tracker.interfaces.Input;
import ru.job4j.tracker.model.Item;

import java.sql.SQLException;
import java.util.function.Consumer;

public class UpdateItem extends BaseAction {

    public UpdateItem(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, ITracker tracker, Consumer<String> output) throws SQLException {
        output.accept("\n------------ Обновление заявки --------------");
        String id = input.ask("Введите ID заявки которую будем обновлять: ");
        Item check = tracker.findById(id);
        if (check != null) {
            String name = input.ask("Введите имя заявки: ");
            String desc = input.ask("Введите описание заявки: ");
            Item item = new Item(name, desc);
            boolean update = tracker.replace(id, item);
            if (update) {
                Item newItem = tracker.findById(id);
                output.accept("\n---------- Результат обновления ----------------");
                output.accept("\nID: " + newItem.getId()
                        + "\nИмя: " + newItem.getName()
                        + "\nОписание: " + newItem.getDesc()
                        + "\nДата: " + newItem.getTime()
                );
            } else {
                output.accept("Ошибка не удалось обновить!");
            }
        } else {
            output.accept("Такой заявки нет!");
        }
    }
}
