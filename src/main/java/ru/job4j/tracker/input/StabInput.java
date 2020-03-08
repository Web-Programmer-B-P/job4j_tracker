package ru.job4j.tracker.input;

import ru.job4j.tracker.exception.MenuOutException;
import ru.job4j.tracker.interfaces.Input;

import java.util.Iterator;
import java.util.List;

/**
 * Class StabInput implements Input
 * Класс создан для тестов, эмитирует работу пользователя в консоле
 * @author Petr B.
 * @version 1.0
 */
public class StabInput implements Input {
    private List<String> answers;
    private Iterator it;

    public StabInput(List<String> anwsers) {
        this.answers = anwsers;
        this.it = anwsers.iterator();
    }

    public String ask(String question) {
        return this.it.next().toString();
    }

    public int ask(String question, List<Integer> range) {
        int key = Integer.parseInt(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Вы вышли за диапазон!");
        }
    }
}
