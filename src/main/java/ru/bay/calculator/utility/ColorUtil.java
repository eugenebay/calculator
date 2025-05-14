package ru.bay.calculator.utility;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ColorUtil {
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = rgb(107, 142, 35);
    public static final String TOMATO = rgb(255, 99, 71);
    public static final String GREY = rgb(96, 96, 96);

    public static String rgb(int r, int g, int b) {
        return "\u001B[38;2;" + r + ";" + g + ";" + b + "m";
    }
}
