package com.visteon.vfin.validation


fun String.assertNotEmpty(fieldName: String = "String"): String {
    require(this.isNotEmpty()) {
        "$fieldName must not be empty."
    }
    return this
}

fun String.assertLength(min: Int, max: Int, fieldName: String = "String"): String {
    require(this.length in min..max) {
        "$fieldName must be between $min and $max characters."
    }
    return this
}

fun String.assertMatchesRegex(regex: Regex, fieldName: String = "String"): String {
    require(regex.matches(this)) {
        "$fieldName must match pattern: ${regex.pattern}"
    }
    return this
}
