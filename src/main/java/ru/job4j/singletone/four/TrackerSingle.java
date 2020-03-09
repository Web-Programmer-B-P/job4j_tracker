package ru.job4j.singletone.four;

import ru.job4j.tracker.model.Item;

import java.util.Arrays;
import java.util.Random;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;

public class TrackerSingle {
    private final Item[] items = new Item[10];
    private final Random rand = new Random();
    private int position = 0;

    private TrackerSingle() {

    }

    public static TrackerSingle getInstance() {
        return InnerSingle.INSTANCE;
    }

    private static final class InnerSingle {
        private static final TrackerSingle INSTANCE = TrackerSingle.getInstance();
    }

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        if (item.getId() == null) {
            item.setId(this.generateId());
        }
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Метод поиска всех заявок
     * @return возвращает копию массива items без значений null
     */
    public Item[] findAll() {
        Item[] result = Arrays.copyOf(this.items, this.position);
        return result;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        String result = format("%d%s", currentTimeMillis(), this.rand.nextInt());
        return result;
    }
}
