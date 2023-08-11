package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class RentalAgreementTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionBecauseDiscountPercentNotInRange() {
        new Checkout(Tool.JAKR, LocalDate.of(2015, 9, 3), 5, 101);
    }

    @Test
    public void shouldGiveTenPercentDiscountOnThreeDayWernerLadderRental() {
        // given
        Checkout checkout = new Checkout(Tool.LADW, LocalDate.of(2020, 7, 2), 3, 10);

        String expectedRentalAgreement = """
                Tool code: LADW
                Tool type: Ladder
                Tool brand: Werner
                Rental days: 3
                Check out date: 07/02/20
                Due date: 07/05/20
                Daily rental charge: $1.99
                Charge days: 2
                Pre-discount charge: $3.98
                Discount percent: 10%
                Discount amount: $0.40
                Final charge: $3.58
                """;

        // when
        RentalAgreement rentalAgreement = new RentalAgreement(checkout);

        // then
        Assert.assertEquals(expectedRentalAgreement, rentalAgreement.toString());
    }

    @Test
    public void shouldGiveTwentyFivePercentDiscountOnFiveDayStihlChainsawRental() {
        // given
        Checkout checkout = new Checkout(Tool.CHNS, LocalDate.of(2015, 7, 2), 5, 25);

        String expectedRentalAgreement = """
                Tool code: CHNS
                Tool type: Chainsaw
                Tool brand: Stihl
                Rental days: 5
                Check out date: 07/02/15
                Due date: 07/07/15
                Daily rental charge: $1.49
                Charge days: 3
                Pre-discount charge: $4.47
                Discount percent: 25%
                Discount amount: $1.12
                Final charge: $3.35
                """;

        // when
        RentalAgreement rentalAgreement = new RentalAgreement(checkout);

        // then
        Assert.assertEquals(expectedRentalAgreement, rentalAgreement.toString());
    }

    @Test
    public void shouldGiveZeroPercentDiscountOnSixDayDewaltJackhammerRental() {
        // given
        Checkout checkout = new Checkout(Tool.JAKD, LocalDate.of(2015, 9, 3), 6, 0);

        String expectedRentalAgreement = """
                Tool code: JAKD
                Tool type: Jackhammer
                Tool brand: DeWalt
                Rental days: 6
                Check out date: 09/03/15
                Due date: 09/09/15
                Daily rental charge: $2.99
                Charge days: 3
                Pre-discount charge: $8.97
                Discount percent: 0%
                Discount amount: $0.00
                Final charge: $8.97
                """;

        // when
        RentalAgreement rentalAgreement = new RentalAgreement(checkout);

        // then
        Assert.assertEquals(expectedRentalAgreement, rentalAgreement.toString());
    }

    @Test
    public void shouldGiveZeroPercentDiscountOnNineDayRidgidJackhammerRental() {
        // given
        Checkout checkout = new Checkout(Tool.JAKR, LocalDate.of(2015, 7, 2), 9, 0);

        String expectedRentalAgreement = """
                Tool code: JAKR
                Tool type: Jackhammer
                Tool brand: Ridgid
                Rental days: 9
                Check out date: 07/02/15
                Due date: 07/11/15
                Daily rental charge: $2.99
                Charge days: 5
                Pre-discount charge: $14.95
                Discount percent: 0%
                Discount amount: $0.00
                Final charge: $14.95
                """;

        // when
        RentalAgreement rentalAgreement = new RentalAgreement(checkout);

        // then
        Assert.assertEquals(expectedRentalAgreement, rentalAgreement.toString());
    }

    @Test
    public void shouldGiveFiftyPercentDiscountOnFourDayRidgidJackhammerRental() {
        // given
        Checkout checkout = new Checkout(Tool.JAKR, LocalDate.of(2020, 7, 2), 4, 50);

        String expectedRentalAgreement = """
                Tool code: JAKR
                Tool type: Jackhammer
                Tool brand: Ridgid
                Rental days: 4
                Check out date: 07/02/20
                Due date: 07/06/20
                Daily rental charge: $2.99
                Charge days: 1
                Pre-discount charge: $2.99
                Discount percent: 50%
                Discount amount: $1.50
                Final charge: $1.49
                """;

        // when
        RentalAgreement rentalAgreement = new RentalAgreement(checkout);

        // then
        Assert.assertEquals(expectedRentalAgreement, rentalAgreement.toString());
    }
}