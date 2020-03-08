package ru.job4j.tracker.input;

import ru.job4j.tracker.exception.MenuOutException;
import ru.job4j.tracker.interfaces.Input;

import java.util.List;

public class ValidateInput implements Input {
    private final Input input;

    public ValidateInput(final Input input) {
        this.input = input;
    }

    public String ask(String question) {
        return this.input.ask(question);
    }

    public int ask(String question, List<Integer> range) {
        boolean invalide = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalide = false;
            } catch (MenuOutException moe) {
                System.out.println(moe.getLocalizedMessage());
            } catch (NumberFormatException nfe) {
                System.out.println("Вы ввели строку, а нужно число!");
            }
        } while (invalide);

        return value;
    }
}
