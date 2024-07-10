package org.tdod.demo5.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utility {

    private Utility() {}

    /**
     * Returns true if the date is a weekend.
     * @param date the date in question.
     * @return true if the date falls on a weekend.
     */
    public static boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    /**
     * Returns true if the date is a weekday.
     * @param date the date in question.
     * @return true if the date falls on a weekday.
     */
    public static boolean isWeekday(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.MONDAY
                || dayOfWeek == DayOfWeek.TUESDAY
                || dayOfWeek == DayOfWeek.WEDNESDAY
                || dayOfWeek == DayOfWeek.THURSDAY
                || dayOfWeek == DayOfWeek.FRIDAY;
    }

    /**
     * A generic date formatting method.
     * @param date the date to format.
     * @return the formatted date in string representation.
     */
    public static String getFormattedDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YY");
        return date.format(formatter);
    }

}
