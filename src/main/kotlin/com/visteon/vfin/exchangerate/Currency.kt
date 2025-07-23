package com.visteon.vfin.exchangerate


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

        val supportedCurrencies: Set<Currency> =
            supported.map { Currency(it) }.toSet()
    }
}