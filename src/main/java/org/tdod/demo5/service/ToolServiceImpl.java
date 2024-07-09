package org.tdod.demo5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdod.demo5.entity.RentalAgreement;
import org.tdod.demo5.entity.Tool;
import org.tdod.demo5.entity.ToolTypeEnum;
import org.tdod.demo5.repository.ToolRepository;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
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
        rentalAgreement.setChargeDays(calculateChargeDays(checkoutDate, rentalAgreement.getDueDate()));
        rentalAgreement.setPrediscountCharge(calculatePreDiscountCharge(tool, rentalAgreement.getChargeDays()));
        rentalAgreement.setDiscountPercent(discountPercent);

        return rentalAgreement;
    }

    private LocalDate calculateDueDate(int rentalDayCount, LocalDate checkoutDate) {
        return checkoutDate.plusDays(rentalDayCount - 1);
    }

    private BigDecimal calculateDailyRentalCharge(Tool tool) {
        return tool.getToolType().getDailyCharge();
    }

    private int calculateChargeDays(LocalDate checkoutDate, LocalDate dueDate) {
        LocalDate currentDate = LocalDate.of(checkoutDate.getYear(), checkoutDate.getMonth(), checkoutDate.getDayOfMonth());
        LocalDate endDate = LocalDate.of(dueDate.getYear(), dueDate.getMonth(), dueDate.getDayOfMonth());;;

        int chargeDays = 0;
        while (!currentDate.isAfter(endDate)) {
            System.out.println("Evaluating " + currentDate);
            currentDate = currentDate.plusDays(1);
            if (!isHoliday(currentDate)) {
                chargeDays++;
            }
        }

        return chargeDays;
    }

    private boolean isHoliday(LocalDate date) {
        // Independence Day
        LocalDate independenceDay = LocalDate.of(date.getYear(), Month.JULY, 4);
        if (date.equals(independenceDay)) {
            System.out.println("Holiday found for independence day: " + date);
            return true;
        }

        // Labor Day : First Monday in September
        int currentYear = date.getYear();
        LocalDate firstMondayInSeptember = LocalDate.of(currentYear, Month.SEPTEMBER, 1);
        while (firstMondayInSeptember.getDayOfWeek() != DayOfWeek.MONDAY) {
            firstMondayInSeptember = firstMondayInSeptember.plusDays(1);
        }
        if (date.equals(firstMondayInSeptember)) {
            System.out.println("Holiday found for labor day: " + date);
            return true;
        }

        return false;

    }

    private BigDecimal calculatePreDiscountCharge(Tool tool, int chargeDays) {
        BigDecimal chargeDaysBigDecimal = BigDecimal.valueOf(chargeDays);
        return tool.getToolType().getDailyCharge().multiply(chargeDaysBigDecimal);
    }

}
