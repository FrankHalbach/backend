package com.visteon.vfin.common

import com.github.f4b6a3.uuid.UuidCreator
import java.util.UUID


object Ids {
    
    /**
    * Generates a UUIDv7 (time-ordered).
    */
    fun new(): UUID = UuidCreator.getTimeOrderedEpoch()  // UUIDv7
    fun empty(): UUID = UuidCreator.getNil()
} 