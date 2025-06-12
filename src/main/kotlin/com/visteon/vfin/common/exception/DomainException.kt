package com.visteon.vfin.common.exception

sealed class DomainException(message: String) : RuntimeException(message)