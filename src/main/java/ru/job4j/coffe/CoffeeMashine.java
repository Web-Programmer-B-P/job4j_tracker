package ru.job4j.coffe;

import java.util.Arrays;

/**
 * Class CoffeeMashine
 *
 * @author Petr B.
 * @version 1
 * @since 25.08.2019
 */
public class CoffeeMashine {
    private static final int[] COINS = {1, 2, 5, 10};
    private int[] cashChange = new int[100];

    /**
     * Метод принимает купюру и цену на кофе и возвращает наименьшее колличество монет
     * @param value купюра
     * @param price цена кофе
     * @return int[]
     */
    public int[] changes(int value, int price) {
        int size = COINS.length - 1;
        int result = value - price;
        int tailNumber = 0;
        int count = 0;
        int temp;
        for (int index = 0; index < size; index++) {
            if (tailNumber != -1) {
                if (index == 0) {
                    tailNumber = result % COINS[size];
                    count = (result - tailNumber) / COINS[size];
                    this.cashChange = this.addManyCoins(this.cashChange, count, COINS[size]);
                    count = 0;
                } else {
                    temp = tailNumber % COINS[size - index];
                    if (temp != 0) {
                        this.cashChange = this.addManyCoins(this.cashChange, 1, COINS[size - index]);
                        tailNumber = temp;
                    } else {
                        if (tailNumber != COINS[size - index]) {
                            count = tailNumber / COINS[size - index];
                            this.cashChange = this.addManyCoins(this.cashChange, count, COINS[size - index]);
                            tailNumber = -1;
                        } else {
                            this.cashChange = this.addManyCoins(this.cashChange, 1, COINS[size - index]);
                            tailNumber = -1;
                        }
                    }
                }
            }
        }
        return cashChange;
    }

    /**
     * Метод addManyCoins добавляет в существующий массив колличество элементов и возвращает новый массив
     * @param array
     * @param count колличество элементов.
     * @param element элементы которые добавляем.
     * @return int[]
     */
    private int[] addManyCoins(int[] array, int count, int element) {
        int[] readyArray = array;
        if (readyArray[0] != 0) {
            readyArray = Arrays.copyOf(array, array.length + count);
        } else {
            readyArray = Arrays.copyOf(array, count);
        }

        for (int index = 0; index < readyArray.length; index++) {
            if (readyArray[index] == 0) {
                readyArray[index] = element;
            }
        }
        return readyArray;
    }
}
