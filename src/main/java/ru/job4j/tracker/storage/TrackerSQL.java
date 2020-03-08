package ru.job4j.tracker.storage;

import org.postgresql.util.PSQLException;
import ru.job4j.tracker.interfaces.ITracker;
import ru.job4j.tracker.model.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class TrackerSQL
 *
 * @author Petr B.
 * @since 09.11.2019, 14:18
 */
public class TrackerSQL implements ITracker, AutoCloseable {
    private Connection connection;

    public TrackerSQL() {

    }

    public TrackerSQL(Connection conn) {
        connection = conn;
    }

    private static final String CREATE_TABLE = "CREATE TABLE item ("
            + "id serial PRIMARY KEY,"
            + "name varchar(200),"
            + "description varchar(500),"
            + "date timestamp without time zone);";

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            String[] type = {"TABLE"};
            DatabaseMetaData metadata = connection.getMetaData();
            try (ResultSet rs = metadata.getTables(null, null, "item", type);) {
                if (!rs.next()) {
                    try (Statement initSheme = connection.createStatement();) {
                        initSheme.execute(CREATE_TABLE);
                    }
                }
            }
        } catch (PSQLException psqle) {
            System.err.printf("PSQLState: %s\n%s", psqle.getSQLState(), psqle.getMessage());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return connection != null;
    }

    @Override
    public String add(Item item) throws SQLException {
        String result = null;
        try (PreparedStatement prepare = connection.prepareStatement("INSERT INTO item (name, description, date)"
                + " VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);) {
            prepare.setString(1, item.getName());
            prepare.setString(2, item.getDesc());
            prepare.setDate(3, new Date(item.getLongTime()));
            prepare.executeUpdate();
            try (ResultSet rs = prepare.getGeneratedKeys();) {
                if (rs.next()) {
                    result = String.valueOf(rs.getInt(1));
                }
            }
        }
        return result;
    }

    @Override
    public boolean replace(String id, Item item) throws SQLException {
        boolean result = false;
        try (PreparedStatement prepare = connection.prepareStatement("UPDATE item SET name=?, description=?, date=? WHERE id=?");) {
            prepare.setString(1, item.getName());
            prepare.setString(2, item.getDesc());
            prepare.setDate(3, new Date(item.getLongTime()));
            prepare.setInt(4, Integer.parseInt(id));
            if (prepare.executeUpdate() == 1) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        boolean result = false;
        try (PreparedStatement prepare = connection.prepareStatement("DELETE FROM item WHERE id=?");) {
            prepare.setInt(1, Integer.parseInt(id));
            if (prepare.executeUpdate() == 1) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public List<Item> findAll() throws SQLException {
        List<Item> result = new ArrayList<>();
        try (Statement prepare = connection.createStatement();) {
            try (ResultSet res = prepare.executeQuery("SELECT * FROM item");) {
                while (res.next()) {
                    String id = res.getString(1);
                    String name = res.getString(2);
                    String desc = res.getString(3);
                    long date = res.getDate(4).getTime();
                    result.add(new Item(id, name, desc, date));
                }
            }
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) throws SQLException {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement prepare = connection.prepareStatement("SELECT * FROM item WHERE name=?");) {
            prepare.setString(1, key);
            try (ResultSet res = prepare.executeQuery();) {
                while (res.next()) {
                    String id = res.getString(1);
                    String name = res.getString(2);
                    String desc = res.getString(3);
                    long date = res.getDate(4).getTime();
                    result.add(new Item(id, name, desc, date));
                }
            }
        }
        return result;
    }

    @Override
    public Item findById(String id) throws SQLException {
        Item result = null;
        try (PreparedStatement prepare = connection.prepareStatement("SELECT * FROM item WHERE id=?");) {
            prepare.setInt(1, Integer.parseInt(id));
            try (ResultSet res = prepare.executeQuery();) {
                if (res.next()) {
                    String primary = res.getString(1);
                    String name = res.getString(2);
                    String desc = res.getString(3);
                    long date = res.getDate(4).getTime();
                    result = new Item(primary, name, desc, date);
                }
            }
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
