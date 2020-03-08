package ru.job4j.tracker.menu;

import ru.job4j.tracker.StartUI;
import ru.job4j.tracker.actions.*;
import ru.job4j.tracker.interfaces.ITracker;
import ru.job4j.tracker.interfaces.Input;
import ru.job4j.tracker.interfaces.UserAction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MenuTracker {
    private final Input input;
    private final ITracker tracker;
    private List<UserAction> actions = new ArrayList<>();
    private int position = 0;
    private final Consumer<String> output;


    public MenuTracker(Input input, ITracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }


    public int getActionsLentgh() {
        return actions.size();
    }


    /**
     *Метод заполнения списка действиями.
     * @param stop дополнительный параметр для выхода из программы.
     */
    public void fillActions(StartUI stop) {
        actions.add(new AddItem(position++, "Добавить новую заявку"));
        actions.add(new ShowAllItems(position++, "Показать все заявки"));
        actions.add(new FindById(position++, "Найти заявку по ID"));
        actions.add(new FindByName(position++, "Найти заявку по её имени"));
        actions.add(new UpdateItem(position++, "Обновить заявку по её ID"));
        actions.add(new Delete(position++, "Удалить заявку по ее ID"));
        actions.add(new ExitProgramm(position++, "Выход из программы", stop));
    }


    /**
     * Метод выбора действия из списка с actions по ключу выполняет действие.
     * @param key
     */
    public void select(int key) throws SQLException {
        actions.get(key).execute(input, tracker, output);
    }

    /**
     * Метод отрисовки меню
     */
    public void show() {
        for (UserAction action : actions) {
            if (action != null) {
                output.accept(action.info());
            }
        }
    }
}
