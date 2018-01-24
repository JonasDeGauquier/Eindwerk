package controller;

import java.util.regex.Pattern;

public class Validation {

    public static boolean checkLenght(String name, int lenght) {
        String regex = "^.{"+lenght+",}$";
        if (Pattern.matches(regex, name)) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean checkAlphabeticalAndNumeric(String name) {
        String regex = "\\b.*[ a-zA-Z ]{1,40}+.*\\b";
        if (Pattern.matches(regex, name)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkNumeric(String name) {
        String regex = "[0-9]{1,40}";
        if (Pattern.matches(regex, name)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkAlphabetical(String name) {
        String regex = "[A-Za-z ]{2,40}";
        if (Pattern.matches(regex, name)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkText(String name) {
        String regex = "[A-Za-z_ ]{2,40}";
        if (Pattern.matches(regex, name)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkHouseNumber(String name) {
        String regex = "[1-9][0-9]{0,3}[A-Za-z]{0,2}";
        if (Pattern.matches(regex, name)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkPostalCode(String name) {
        String regex = "[1-9][0-9]{3}";
        if (Pattern.matches(regex, name)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkPhone(String name) {
        String regex = "[\\+]{0,1}[0-9]{5,20}";
        if (Pattern.matches(regex, name)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkFirstName(String firstName) {
        String regex = "[A-Za-z\\s-]{2,40}";
        if (Pattern.matches(regex, firstName)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkLastName(String lastName) {
        String regex = "[A-Za-z\\s-]{2,40}";
        if (Pattern.matches(regex, lastName)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkEmail(String email) {
        String regex = "[A-Za-z0-9._%+-]+@[A-Za-z0-9]+[\\.][A-Za-z]{2,4}";
        if (Pattern.matches(regex, email)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkIdentitiecard(String indentiticard) {
        String regex = "[0-9]{3}+-[0-9]{7}+-[0-9]{2}";
        if (Pattern.matches(regex, indentiticard)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkRijksregisternr(String rijksregisternr) {
        String regex = "[0-9]{11}";
        if (Pattern.matches(regex, rijksregisternr)) {
            return true;
        } else {
            return false;
        }
    }
}
