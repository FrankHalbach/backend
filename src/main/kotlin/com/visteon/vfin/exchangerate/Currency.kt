package com.visteon.vfin.exchangerate


@JvmInline
value class Currency(val code: String) {
    init {
        require(isSupported(code)) { "Unsupported currency code: $code" }
    }

    override fun toString() = code

    companion object {
        // List of all supported currencies (ISO 4217 based) - short list, add more currencies here if needed
        private val supported: Set<String> = setOf(
            "USD", "EUR", "GBP", "JPY", "AUD", "CAD", "CHF", "CNY", "SEK", "NZD",
            "NOK", "MXN", "INR", "BRL", "ZAR", "RUB", "KRW", "SGD", "HKD", "TRY",
            "DKK", "PLN", "HUF", "CZK", "ILS", "CLP", "PHP", "AED", "THB", "IDR",
            "RON", "MYR", "TWD", "SAR", "VND", "PKR", "EGP", "NGN", "BDT", "MKD"
        )
        fun from(code: String): Currency = Currency(code.uppercase())

        fun isSupported(code: String): Boolean =
            supported.contains(code.uppercase())

        val supportedCurrencies: Set<Currency> = supported.map { Currency(it) }.toSet()


    }
}