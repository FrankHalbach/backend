package com.visteon.vfin.sharedkernel.types

import org.springframework.modulith.NamedInterface
import java.math.BigDecimal
import java.math.RoundingMode

@JvmInline
@NamedInterface
value class Money(private val amount: BigDecimal) : Comparable<Money> {

    constructor(amount: Double) : this(BigDecimal.valueOf(amount))
    constructor(amount: String) : this(BigDecimal(amount))

    init {
        require(amount.scale() <= 2) { "Money scale must be 2 or less" }
        require(amount >= BigDecimal.ZERO) { "Money amount cannot be negative" }
    }

    operator fun plus(other: Money): Money = Money(amount + other.amount)
    operator fun minus(other: Money): Money = Money(amount - other.amount)
    operator fun times(factor: Money): Money = Money(amount.multiply(factor.toBigDecimal()).setScale(2, RoundingMode.HALF_UP))
    operator fun div(divisor: Money): Money = Money(amount.divide(divisor.toBigDecimal(),2,RoundingMode.HALF_UP))


    override fun compareTo(other: Money): Int = amount.compareTo(other.amount)

    override fun toString(): String = amount.setScale(2, RoundingMode.HALF_UP).toPlainString()

    fun toBigDecimal() = amount
}
