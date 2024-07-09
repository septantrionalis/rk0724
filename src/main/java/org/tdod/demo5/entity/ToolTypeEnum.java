package org.tdod.demo5.entity;

import java.math.BigDecimal;

public enum ToolTypeEnum {

    CHAINSAW("Chainsaw", new BigDecimal("1.99"), true, true, false),
    LADDER("Ladder", new BigDecimal("1.49"), true, false, true),
    JACKHAMMER("Jackhammer", new BigDecimal("2.99"), true, false, false);

    private String name;
    private BigDecimal dailyCharge;
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;

    private ToolTypeEnum(String name, BigDecimal dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean hoidayCharge) {
        this.name = name;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = hoidayCharge;
    }

    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }

    public String toString() {
        return name;
    }
}
