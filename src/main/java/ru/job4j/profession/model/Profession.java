package ru.job4j.profession.model;

/**
 * Class Profession
 * Класс описывает базовое состояние для всех своих потомков, а так же методы для просмотра этого состояния
 *
 * @author Petr B.
 * @version 1
 * @since 07.08.2019
 */
public class Profession {
    private String name;
    private String surname;
    private String education;
    private String birthday;

    public Profession() {
    }

    public Profession(String name, String surname, String education, String birthday) {
        this.name = name;
        this.surname = surname;
        this.education = education;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEducation() {
        return education;
    }

    public String getBirthday() {
        return birthday;
    }
}
