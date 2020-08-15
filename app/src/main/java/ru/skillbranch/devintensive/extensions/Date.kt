package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy") =
    SimpleDateFormat(pattern, Locale("ru")).format(this)

enum class TimeUnits(val toMilliseconds: Long) {
    SECOND(1000), MINUTE(SECOND.toMilliseconds * 60),
    HOUR(MINUTE.toMilliseconds * 60), DAY(HOUR.toMilliseconds * 24);

    fun toMs(value: Long) = value * toMilliseconds

    fun fromMs(milieseconds: Long) = milieseconds / toMilliseconds

    fun plural(value: Int): String {
        TODO()
    }
}

fun Date.add(value: Long, units: TimeUnits) {
    time += units.toMs(value)
}

fun Date.humanizeDiff(date: Date = Date()) =
    when(val t = date.time - time) {
        in TimeUnits.SECOND.toMs(0)..TimeUnits.SECOND.toMs(1) ->
            "только что"
        in TimeUnits.SECOND.toMs(1)..TimeUnits.SECOND.toMs(45) ->
            "несколько секунд назад"
        in TimeUnits.SECOND.toMs(45)..TimeUnits.SECOND.toMs(75) ->
            "минуту назад"
        in TimeUnits.SECOND.toMs(75)..TimeUnits.MINUTE.toMs(45) ->
            "${TimeUnits.MINUTE.fromMs(t)} минут назад"
        in TimeUnits.MINUTE.toMs(45)..TimeUnits.MINUTE.toMs(75) ->
            "час назад"
        in TimeUnits.MINUTE.toMs(75)..TimeUnits.HOUR.toMs(22) ->
            "${TimeUnits.HOUR.fromMs(t)} часов назад"
        in TimeUnits.HOUR.toMs(22)..TimeUnits.HOUR.toMs(26) ->
            "день назад"
        in TimeUnits.HOUR.toMs(26)..TimeUnits.DAY.toMs(360) ->
            "${TimeUnits.DAY.fromMs(t)} дней назад"
        else -> if (t < 0) "в будущем" else "более года назад"
    }
