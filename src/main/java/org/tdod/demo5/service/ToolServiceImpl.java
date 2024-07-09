package org.tdod.demo5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tdod.demo5.entity.RentalAgreement;
import org.tdod.demo5.entity.Tool;
import org.tdod.demo5.entity.ToolTypeEnum;
import org.tdod.demo5.repository.ToolRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
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

        calculateChargeDays(checkoutDate, rentalAgreement.getDueDate());


        return rentalAgreement;
    }

    private LocalDate calculateDueDate(int rentalDayCount, LocalDate checkoutDate) {
        return checkoutDate.plusDays(rentalDayCount);
    }

    private BigDecimal calculateDailyRentalCharge(Tool tool) {
        return tool.getToolType().getDailyCharge();
    }

    private int calculateChargeDays(LocalDate checkoutDate, LocalDate dueDate) {
        LocalDate currentDate = LocalDate.of(checkoutDate.getYear(), checkoutDate.getMonth(), checkoutDate.getDayOfMonth());
        LocalDate endDate = LocalDate.of(dueDate.getYear(), dueDate.getMonth(), dueDate.getDayOfMonth());;;

        while (!currentDate.isAfter(endDate)) {
            System.out.println(currentDate);
            currentDate = currentDate.plusDays(1);
        }

        return 0;
    }

    private void isHoliday() {

    }

}
