package com.visteon.vfin.exception

sealed class DomainException(message: String) : RuntimeException(message)