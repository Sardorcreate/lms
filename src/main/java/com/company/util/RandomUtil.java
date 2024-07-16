package com.company.util;

import java.util.Random;

public class RandomUtil {

    public static String getRandomSMSCode() {

        Random random = new Random();

        return String.valueOf(random.nextInt(10000, 99999));
    }

    public static Integer getRandomNumbers(Integer listSize) {

        Random random = new Random();

        return random.nextInt(1, listSize);
    }
}
