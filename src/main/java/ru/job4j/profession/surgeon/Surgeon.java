package ru.job4j.profession.surgeon;

import ru.job4j.profession.doctor.Doctor;

/**
 * Class Surgeon
 * Класс описывает сущьность хирурга
 *
 * @author Petr B.
 * @version 1
 * @since 07.08.2019
 */
public class Surgeon extends Doctor {
    private int numberOfOperations;
    private String operationTime;

    public Surgeon(String name, String surname, String education, String birthday, String direction,
                   String hospitalAddress, String typeOfAnesthesia, int numberOfPatients, double salary,
                   int numberOfOperations, String operationTime) {
        super(name, surname, education, birthday, direction, hospitalAddress, typeOfAnesthesia, numberOfPatients, salary);
        this.numberOfOperations = numberOfOperations;
        this.operationTime = operationTime;
    }

    public int getNumberOfOperations() {
        return numberOfOperations;
    }

    public String getOperationTime() {
        return operationTime;
    }
}
