package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Checkout {

    private static final String CHECKOUT_ERROR_MESSAGE = """
                When checking out, the following must be supplied:
                * The desired tool to checkout
                * The checkout date
                * The desired number of days to rent the tool (must be greater than or equal to 1)
                * The discount percent (must be in the range 0-100)
                """;

    private final Tool tool;
    private final LocalDate checkoutDate;
    private final int rentalDays;
    private final BigDecimal discountPercent;

    public Checkout(Tool tool, LocalDate checkoutDate, int rentalDays, int discountPercent) {
        if ((tool == null) || (checkoutDate == null) || (rentalDays < 1) || ((discountPercent < 0) || (discountPercent > 100))) {
            throw new IllegalArgumentException(CHECKOUT_ERROR_MESSAGE);
        }
        this.tool = tool;
        this.checkoutDate = checkoutDate;
        this.rentalDays = rentalDays;
        this.discountPercent = BigDecimal.valueOf(discountPercent * .01);
    }

    public Tool getTool() {
        return tool;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }
}
