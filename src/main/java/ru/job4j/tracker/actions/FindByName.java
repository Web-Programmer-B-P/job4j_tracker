package ru.job4j.tracker.actions;

import ru.job4j.tracker.interfaces.ITracker;
import ru.job4j.tracker.interfaces.Input;
import ru.job4j.tracker.model.Item;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Consumer;

public class FindByName extends BaseAction {

    public FindByName(int key, String name) {
        super(key, name);
    }
    @Override
    public void execute(Input input, ITracker tracker, Consumer<String> output) throws SQLException {
        output.accept("\n------------ Поиск всех заявок по названию --------------");
        String name = input.ask("Введите название заявки: ");
        List<Item> allItemsByName = tracker.findByName(name);
        if (!allItemsByName.isEmpty()) {
            output.accept("\n------------ Результат поиска --------------");
            int index = 0;
            for (Item el : allItemsByName) {
                output.accept("\tЗаявка номер: " + ((int) ++index));
                output.accept("\tID: " + el.getId());
                output.accept("\tИмя: " + el.getName());
                output.accept("\tОписание: " + el.getDesc());
                output.accept("\tДата создания: " + el.getTime());
                output.accept("==============================================");
            }
        } else {
            output.accept("Заявка с таким именем не найдена! Проверте имя еще раз!");
        }
    }
}
