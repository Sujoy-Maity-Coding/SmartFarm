package com.sujoy.smartfarming.presentation.Utils

fun calculateGrowthDays(sowingDate: Long): Int {
    val diff = System.currentTimeMillis() - sowingDate
    return (diff / (1000 * 60 * 60 * 24)).toInt().coerceAtLeast(0)
}