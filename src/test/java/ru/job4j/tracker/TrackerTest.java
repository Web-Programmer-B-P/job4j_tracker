package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.storage.Tracker;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        long created = System.currentTimeMillis();
        Item item = new Item("Первая", "Первая тестовая", created);
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("Первая заявка", "Пробная заявка в большой мир))", 123L);
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("Обновляем первую заявку этой записью", "Должно было получится", 234L);
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("Обновляем первую заявку этой записью"));
    }

    @Test
    public void testMethodDeleteReturnTrueIfSuccessefull() {
        Tracker tracker = new Tracker();
        Item row  = new Item("Первая", "Проверочная1", 123L);
        Item row1 = new Item("Вторая", "Проверочная2", 323L);
        Item row2 = new Item("Третья", "Проверочная3", 523L);
        Item row3 = new Item("Четвертая", "Проверочная4", 623L);
        tracker.add(row);
        tracker.add(row1);
        tracker.add(row2);
        tracker.add(row3);
        boolean status = tracker.delete(row.getId());
        assertThat(status, is(true));
        List<Item> result = tracker.findAll();
        List<Item> expected = Arrays.asList(tracker.findById(row1.getId()), tracker.findById(row2.getId()), tracker.findById(row3.getId()));
        assertEquals(expected, result);
    }

    @Test
    public void testFindByName() {
        Tracker object = new Tracker();
        Item third = new Item("Третья", "Проверочная2", 123434L);
        Item first  = new Item("Первая", "Проверочная1", 1234L);
        Item second = new Item("Первая", "Проверочная2", 123434L);
        object.add(third);
        object.add(first);
        object.add(second);
        List<Item> result = object.findByName("Первая");
        List<Item> expected = Arrays.asList(object.findById(first.getId()), object.findById(second.getId()));
        assertEquals(expected, result);
    }

    @Test
    public void testMethodFindAll() {
        Tracker object = new Tracker();
        Item first  = new Item("Первая", "Проверочная1", 1234L);
        Item second = new Item("Первая", "Проверочная2", 123434L);
        object.add(first);
        object.add(second);
        List<Item> result = object.findAll();
        List<Item> expected = Arrays.asList(object.findById(first.getId()), object.findById(second.getId()));
        assertEquals(expected, result);
    }

    @Test
    public void testMethodFindById() {
        Tracker object = new Tracker();
        Item first  = new Item("Первая", "Проверочная1", 1234L);
        object.add(first);
        Item result = object.findById(first.getId());
        String expected = "Item{id='" + first.getId() + "', name='Первая', desc='Проверочная1', time=1234}";
        assertThat(result.toString(), is(expected));
    }
}