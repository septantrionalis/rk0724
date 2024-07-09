package org.tdod.demo5.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.tdod.demo5.util.Utility;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalAgreement {

    private Tool tool;
    private int rentalDays;
    @JsonFormat(pattern="MM/dd/yyyy")
    private LocalDate checkoutDate;
    @JsonFormat(pattern="MM/dd/yyyy")
    private LocalDate dueDate;
    private BigDecimal dailyRentalCharge;
    private int chargeDays;
    private BigDecimal prediscountCharge;
    private int discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public void setDailyRentalCharge(BigDecimal dailyRentalCharge) {
        this.dailyRentalCharge = dailyRentalCharge;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public void setChargeDays(int chargeDays) {
        this.chargeDays = chargeDays;
    }

    public BigDecimal getPrediscountCharge() {
        return prediscountCharge;
    }

    public void setPrediscountCharge(BigDecimal prediscountCharge) {
        this.prediscountCharge = prediscountCharge;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(BigDecimal finalCharge) {
        this.finalCharge = finalCharge;
    }

    public String toString() {



        StringBuilder builder = new StringBuilder()
                .append("Tool Code: ")
                .append(tool.getToolCode()).append("\n")
                .append("Tool Type: " + tool.getToolType() + "\n")
                .append("Tool Brand: " + tool.getBrand() + "\n")
                .append("Rental Days: " + rentalDays + "\n")
                .append("Checkout Date: " + Utility.getFormattedDate(checkoutDate) + "\n")
                .append("Due Date: " + Utility.getFormattedDate(dueDate) + "\n")
                .append("Daily Rental Charge: $")
                .append(dailyRentalCharge).append("\n")
                .append("Charge Days: " + chargeDays + "\n")
                .append("Prediscount Charge: $" + prediscountCharge + "\n")
                .append("Discount Percent: " + discountPercent + "%\n")
                .append("Discount Amount: $" + discountAmount + "\n")
                .append("Final Charge: $" + finalCharge + "\n");

        return builder.toString();
    }

}
