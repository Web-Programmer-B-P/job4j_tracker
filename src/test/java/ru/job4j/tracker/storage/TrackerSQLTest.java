package ru.job4j.tracker.storage;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.proxy.ConnectionRollback;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class TrackerSQLTest
 *
 * @author Petr B.
 * @since 09.11.2019, 17:35
 */
public class TrackerSQLTest {
    private TrackerSQL track;

    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")


            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Before
    public void setUp() throws Exception {
        track = new TrackerSQL(ConnectionRollback.create(init()));
    }

    @Test
    public void createItem() throws SQLException {
        track.add(new Item("name", "desc"));
        assertThat(track.findByName("name").size(), is(1));
    }

    @Test
    public void checkConnection() {
        assertThat(track.init(), is(true));
    }

    @Test
    public void whenAddItem() throws SQLException {
        String primary = track.add(new Item("New Item", "It`s a new item", 123L));
        assertThat(track.findById(primary).getName(), is("New Item"));
    }

    @Test
    public void whenFindById() throws SQLException {
        String res = track.add(new Item("First", "Item", 123L));
        Item result = track.findById(res);
        assertThat(result.getName(), is("First"));
    }

    @Test
    public void whenFindAllItems() throws SQLException {
        List<Item> result = track.findAll();
        List<Item> expected = track.findAll();
        assertThat(result.size(), is(expected.size()));
    }

    @Test
    public void whenDeleteItem() throws SQLException {
        String res = track.add(new Item("Delete", "Item", 123L));
        assertThat(track.delete(res), is(true));
    }

    @Test
    public void whenFindByName() throws SQLException {
        track.add(new Item("UniqueName", "this is fist unique item", 1234L));
        track.add(new Item("UniqueName", "it is other one", 1234L));
        List<Item> res = track.findByName("UniqueName");
        assertThat(res.size(), is(2));
    }

    @Test
    public void whenUpdateItem() throws SQLException {
        String res = track.add(new Item("Item for update", "before update", 123L));
        track.replace(res, new Item("Item for update", "after update", 1234L));
        String result = track.findById(res).getDesc();
        assertThat(result, is("after update"));
    }
}