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

        rentalAgreement.setTool(tool);
        rentalAgreement.setDailyRentalCharge(new BigDecimal("1.11"));

        return rentalAgreement;
    }

    private LocalDate calculateDueDate(int rentalDayCount, LocalDate checkoutDate) {
        LocalDate dueDate = checkoutDate.plusDays(rentalDayCount);
        return dueDate;
    }

}
