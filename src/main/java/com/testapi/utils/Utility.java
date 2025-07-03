package com.testapi.utils;

import java.util.Random;
import java.util.Set;

public class Utility {

    public static int generateUniqueNumber(Set<Long> ids) {
        int number;
        do {
            Random random = new Random();
            number = random.nextInt(Integer.MAX_VALUE);

        } while (ids.contains((long) number));
        return number;
    }

}
