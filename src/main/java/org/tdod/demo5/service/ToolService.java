package org.tdod.demo5.service;

import org.tdod.demo5.entity.RentalAgreement;
import org.tdod.demo5.entity.Tool;

import java.time.LocalDate;
import java.util.List;

public interface ToolService {

    List<Tool> getAvailableTools(int offset, int size);

    RentalAgreement getRentalAgreement(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate);

}
