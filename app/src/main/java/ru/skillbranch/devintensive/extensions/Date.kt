package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy") =
    SimpleDateFormat(pattern, Locale("ru")).format(this)

private fun plural(
    value: Int,
    preffix: String,
    one: String,
    twoToFour: String,
    tenToTwentyOrElse: String
) =
    preffix + if (value % 100 in 10..20) tenToTwentyOrElse else when (value % 10) {
        1 -> one
        in 2..4 -> twoToFour
        else -> tenToTwentyOrElse
    }

enum class TimeUnits(val toMilliseconds: Long) {
    SECOND(1000) {
        override fun plural(value: Int) =
            plural(value, "секунд", "у", "ы", "")
    },

    MINUTE(SECOND.toMilliseconds * 60) {
        override fun plural(value: Int) =
            plural(value, "минут", "у", "ы", "")
    },

    HOUR(MINUTE.toMilliseconds * 60) {
        override fun plural(value: Int) =
            plural(value, "час", "", "а", "ов")
    },

    DAY(HOUR.toMilliseconds * 24) {
        override fun plural(value: Int) =
            plural(value, "", "день", "дня", "дней")
    };

    fun toMs(value: Long) = value * toMilliseconds

    fun fromMs(milieseconds: Long) = milieseconds / toMilliseconds

    abstract fun plural(value: Int): String
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
            "${TimeUnits.MINUTE.plural(TimeUnits.MINUTE.fromMs(t).toInt())} назад"
        in TimeUnits.MINUTE.toMs(45)..TimeUnits.MINUTE.toMs(75) ->
            "час назад"
        in TimeUnits.MINUTE.toMs(75)..TimeUnits.HOUR.toMs(22) ->
            "${TimeUnits.HOUR.plural(TimeUnits.HOUR.fromMs(t).toInt())} назад"
        in TimeUnits.HOUR.toMs(22)..TimeUnits.HOUR.toMs(26) ->
            "день назад"
        in TimeUnits.HOUR.toMs(26)..TimeUnits.DAY.toMs(360) ->
            "${TimeUnits.DAY.plural(TimeUnits.DAY.fromMs(t).toInt())} назад"
        else -> if (t < 0) "в будущем" else "более года назад"
    }
