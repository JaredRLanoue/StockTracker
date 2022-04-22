package com.example.stockcryptotracker.dto

data class ChartData(
    val chart: ChartData2
)

data class ChartData2(
    val result: List<ChartData3>
)

data class ChartData3(
    val timeStamp: List<Int>,
    val indicators: List<ChartData4>
)

data class ChartData4(
    val quote: List<ChartData5>
)

data class ChartData5(
    val close: List<Int>
)