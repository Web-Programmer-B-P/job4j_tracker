package ru.job4j.tracker.actions;

import ru.job4j.tracker.interfaces.ITracker;
import ru.job4j.tracker.interfaces.Input;

import java.sql.SQLException;
import java.util.function.Consumer;

public class Delete extends BaseAction {
    public Delete(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, ITracker tracker, Consumer<String> output) throws SQLException {
        output.accept("\n------------ Удаление заявки --------------");
        String id = input.ask("Введите ID заявки которую хотите удалить: ");
        boolean deleted = tracker.delete(id);
        if (deleted) {
            output.accept("Ваша заявка удалена!");
        } else {
            output.accept("Ошибка заявка не может быть удалена!");
        }
    }
}
