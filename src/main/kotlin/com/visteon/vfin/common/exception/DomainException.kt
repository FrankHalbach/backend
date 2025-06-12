package com.visteon.vfin.common.exception

sealed class DomainException(message: String, val errorCode: String) : RuntimeException(message)