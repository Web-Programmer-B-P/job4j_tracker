package ru.job4j.profession.pacient;

import ru.job4j.profession.doctor.Doctor;

/**
 * Class Pacient
 * Класс описывает сущьность пациент
 *
 * @author Petr B.
 * @version 1
 * @since 07.08.2019
 */
public class Pacient extends Doctor {
    private boolean health;

    public Pacient() {
    }

    public Pacient(String name, String surname, String education, String birthday, String direction,
                   String hospitalAddress, String typeOfAnesthesia, int numberOfPatients, double salary, boolean health) {
        super(name, surname, education, birthday, direction, hospitalAddress, typeOfAnesthesia, numberOfPatients, salary);
        this.health = health;
    }

    public boolean getStatusOfHealthy() {
        boolean result = this.health ? true : false;
        return result;
    }
}
