package org.tdod.demo5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.tdod.demo5.entity.RentalAgreement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Demo5ApplicationTests {

    @Autowired
    Demo5Application demo5Application;

    @Test
    void contextLoads() {
    }

    @Test
    void testCheckout1() {
        String toolcodeStr = "JAKR";
        String rentalDayCountStr = "5";
        String discountPercentStr = "101";
        String checkoutDateStr = "09032015";

        try {
            RentalAgreement rentalAgreement = demo5Application.checkout(toolcodeStr, rentalDayCountStr, discountPercentStr, checkoutDateStr);
        } catch (Exception e) {
            assertThat(e.getMessage()).isEqualTo("400 BAD_REQUEST \"discount-percent must be between 0 and 100\"");
        }
    }

    @Test
    void testCheckout2() {
        String toolcodeStr = "LADW";
        String rentalDayCountStr = "3";
        String discountPercentStr = "10";
        String checkoutDateStr = "07022024";

        RentalAgreement rentalAgreement = demo5Application.checkout(toolcodeStr, rentalDayCountStr, discountPercentStr, checkoutDateStr);

        assertThat(rentalAgreement.getTool().getToolCode()).isEqualTo(toolcodeStr);
        assertThat(rentalAgreement.getTool().getToolType().toString()).isEqualTo("Ladder");
        assertThat(rentalAgreement.getTool().getBrand()).isEqualTo("Werner");
        assertThat(rentalAgreement.getRentalDays()).isEqualTo(3);
        LocalDate checkoutDate = LocalDate.of(2024, Month.JULY, 2);
        assertThat(rentalAgreement.getCheckoutDate()).isEqualTo(checkoutDate);
        LocalDate dueDate = LocalDate.of(2024, Month.JULY, 5);
        assertThat(rentalAgreement.getDueDate()).isEqualTo(dueDate);
        assertThat(rentalAgreement.getDailyRentalCharge()).isEqualTo(new BigDecimal("1.99"));
        assertThat(rentalAgreement.getChargeDays()).isEqualTo(2);
        assertThat(rentalAgreement.getPrediscountCharge()).isEqualTo(new BigDecimal("3.98"));
        assertThat(rentalAgreement.getDiscountPercent()).isEqualTo(Integer.valueOf(discountPercentStr));
        assertThat(rentalAgreement.getDiscountAmount()).isEqualTo(new BigDecimal("0.40"));
        assertThat(rentalAgreement.getFinalCharge()).isEqualTo(new BigDecimal("3.58"));
    }

    @Test
    void testCheckout3() {
        String toolcodeStr = "CHNS";
        String rentalDayCountStr = "5";
        String discountPercentStr = "25";
        String checkoutDateStr = "07022015";

        RentalAgreement rentalAgreement = demo5Application.checkout(toolcodeStr, rentalDayCountStr, discountPercentStr, checkoutDateStr);

        assertThat(rentalAgreement.getTool().getToolCode()).isEqualTo(toolcodeStr);
        assertThat(rentalAgreement.getTool().getToolType().toString()).isEqualTo("Chainsaw");
        assertThat(rentalAgreement.getTool().getBrand()).isEqualTo("Stihl");
        assertThat(rentalAgreement.getRentalDays()).isEqualTo(5);
        LocalDate checkoutDate = LocalDate.of(2015, Month.JULY, 2);
        assertThat(rentalAgreement.getCheckoutDate()).isEqualTo(checkoutDate);
        LocalDate dueDate = LocalDate.of(2015, Month.JULY, 7);
        assertThat(rentalAgreement.getDueDate()).isEqualTo(dueDate);
        assertThat(rentalAgreement.getDailyRentalCharge()).isEqualTo(new BigDecimal("1.49"));
        assertThat(rentalAgreement.getChargeDays()).isEqualTo(3);
        assertThat(rentalAgreement.getPrediscountCharge()).isEqualTo(new BigDecimal("4.47"));
        assertThat(rentalAgreement.getDiscountPercent()).isEqualTo(Integer.valueOf(discountPercentStr));
        assertThat(rentalAgreement.getDiscountAmount()).isEqualTo(new BigDecimal("1.12"));
        assertThat(rentalAgreement.getFinalCharge()).isEqualTo(new BigDecimal("3.35"));
    }

    @Test
    void testCheckout4() {
        String toolcodeStr = "JAKD";
        String rentalDayCountStr = "6";
        String discountPercentStr = "0";
        String checkoutDateStr = "09032015";

        RentalAgreement rentalAgreement = demo5Application.checkout(toolcodeStr, rentalDayCountStr, discountPercentStr, checkoutDateStr);

        assertThat(rentalAgreement.getTool().getToolCode()).isEqualTo(toolcodeStr);
        assertThat(rentalAgreement.getTool().getToolType().toString()).isEqualTo("Jackhammer");
        assertThat(rentalAgreement.getTool().getBrand()).isEqualTo("DeWalt");
        assertThat(rentalAgreement.getRentalDays()).isEqualTo(6);
        LocalDate checkoutDate = LocalDate.of(2015, Month.SEPTEMBER, 3);
        assertThat(rentalAgreement.getCheckoutDate()).isEqualTo(checkoutDate);
        LocalDate dueDate = LocalDate.of(2015, Month.SEPTEMBER, 9);
        assertThat(rentalAgreement.getDueDate()).isEqualTo(dueDate);
        assertThat(rentalAgreement.getDailyRentalCharge()).isEqualTo(new BigDecimal("2.99"));
        assertThat(rentalAgreement.getChargeDays()).isEqualTo(3);
        assertThat(rentalAgreement.getPrediscountCharge()).isEqualTo(new BigDecimal("8.97"));
        assertThat(rentalAgreement.getDiscountPercent()).isEqualTo(Integer.valueOf(discountPercentStr));
        assertThat(rentalAgreement.getDiscountAmount()).isEqualTo(new BigDecimal("0.00"));
        assertThat(rentalAgreement.getFinalCharge()).isEqualTo(new BigDecimal("8.97"));
    }

    @Test
    void testCheckout5() {
        String toolcodeStr = "JAKR";
        String rentalDayCountStr = "9";
        String discountPercentStr = "0";
        String checkoutDateStr = "07022015";

        RentalAgreement rentalAgreement = demo5Application.checkout(toolcodeStr, rentalDayCountStr, discountPercentStr, checkoutDateStr);

        assertThat(rentalAgreement.getTool().getToolCode()).isEqualTo(toolcodeStr);
        assertThat(rentalAgreement.getTool().getToolType().toString()).isEqualTo("Jackhammer");
        assertThat(rentalAgreement.getTool().getBrand()).isEqualTo("Ridgid");
        assertThat(rentalAgreement.getRentalDays()).isEqualTo(9);
        LocalDate checkoutDate = LocalDate.of(2015, Month.JULY, 2);
        assertThat(rentalAgreement.getCheckoutDate()).isEqualTo(checkoutDate);
        LocalDate dueDate = LocalDate.of(2015, Month.JULY, 11);
        assertThat(rentalAgreement.getDueDate()).isEqualTo(dueDate);
        assertThat(rentalAgreement.getDailyRentalCharge()).isEqualTo(new BigDecimal("2.99"));
        assertThat(rentalAgreement.getChargeDays()).isEqualTo(6);
        assertThat(rentalAgreement.getPrediscountCharge()).isEqualTo(new BigDecimal("17.94"));
        assertThat(rentalAgreement.getDiscountPercent()).isEqualTo(Integer.valueOf(discountPercentStr));
        assertThat(rentalAgreement.getDiscountAmount()).isEqualTo(new BigDecimal("0.00"));
        assertThat(rentalAgreement.getFinalCharge()).isEqualTo(new BigDecimal("17.94"));
    }

    @Test
    void testCheckout6() {
        String toolcodeStr = "JAKR";
        String rentalDayCountStr = "4";
        String discountPercentStr = "50";
        String checkoutDateStr = "07022020";

        RentalAgreement rentalAgreement = demo5Application.checkout(toolcodeStr, rentalDayCountStr, discountPercentStr, checkoutDateStr);

        assertThat(rentalAgreement.getTool().getToolCode()).isEqualTo(toolcodeStr);
        assertThat(rentalAgreement.getTool().getToolType().toString()).isEqualTo("Jackhammer");
        assertThat(rentalAgreement.getTool().getBrand()).isEqualTo("Ridgid");
        assertThat(rentalAgreement.getRentalDays()).isEqualTo(4);
        LocalDate checkoutDate = LocalDate.of(2020, Month.JULY, 2);
        assertThat(rentalAgreement.getCheckoutDate()).isEqualTo(checkoutDate);
        LocalDate dueDate = LocalDate.of(2020, Month.JULY, 6);
        assertThat(rentalAgreement.getDueDate()).isEqualTo(dueDate);
        assertThat(rentalAgreement.getDailyRentalCharge()).isEqualTo(new BigDecimal("2.99"));
        assertThat(rentalAgreement.getChargeDays()).isEqualTo(2);
        assertThat(rentalAgreement.getPrediscountCharge()).isEqualTo(new BigDecimal("5.98"));
        assertThat(rentalAgreement.getDiscountPercent()).isEqualTo(Integer.valueOf(discountPercentStr));
        assertThat(rentalAgreement.getDiscountAmount()).isEqualTo(new BigDecimal("2.99"));
        assertThat(rentalAgreement.getFinalCharge()).isEqualTo(new BigDecimal("2.99"));
    }

}
