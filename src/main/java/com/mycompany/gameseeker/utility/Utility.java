package com.mycompany.gameseeker.utility;

import java.util.logging.Logger;

public class Utility {

    public static final String DS = "dark souls";
    

    public static String clearSpecialCharacter(String string) {
        String clearString = "";
        clearString = string.replaceAll("[^a-zA-Z]+", " ");
        return clearString;
    }

    public static String clearSpecialCharacterWithDigits(String string) {
        String clearString = "";
        clearString = string.replaceAll("[^a-zA-Z0-9]+", " ");
        return clearString;
    }

    public static String normalizeSearchQuery(String searchQuery, boolean lowercase) {
        String normalizedSearchQuery = searchQuery.replace(" ", "+");
        if (lowercase) {

            normalizedSearchQuery = normalizedSearchQuery.toLowerCase();
        }

        return normalizedSearchQuery;
    }

    public static String checkRomanNumber(String title) {
        if (title.contains(" I ")) {
            title = title.replace(" I ", " 1 ");
            return title;
        }
        if (title.contains(" II ")) {
            title = title.replace(" II ", " 2 ");
            return title;
        }
        if (title.contains(" III ")) {
            title = title.replace(" III ", " 3 ");
            return title;
        }
        if (title.contains(" IV ")) {
            title = title.replace(" IV ", " 4 ");
            return title;
        }
        if (title.contains(" V ")) {
            title = title.replace(" V ", " 5 ");
            return title;
        }
        if (title.contains(" VI ")) {
            title = title.replace("VI", " 6 ");
            return title;
        }
        if (title.contains(" VII ")) {
            title = title.replace(" VII ", " 7 ");
            return title;
        }
        if (title.contains(" VIII ")) {
            title = title.replace(" VIII ", " 8 ");
            return title;
        }
        if (title.contains(" IX ")) {
            title = title.replace(" IX ", " 9 ");
            return title;
        }
        if (title.contains(" X ")) {
            title = title.replace(" V ", " 10 ");
            return title;
        }
        if (title.contains(" XI ")) {
            title = title.replace(" XI ", " 11 ");
            return title;
        }
        if (title.contains(" XII ")) {
            title = title.replace(" XII ", " 12 ");
            return title;
        }
        if (title.contains(" XIII ")) {
            title = title.replace(" XIII ", " 13 ");
            return title;
        }
        if (title.contains(" XIV ")) {
            title = title.replace(" XIV ", " 14 ");
            return title;
        }
        if (title.contains(" XV ")) {
            title = title.replace(" XV ", " 15 ");
            return title;

        }

        if (title.contains(" XV")) {
            title = title.replace(" XV", " 15 ");
            return title;

        }

        if (title.contains(" XIV")) {
            title = title.replace(" XIV", " 14 ");
            return title;
        }

        if (title.contains(" XIII")) {
            title = title.replace(" XIII", " 13 ");
            return title;
        }

        if (title.contains(" XII")) {
            title = title.replace(" XII", " 12 ");
            return title;
        }

        if (title.contains(" X")) {
            title = title.replace(" V", " 10 ");
            return title;
        }

        if (title.contains(" VIII")) {
            title = title.replace(" VIII", " 8 ");
            return title;
        }

        if (title.contains(" VI")) {
            title = title.replace("VI", " 6 ");
            return title;
        }

        if (title.contains(" V")) {
            title = title.replace(" V", " 5 ");
            return title;
        }

        if (title.contains(" IV")) {
            title = title.replace(" IV", " 4 ");
            return title;
        }

        if (title.contains(" III")) {
            title = title.replace(" III", " 3 ");
            return title;
        }

        if (title.contains(" II")) {
            title = title.replace(" II", " 2 ");
            return title;
        }

        if (title.contains(" I")) {
            title = title.replace(" I", " 1 ");
            return title;
        }

        return title;
    }
}
