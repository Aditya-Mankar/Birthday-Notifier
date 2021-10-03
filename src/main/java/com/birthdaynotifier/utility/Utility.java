package com.birthdaynotifier.utility;

import java.util.UUID;

public class Utility {

    public static String generateID(int length) {
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
        switch (birthMonth) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "INVALID";
        }
    }

    public static boolean checkIfNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean checkIfNullOrEmpty(int num) {
        return num == 0;
    }

}
