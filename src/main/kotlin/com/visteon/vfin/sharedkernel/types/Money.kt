package com.visteon.vfin.sharedkernel.types

import org.springframework.modulith.NamedInterface
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * A value class representing a monetary amount with up to 2 decimal places.
 *
 * Internally backed by [BigDecimal] with scale restricted to 2 or fewer digits after the decimal point.
 * Ensures the amount is never negative.
 *
 * Provides arithmetic operators (+, -, *, /) with appropriate rounding on multiplication and division.
 *
 * Constructors allow initialization from [BigDecimal], [Double], or [String].
 *
 * Example:
 * ```
 * val price = Money("19.99")
 * val tax = Money(1.50)
 * val total = price + tax
 * ```
 *
 * @property amount The monetary amount as [BigDecimal], scale ≤ 2, and ≥ 0.
 * @throws IllegalArgumentException if the scale is greater than 2 or the amount is negative.
 */
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
