package org.tdod.demo5.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Utility {

    private Utility() {}

    public static boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    public static boolean isWeekday(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.MONDAY
                || dayOfWeek == DayOfWeek.TUESDAY
                || dayOfWeek == DayOfWeek.WEDNESDAY
                || dayOfWeek == DayOfWeek.THURSDAY
                || dayOfWeek == DayOfWeek.FRIDAY;
    }

}
