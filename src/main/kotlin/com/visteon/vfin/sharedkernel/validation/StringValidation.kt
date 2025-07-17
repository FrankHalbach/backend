@file:org.springframework.modulith.NamedInterface
package com.visteon.vfin.sharedkernel.validation


fun String.assertNotEmpty(fieldName: String = "String"): String {
    require(this.isNotEmpty()) {
        "$fieldName must not be empty."
    }
    return this
}


fun String.assertMinLength(min: Int, fieldName: String = "String"): String {
    require(this.length >= min) {
        "$fieldName must be at least $min characters."
    }
    return this
}

fun String.assertMaxLength(max: Int, fieldName: String = "String"): String {
    require(this.length <= max) {
        "$fieldName must be at most $max characters."
    }
    return this
}

fun String.assertMatchesRegex(regex: Regex, fieldName: String = "String"): String {
    require(regex.matches(this)) {
        "$fieldName must match pattern: ${regex.pattern}"
    }
    return this
}
