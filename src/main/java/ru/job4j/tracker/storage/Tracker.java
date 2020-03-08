package ru.job4j.tracker.storage;

import ru.job4j.tracker.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.lang.System.currentTimeMillis;

/**
 * @version $Id$
 * @since 0.1
 */
public class Tracker {
    private final List<Item> items = new ArrayList<>();
    private final Random rand = new Random();
    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        if (item.getId() == null) {
            item.setId(this.generateId());
        }
        this.items.add(item);
        return item;
    }

    /**
     * Метод обновляет заявку в случае её существования.
     * @param id
     * @param item
     * @return true/false
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        int index = 0;
        for (Item el : this.items) {
            if (el.getId().equals(id)) {
                item.setId(id);
                this.items.set(index, item);
                result = true;
                break;
            }
            index++;
        }
        return result;
    }

    /**
     * Метод удаляет заявку с существующим id
     * @param id
     * @return true/false
     */
    public boolean delete(String id) {
        boolean result = false;
        int index = 0;
        for (Item el : this.items) {
            if (el.getId().equals(id)) {
                this.items.remove(index);
                result = true;
                break;
            }
            index++;
        }
        return result;
    }

    /**
     * Метод поиска всех заявок
     * @return возвращает копию массива items без значений null
     */
    public List<Item> findAll() {
        List<Item> result = this.items;
        return result;
    }

    /**
     * Метод поска заявки по имени
     * @param key
     * @return возвращает найденые совпадения
     */
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        for (Item find : this.items) {
            if (find.getName().equals(key)) {
                result.add(find);
            }
        }
            return result;
    }

    /**
     * Метод поиска заявки по id
     * @param id
     * @return возращает найденый Item
     */
    public Item findById(String id) {
        Item result = null;
        for (Item find : this.items) {
            if (find.getId().equals(id)) {
                result = find;
            }
        }
        return result;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        String result = format("%d%s", currentTimeMillis(), valueOf(this.rand.nextInt()));
        return result;
    }
}