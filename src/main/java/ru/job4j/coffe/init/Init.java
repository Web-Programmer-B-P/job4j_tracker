package ru.job4j.coffe.init;

import ru.job4j.coffe.CoffeeMashine;

/**
 * Class Init пользовательский интерфейс для кофе-машины
 */
public class Init {
    public static void main(String[] args) {
        System.out.println("Клиент выбрал кофе за 21 и положил купюру 50\n");
        CoffeeMashine customer = new CoffeeMashine();
        System.out.println("Аппарат выдал сдачу монетами: ");
        int[] back = customer.changes(50, 21);
        for (int index = 0, count = 0; index < back.length; index++) {
            count += back[index];
            if (index == back.length - 1) {
                System.out.print(back[index] + ".\nСдача = " + count);
            } else {
                System.out.print(back[index] + ",");
            }
        }
    }
}
