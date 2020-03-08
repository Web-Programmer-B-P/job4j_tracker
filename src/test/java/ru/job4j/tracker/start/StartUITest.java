package ru.job4j.tracker.start;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.StartUI;
import ru.job4j.tracker.input.StabInput;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.proxy.ConnectionRollback;
import ru.job4j.tracker.storage.TrackerSQL;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private TrackerSQL tracker;

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
        tracker = new TrackerSQL(ConnectionRollback.create(init()));
    }

    private final Consumer<String> output = new Consumer<String>() {
        private final PrintStream stdout = new PrintStream(out);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }

        @Override
        public String toString() {
            return out.toString();
        }
    };
    private static final String MENU =
            "0. Добавить новую заявку\n"
                    + "1. Показать все заявки\n"
                    + "2. Найти заявку по ID\n"
                    + "3. Найти заявку по её имени\n"
                    + "4. Обновить заявку по её ID\n"
                    + "5. Удалить заявку по ее ID\n"
                    + "6. Выход из программы";

    @Test
    public void whenAddItem() throws SQLException {
        List<String> input = Arrays.asList("0", "UniqueNAme", "Описание", "6");
        new StartUI(new StabInput(input), tracker, output).init();
        assertThat(tracker.findByName("UniqueNAme").iterator().next().getName(), is("UniqueNAme"));
    }

    @Test
    public void whenUpdateItem() throws SQLException {
        Item in = new Item("Имя", "Описание");
        String id = tracker.add(in);
        new StartUI(new StabInput(Arrays.asList("4", id, "Новое имя", "Новое описание", "6")), tracker, output).init();
        assertThat(tracker.findById(id).getName(), is("Новое имя"));
    }

    @Test
    public void whenFindItemByName() throws SQLException {
        List<String> search = Arrays.asList("0", "Имя", "Описание1", "0", "Имя", "Описание2", "0", "Одна другая", "Описание3",
                "0", "Имя", "Описание4", "3", "Имя", "6");
        new StartUI(new StabInput(search), tracker, output).init();
        assertThat(tracker.findByName("Имя").size(), is(3));
    }

    @Test
    public void whenFindItemById() throws SQLException {
        String id = tracker.add(new Item("Новая", "Заявка"));
        List<String> search = Arrays.asList("2", id, "6");
        new StartUI(new StabInput(search), tracker, output).init();
        String result = tracker.findById(id).getName();
        assertThat(result, is("Новая"));
    }

    @Test
    public void whenDeleteItemById() throws SQLException {
        String id = tracker.add(new Item("Новая", "Заявка"));
        List<String> search = Arrays.asList("5", id, "6");
        new StartUI(new StabInput(search), tracker, output).init();
        boolean result = tracker.findById(id) == null;
        assertThat(result, is(true));
    }

    @Test
    public void testOutPutAllItems() throws SQLException {
        Item third = new Item("Третья", "Проверочная2", 123434L);
        Item first = new Item("Первая", "Проверочная1", 1234L);
        String thirdId = tracker.add(third);
        String firstId = tracker.add(first);
        List<String> search = Arrays.asList("1", "6");
        new StartUI(new StabInput(search), tracker, output).init();
        String time1 = tracker.findById(thirdId).getTime();
        String time2 = tracker.findById(firstId).getTime();
        assertThat(
                output.toString(),
                is(
                        new StringBuffer()
                                .append(MENU + "\n")
                                .append("\n------------ Вывод всех заявок --------------")
                                .append("\n\tЗаявка номер: 1\n\tID: " + thirdId)
                                .append("\n\tИмя: Третья\n\tОписание: Проверочная2")
                                .append("\n\tДата создания: " + time1)
                                .append("\n==============================================")
                                .append("\n\tЗаявка номер: 2\n\tID: " + firstId)
                                .append("\n\tИмя: Первая\n\tОписание: Проверочная1")
                                .append("\n\tДата создания: " + time2)
                                .append("\n==============================================\n")
                                .append(MENU)
                                .append(System.lineSeparator()).toString()
                )
        );
    }

    @Test
    public void testOutPutFindById() throws SQLException {
        Item third = new Item("Третья", "Проверочная2", 123434L);
        Item first = new Item("Первая", "Проверочная1", 1234L);
        tracker.add(third);
        String firstId = tracker.add(first);
        List<String> search = Arrays.asList("2", firstId, "6");
        new StartUI(new StabInput(search), tracker, output).init();
        String time = tracker.findById(firstId).getTime();
        assertThat(
                output.toString(),
                is(
                        new StringBuffer()
                                .append(MENU + "\n")
                                .append("\n------------ Поиск заявки --------------\n")
                                .append("\n------------ Результат поиска ---------\nID: " + firstId)
                                .append("\nИмя: Первая\nОписание: Проверочная1")
                                .append("\nДата создания: " + time)
                                .append("\n======================================================\n\n")
                                .append(MENU)
                                .append(System.lineSeparator()).toString()
                )
        );
    }

    @Test
    public void testOutPutFindByName() throws SQLException {
        Item first = new Item("Первая", "first", 123434L);
        Item second = new Item("Первая", "second", 1234L);
        Item third = new Item("Левая", "third", 1234L);
        String firstId = tracker.add(first);
        String secondId = tracker.add(second);
        tracker.add(third);
        List<String> search = Arrays.asList("3", "Первая", "6");
        new StartUI(new StabInput(search), tracker, output).init();
        String time1 = tracker.findById(firstId).getTime();
        String time2 = tracker.findById(secondId).getTime();
        assertThat(
                output.toString(),
                is(
                        new StringBuffer()
                                .append(MENU + "\n")
                                .append("\n------------ Поиск всех заявок по названию --------------\n")
                                .append("\n------------ Результат поиска --------------\n")
                                .append("\tЗаявка номер: 1\n\tID: " + firstId)
                                .append("\n\tИмя: Первая\n\tОписание: first")
                                .append("\n\tДата создания: " + time1)
                                .append("\n==============================================\n")
                                .append("\tЗаявка номер: 2\n\tID: " + secondId)
                                .append("\n\tИмя: Первая\n\tОписание: second")
                                .append("\n\tДата создания: " + time2)
                                .append("\n==============================================\n")
                                .append(MENU)
                                .append(System.lineSeparator()).toString()
                )
        );
    }

    @Test
    public void testOutPutUpdateItem() throws SQLException {
        Item first = new Item("Первая", "Описание", 123434L);
        String id = tracker.add(first);
        List<String> search = Arrays.asList("4", id, "Updating", "Second", "6");
        new StartUI(new StabInput(search), tracker, output).init();
        String date = tracker.findById(id).getTime();
        assertThat(
                output.toString(),
                is(
                        new StringBuffer()
                                .append(MENU + "\n")
                                .append("\n------------ Обновление заявки --------------\n")
                                .append("\n---------- Результат обновления ----------------\n")
                                .append("\nID: " + id)
                                .append("\nИмя: Updating")
                                .append("\nОписание: Second")
                                .append("\nДата: " + date + "\n")
                                .append(MENU)
                                .append(System.lineSeparator()).toString()
                )
        );
    }

    @Test
    public void testOutPutDeleteItem() throws SQLException {
        Item first = new Item("Первая", "Описание", 123434L);
        String id = tracker.add(first);
        List<String> search = Arrays.asList("5", id, "6");
        new StartUI(new StabInput(search), tracker, output).init();
        assertThat(
                output.toString(),
                is(
                        new StringBuffer()
                                .append(MENU + "\n")
                                .append("\n------------ Удаление заявки --------------\n")
                                .append("Ваша заявка удалена!\n")
                                .append(MENU)
                                .append(System.lineSeparator()).toString()
                )
        );
    }

    @Test
    public void testOutPutAddItem() throws SQLException {
        List<String> search = Arrays.asList("0", "Add Item", "This`s a new item!", "6");
        new StartUI(new StabInput(search), tracker, output).init();
        assertThat(
                this.output.toString(),
                is(
                        new StringBuffer()
                                .append(MENU + "\n")
                                .append("\n------------ Добавление новой заявки --------------\n")
                                .append("Новая заявка с getId : " + tracker.findAll().iterator().next().getId() + "\n")
                                .append(MENU)
                                .append(System.lineSeparator()).toString()
                )
        );
    }
}