package org.tdod.demo5.service;

import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdod.demo5.entity.RentalAgreement;
import org.tdod.demo5.entity.Tool;
import org.tdod.demo5.repository.ToolRepository;
import org.tdod.demo5.util.Utility;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class ToolServiceImpl implements ToolService {

    @Autowired
    private ToolRepository toolRepository;

    public List<Tool> getAvailableTools(int offset, int size) {
        return toolRepository.getAvailableTools(offset, size);
    }

    public RentalAgreement getRentalAgreement(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate) {
        Tool tool = toolRepository.getToolByCode(toolCode);
        if (tool == null) {
            return null;
        }

        RentalAgreement rentalAgreement = new RentalAgreement();
        rentalAgreement.setTool(tool);
        rentalAgreement.setRentalDays(rentalDayCount);
        rentalAgreement.setCheckoutDate(checkoutDate);
        rentalAgreement.setDueDate(calculateDueDate(rentalDayCount, checkoutDate));
        rentalAgreement.setDailyRentalCharge(calculateDailyRentalCharge(tool));
        rentalAgreement.setChargeDays(calculateChargeDays(rentalAgreement, checkoutDate));
        rentalAgreement.setPrediscountCharge(calculatePreDiscountCharge(tool, rentalAgreement.getChargeDays()));
        rentalAgreement.setDiscountPercent(discountPercent);
        rentalAgreement.setDiscountAmount(calculateDiscountAmount(rentalAgreement.getPrediscountCharge(), discountPercent));
        rentalAgreement.setFinalCharge(calculateFinalCharge(rentalAgreement.getPrediscountCharge(), rentalAgreement.getDiscountAmount()));

        return rentalAgreement;
    }

    private LocalDate calculateDueDate(int rentalDayCount, LocalDate checkoutDate) {
        return checkoutDate.plusDays(rentalDayCount - 1);
    }

    private BigDecimal calculateDailyRentalCharge(Tool tool) {
        return tool.getToolType().getDailyCharge();
    }

    private int calculateChargeDays(RentalAgreement rentalAgreement, LocalDate checkoutDate) {
        Tool tool = rentalAgreement.getTool();
        LocalDate dueDate = rentalAgreement.getDueDate();
        int totalChargeDays = rentalAgreement.getRentalDays();
        // int totalChargeDays = 0;
        LocalDate currentDate = LocalDate.of(checkoutDate.getYear(), checkoutDate.getMonth(), checkoutDate.getDayOfMonth());
        LocalDate endDate = LocalDate.of(dueDate.getYear(), dueDate.getMonth(), dueDate.getDayOfMonth());;;

        while (!currentDate.isAfter(endDate)) {

            if (subtractCharge(tool, currentDate)) {
                totalChargeDays--;
            }

            currentDate = currentDate.plusDays(1);
        }

        return totalChargeDays;
    }

    private boolean subtractCharge(Tool tool, LocalDate date) {
        // Weekend
        if (Utility.isWeekend(date) && !tool.getToolType().isWeekendCharge()) {
            return true;
        }

        // Weekday
        if (Utility.isWeekday(date) && !tool.getToolType().isWeekdayCharge()) {
            return true;
        }

        // Independence Day
        LocalDate independenceDay = LocalDate.of(date.getYear(), Month.JULY, 4);
        if (date.equals(independenceDay) && !tool.getToolType().isHolidayCharge()) {
            System.out.println("Holiday found for independence day: " + date);
            return true;
        }

        // Labor Day : First Monday in September
        int currentYear = date.getYear();
        LocalDate firstMondayInSeptember = LocalDate.of(currentYear, Month.SEPTEMBER, 1);
        while (firstMondayInSeptember.getDayOfWeek() != DayOfWeek.MONDAY) {
            firstMondayInSeptember = firstMondayInSeptember.plusDays(1);
        }
        if (date.equals(firstMondayInSeptember) && !tool.getToolType().isHolidayCharge()) {
            System.out.println("Holiday found for labor day: " + date);
            return true;
        }

        return false;

    }

    private BigDecimal calculatePreDiscountCharge(Tool tool, int chargeDays) {
        BigDecimal chargeDaysBigDecimal = BigDecimal.valueOf(chargeDays);
        return tool.getToolType().getDailyCharge().multiply(chargeDaysBigDecimal);
    }

    private BigDecimal calculateDiscountAmount(BigDecimal preDiscountCharge, int discountPercent) {
        BigDecimal percentValue = BigDecimal.valueOf(discountPercent);
        BigDecimal decimalValue = percentValue.divide(BigDecimal.valueOf(100));

        return preDiscountCharge.multiply(decimalValue).setScale(2, RoundingMode.UP);
    }

    private BigDecimal calculateFinalCharge(BigDecimal preDiscountCharge, BigDecimal discountAmount) {
        return preDiscountCharge.subtract(discountAmount);
    }
}
