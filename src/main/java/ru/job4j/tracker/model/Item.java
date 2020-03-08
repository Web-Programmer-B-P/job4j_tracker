package ru.job4j.tracker.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Item {
    private String id;
    private String name;
    private String desc;
    private long time;

    public Item() {

    }

    public Item(String name, String desc, long time) {
        this.name = name;
        this.desc = desc;
        this.time = time;
    }

    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.time = System.currentTimeMillis();
    }

    public Item(String id, String name, String desc, long time) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        String correctTime;
        SimpleDateFormat need = new SimpleDateFormat("dd MMM yyyy HH:mm");
        Date resultdate = new Date(this.time);
        correctTime = need.format(resultdate);
        return correctTime;
    }

    public long getLongTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return time == item.time
                && Objects.equals(this.id, item.id)
                && Objects.equals(this.name, item.name)
                && Objects.equals(this.desc, item.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, time);
    }

    @Override
    public String toString() {
        return "Item{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", desc='" + desc + '\''
                + ", time=" + time
                + '}';
    }
}
