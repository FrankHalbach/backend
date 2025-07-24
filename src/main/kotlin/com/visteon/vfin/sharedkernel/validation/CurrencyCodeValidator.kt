package com.visteon.vfin.sharedkernel.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.modulith.NamedInterface
import java.util.*

@NamedInterface
class CurrencyCodeValidator : ConstraintValidator<ValidCurrency, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        if (value.isNullOrBlank()) return false
        return Currency.getAvailableCurrencies().any { it.currencyCode == value.uppercase() }
    }
}