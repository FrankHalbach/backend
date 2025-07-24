package com.visteon.vfin.sharedkernel.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import org.springframework.modulith.NamedInterface
import kotlin.reflect.KClass

@NamedInterface
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [CurrencyCodeValidator::class])
annotation class ValidCurrency(
    val message: String = "Unsupported currency code",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
