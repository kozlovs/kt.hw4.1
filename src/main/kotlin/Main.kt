fun main() {
    val previousPayments = 10_000_00
    val sum = 10_000_00
    val cardType = CardType.MASTERCARD

    val commission = calculateCommission(previousPayments, sum, cardType)
    println("Предыдущие платежи: ${previousPayments.toDouble()/100} рублей\nСумма платежа: ${sum.toDouble()/100} рублей\nСумма комиссии: ${commission.toDouble()/100} рублей")
}

fun calculateCommission(previousPayments: Int, sum: Int, cardType: CardType = CardType.VKPAY): Int {
    val limitOnDay = 150_000_00
    val limitOnMonth = 600_000_00
    val limitOnDayVK = 15_000_00
    val limitOnMonthVK = 40_000_00

    val minSolidCommission = 35_00
    val percentageCommission = 0.0075
    val promoSolidCommission = 20_00
    val promoPercentageCommission = 0.006

    val minPromoSum = 300_00
    val maxPromoPreviousPayments = 75_000_00
    // в случае превышения лимита кидаем исключение
    when {
        cardType == CardType.VKPAY && sum > limitOnDayVK -> throw OverLimitException("Превышен дневной лимит по карте VKpay")
        cardType == CardType.VKPAY && (previousPayments + sum) > limitOnMonthVK -> throw OverLimitException("Превышен месячный лимит по карте VKpay")
        sum > limitOnDay -> throw OverLimitException("Превышен дневной лимит по карте")
        (previousPayments + sum) > limitOnMonth -> throw OverLimitException("Превышен месячный лимит по карте")
    }
    // расчет комиссии
    return when (cardType) {
        CardType.MASTERCARD, CardType.MAESTRO -> if (sum >= minPromoSum && previousPayments < maxPromoPreviousPayments) 0 else (sum * promoPercentageCommission + promoSolidCommission).toInt()
        CardType.VISA, CardType.MIR -> if (sum * percentageCommission > minSolidCommission) (sum * percentageCommission).toInt() else minSolidCommission
        else -> 0
    }
}