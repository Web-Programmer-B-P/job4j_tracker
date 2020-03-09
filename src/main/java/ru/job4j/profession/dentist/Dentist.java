package ru.job4j.profession.dentist;

import ru.job4j.profession.doctor.Doctor;

/**
 * Class Dentist
 * Класс описывает сущьность стамотолога
 *
 * @author Petr B.
 * @version 1
 * @since 07.08.2019
 */
public class Dentist extends Doctor {
    private String adultOrChild;
    private String treatmentOrProsthetics;

    public Dentist() {
    }

    public Dentist(String adultOrChild, String treatmentOrProsthetics) {
        this.adultOrChild = adultOrChild;
        this.treatmentOrProsthetics = treatmentOrProsthetics;
    }

    public Dentist(String name, String surname, String education, String birthday, String direction,
                   String hospitalAddress,  String typeOfAnesthesia, int numberOfPatients, double salary,
                   String adultOrChild, String treatmentOrProsthetics) {
        super(name, surname, education, birthday, direction, hospitalAddress, typeOfAnesthesia, numberOfPatients, salary);
        this.adultOrChild = adultOrChild;
        this.treatmentOrProsthetics = treatmentOrProsthetics;
    }

    public String getAdultOrChild() {
        return adultOrChild;
    }

    public String getTreatmentOrProsthetics() {
        return treatmentOrProsthetics;
    }
}
