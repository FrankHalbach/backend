package com.visteon.vfin.common.annotation

import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Service
@Validated
annotation class Command