package com.visteon.vfin.sharedkernel.validation

import com.visteon.vfin.sharedkernel.types.Year
import com.visteon.vfin.sharedkernel.types.YearMonth
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.modulith.NamedInterface

@NamedInterface
class YearMonthValidator : ConstraintValidator<ValidYearMonth, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        if (value.isNullOrBlank()) return true // let @NotBlank handle this

        return try {
            YearMonth.fromString(value) // throws if invalid
            true
        } catch (e: IllegalArgumentException) {
            context.disableDefaultConstraintViolation()
            context.buildConstraintViolationWithTemplate(
                "Invalid year-month: must be in format YYYY-MM and between ${Year.YEAR_MIN}-01 and ${Year.YEAR_MAX}-12."
            ).addConstraintViolation()
            false
        }
    }
}