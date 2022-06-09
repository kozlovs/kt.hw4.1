import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun calculateCommissionMasterCardPromo() {
        val previousPayments = 10_000_00
        val sum = 10_000_00
        val cardType = CardType.MASTERCARD

        val commission = calculateCommission(previousPayments, sum, cardType)
        assertEquals(0, commission)
    }

    @Test
    fun calculateCommissionMasterCardNotPromo() {
        val previousPayments = 10_000_00
        val sum = 299_00
        val cardType = CardType.MASTERCARD

        val commission = calculateCommission(previousPayments, sum, cardType)
        assertEquals(2179, commission)
    }

    @Test
    fun calculateCommissionMaestroPromo() {
        val previousPayments = 10_000_00
        val sum = 10_000_00
        val cardType = CardType.MAESTRO

        val commission = calculateCommission(previousPayments, sum, cardType)
        assertEquals(0, commission)
    }

    @Test
    fun calculateCommissionMaestroNotPromo() {
        val previousPayments = 91_000_00
        val sum = 10_000_00
        val cardType = CardType.MAESTRO

        val commission = calculateCommission(previousPayments, sum, cardType)
        assertEquals(9000, commission)
    }

    @Test
    fun calculateCommissionVisa() {
        val previousPayments = 10_000_00
        val sum = 10_000_00
        val cardType = CardType.VISA

        val commission = calculateCommission(previousPayments, sum, cardType)
        assertEquals(7500, commission)
    }

    @Test
    fun calculateCommissionVKpayNotCommission() {
        val previousPayments = 10_000_00
        val sum = 10_000_00
        val cardType = CardType.VKPAY

        val commission = calculateCommission(previousPayments, sum, cardType)
        assertEquals(0, commission)
    }

    @Test
    fun calculateCommissionMirMinCommission() {
        val previousPayments = 10_000_00
        val sum = 10_00
        val cardType = CardType.MIR

        val commission = calculateCommission(previousPayments, sum, cardType)
        assertEquals(3500, commission)
    }
}