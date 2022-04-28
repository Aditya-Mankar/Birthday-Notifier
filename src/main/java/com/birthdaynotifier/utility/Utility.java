package com.birthdaynotifier.utility;

import java.util.*;

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

    public static String generateCode(int length) {
        UUID randomUUID = UUID.randomUUID();
        String randomString = randomUUID.toString().replaceAll("-", "");
        return randomString.substring(0, length);
    }

    public static String determineMonthFromNum(int birthMonth) {
        Map<Integer, String> monthsMap = new HashMap<>();
        List<String> months = List.of("January", "February", "March",
                "April", "May", "June", "July", "August", "September", "October", "November", "December");

        for(int i = 0; i < months.size(); i++)
            monthsMap.put(i + 1, months.get(i));

        return monthsMap.get(birthMonth);
    }

}
