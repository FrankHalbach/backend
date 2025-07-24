@file:org.springframework.modulith.NamedInterface

package com.visteon.vfin.sharedkernel.exposedtypes

import com.visteon.vfin.sharedkernel.types.YearMonth
import org.jetbrains.exposed.v1.core.ColumnTransformer
import org.jetbrains.exposed.v1.core.Table

// extending the exposed tables by yearMonthInt and nullableYearMonthInt,

fun Table.yearMonthInt(name: String) = integer(name).transform(YearMonthTransformer())

fun Table.nullableYearMonthInt(name: String) = integer(name).nullable().transform(NullableYearMonthTransformer())

class YearMonthTransformer : ColumnTransformer<Int, YearMonth>{
    override fun unwrap(value: YearMonth): Int = value.toInt()
    override fun wrap(value: Int): YearMonth = YearMonth.fromInt(value)
}
class NullableYearMonthTransformer : ColumnTransformer<Int?, YearMonth?> {
    override fun unwrap(value: YearMonth?): Int? = value?.toInt()
    override fun wrap(value: Int?): YearMonth? = value?.let { YearMonth.fromInt(it) }
}