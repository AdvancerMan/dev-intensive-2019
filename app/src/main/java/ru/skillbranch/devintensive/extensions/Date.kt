package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy") =
    SimpleDateFormat(pattern, Locale("ru")).format(this)

enum class TimeUnits(val toMilliseconds: Long) {
    SECOND(1000), MINUTE(SECOND.toMilliseconds * 60),
    HOUR(MINUTE.toMilliseconds * 60), DAY(HOUR.toMilliseconds * 24);
}

fun Date.add(value: Long, units: TimeUnits) {
    time += value * units.toMilliseconds
}
