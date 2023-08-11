package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RentalAgreement {
    private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance(Locale.US);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yy", Locale.US);
    private static final NumberFormat PERCENT_FORMATTER = NumberFormat.getPercentInstance(Locale.US);

    private final Checkout checkout;

    public RentalAgreement(Checkout checkout) {
        if (checkout == null) {
            throw new IllegalArgumentException("checkout must be supplied");
        }
        this.checkout = checkout;
    }

    public String getToolCode() {
        return checkout.getTool().name();
    }

    public String getToolType() {
        return checkout.getTool().getToolType().getText();
    }

    public String getToolBrand() {
        return checkout.getTool().getToolBrand().getText();
    }

    public int getRentalDays() {
        return checkout.getRentalDays();
    }

    public LocalDate getCheckoutDate() {
        return checkout.getCheckoutDate();
    }

    public LocalDate getDueDate() {
        return checkout.getCheckoutDate().plusDays(checkout.getRentalDays());
    }

    public BigDecimal getDailyRentalCharge() {
        return checkout.getTool().getToolType().getDailyCharge();
    }

    public int getChargeDays() {
        return getChargeDays(getDueDate(), checkout.getCheckoutDate().plusDays(1), 0);
    }

    private int getChargeDays(LocalDate dueDate, LocalDate currentDay, int currentChargeDays) {
        if (currentDay.isAfter(dueDate)) {
            return currentChargeDays;
        }
        int chargeDays = currentChargeDays + (DayUtil.shouldNotCharge(currentDay, checkout.getTool()) ? 0 : 1);
        return getChargeDays(dueDate, currentDay.plusDays(1), chargeDays);
    }

    public BigDecimal getPreDiscountCharge() {
        return roundHalfUpToCents(
                getDailyRentalCharge().multiply(BigDecimal.valueOf(getChargeDays()))
        );
    }

    public BigDecimal getDiscountPercent() {
        return checkout.getDiscountPercent();
    }

    public BigDecimal getDiscountAmount() {
        return roundHalfUpToCents(
                getPreDiscountCharge().multiply(getDiscountPercent())
        );
    }

    private BigDecimal roundHalfUpToCents(BigDecimal val) {
        return val.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getFinalCharge() {
        return getPreDiscountCharge().subtract(getDiscountAmount());
    }

    public void printValues() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Tool code: " + getToolCode() + "\n" +
                "Tool type: " + getToolType() + "\n" +
                "Tool brand: " + getToolBrand() + "\n" +
                "Rental days: " + getRentalDays() + "\n" +
                "Check out date: " + DATE_TIME_FORMATTER.format(getCheckoutDate()) + "\n" +
                "Due date: " + DATE_TIME_FORMATTER.format(getDueDate()) + "\n" +
                "Daily rental charge: " + CURRENCY_FORMATTER.format(getDailyRentalCharge()) + "\n" +
                "Charge days: " + getChargeDays() + "\n" +
                "Pre-discount charge: " + CURRENCY_FORMATTER.format(getPreDiscountCharge()) + "\n" +
                "Discount percent: " + PERCENT_FORMATTER.format(getDiscountPercent()) + "\n" +
                "Discount amount: " + CURRENCY_FORMATTER.format(getDiscountAmount()) + "\n" +
                "Final charge: " + CURRENCY_FORMATTER.format(getFinalCharge()) + "\n";
    }
}
