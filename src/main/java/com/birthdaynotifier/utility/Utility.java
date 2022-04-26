package com.birthdaynotifier.utility;

import java.util.UUID;

public class Utility {

    public static boolean checkIfNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean checkIfNullOrEmpty(int num) {
        return num == 0;
    }

    public static String generateUUID(int length) {
        UUID randomUUID = UUID.randomUUID();
        String randomString = randomUUID.toString().replaceAll("[^0-9]", "");
        return randomString.substring(0, length);
    }

}
