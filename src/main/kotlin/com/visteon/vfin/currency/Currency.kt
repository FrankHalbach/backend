package com.visteon.vfin.currency

/**
 * Value class representing an ISO 4217 currency code (e.g. "USD", "EUR").
 *
 * Ensures the currency code is supported by [java.util.Currency].
 *
 * Use [Currency.from] to safely create an instance.
 *
 * Example:
 * ```
 * val usd = Currency.from("usd")  // -> Currency("USD")
 * ```
 *
 * @property code The 3-letter ISO 4217 currency code.
 * @throws IllegalArgumentException if the currency code is unsupported.
 */
@JvmInline
value class Currency(val code: String) {
    init {
        require(isSupported(code)) { "Unsupported currency code: $code" }
    }

    override fun toString(): String = code

    companion object {
        private val supported: Set<String> = java.util.Currency.getAvailableCurrencies()
            .map { it.currencyCode }
            .toSet()

        fun isSupported(code: String): Boolean =
            code.uppercase() in supported

        fun from(code: String): Currency = Currency(code.uppercase())

        val worldCurrencies: Set<Currency> =
            supported.map { Currency(it) }.toSet()
    }
}