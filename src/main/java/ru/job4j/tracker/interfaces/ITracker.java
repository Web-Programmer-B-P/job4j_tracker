package ru.job4j.tracker.interfaces;

import ru.job4j.tracker.model.Item;

import java.sql.SQLException;
import java.util.List;

/**
 * Class ITracker
 *
 * @author Petr B.
 * @since 09.11.2019, 14:07
 */
public interface ITracker {
    String add(Item item) throws SQLException;
    boolean replace(String id, Item item) throws SQLException;
    boolean delete(String id) throws SQLException;
    List<Item> findAll() throws SQLException;
    List<Item> findByName(String key) throws SQLException;
    Item findById(String id) throws SQLException;
}
