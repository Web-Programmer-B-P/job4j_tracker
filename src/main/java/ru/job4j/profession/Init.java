package ru.job4j.profession;

import ru.job4j.profession.dentist.Dentist;
import ru.job4j.profession.doctor.Doctor;
import ru.job4j.profession.model.Profession;
import ru.job4j.profession.pacient.Pacient;

/**
 * Class Init
 * Служит точкой входа, смотри package-info Init
 */
public class Init {
    public static void main(String[] args) {
        Profession sick = new Pacient("Игорь", "Петров", "Среднее", "19/09/1983",
                "Строитель", "Госпиталь г.Сильный", "Местная", 0,
                0.0, true);
        Profession profi = new Dentist("Иван", "Сидоров", "Профессор", "29/07/1933",
                "Стамотолог", " Nice National Hospital 3323", "Местная/Общий",
                20, 2500.99, "Взрослые", "Протезист");
        System.out.println("Методы родительского класса Professional: \nИмя: " + profi.getName() + "\nФамилия: "
                            + profi.getSurname() + "\nОбразование: " + profi.getEducation() + "\nДата рождения: "
                            + profi.getBirthday());
        Doctor doctor = (Doctor) profi;
        System.out.println("Методы класса Doctor:\nНаправление: " + doctor.getDirection() + "\nАдресс Госпиталя:"
                            + doctor.getHospitalAddress() + "\nТип анестезии: " + doctor.getTypeOfAnesthesia()
                            + "\nКол-во пациентов: " + doctor.getNumberOfPatients() + "\nЗп.: " + doctor.getSalary());
        Dentist denta = (Dentist) profi;
        System.out.println("Методы класса Dentist:\nТип пациентов с которыми работает: " + denta.getAdultOrChild()
                            + "\nНаправление в стамотологии: " + denta.getTreatmentOrProsthetics());
        System.out.println("=========================================================================================");
        System.out.println("Класс Pacient:\nИмя пациента: " + sick.getName() + "\nФамилия пациента: " + sick.getSurname()
                            + "\nДата рождения: " + sick.getBirthday() + "\nДиагноз: "
                            + ((Doctor) sick).getDiagnose((Pacient) sick));

    }
}
