package com.visteon.vfin.sharedkernel.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [YearMonthValidator::class])
annotation class ValidYearMonth(
    val message: String = "Invalid year-month, must be yyyy-MM between 2000-01 and 2999-12",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)