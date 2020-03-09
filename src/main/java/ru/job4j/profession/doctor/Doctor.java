package ru.job4j.profession.doctor;

import ru.job4j.profession.model.Profession;
import ru.job4j.profession.pacient.Pacient;

/**
 * Class Doctor
 * Класс описывает сущьность врач
 *
 * @author Petr B.
 * @version 1
 * @since 07.08.2019
 */
public class Doctor extends Profession {
    private String direction;
    private String hospitalAddress;
    private String typeOfAnesthesia;
    private int numberOfPatients;
    private double salary;

    public Doctor() {
    }

    public Doctor(String name, String surname, String education, String birthday, String direction,
                  String hospitalAddress, String typeOfAnesthesia, int numberOfPatients, double salary) {
        super(name, surname, education, birthday);
        this.direction = direction;
        this.hospitalAddress = hospitalAddress;
        this.typeOfAnesthesia = typeOfAnesthesia;
        this.numberOfPatients = numberOfPatients;
        this.salary = salary;
    }

    public String getDirection() {
        return direction;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public String getTypeOfAnesthesia() {
        return typeOfAnesthesia;
    }

    public int getNumberOfPatients() {
        return numberOfPatients;
    }

    public double getSalary() {
        return salary;
    }

    public String getDiagnose(Pacient pacient) {
        String status = pacient.getStatusOfHealthy() ? "Пациент абсолютно здоров!" : "Пациент болен!";
        return status;
    }
}
