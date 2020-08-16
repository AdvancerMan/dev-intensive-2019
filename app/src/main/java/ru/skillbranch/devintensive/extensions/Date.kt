package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy") =
    SimpleDateFormat(pattern, Locale("ru")).format(this)!!

private fun plural(
    value: Int,
    preffix: String,
    one: String,
    twoToFour: String,
    tenToTwentyOrElse: String
) =
    "$value $preffix" + if (value % 100 in 10..20) tenToTwentyOrElse else when (value % 10) {
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

fun Date.add(value: Long, units: TimeUnits) =
    (clone() as Date).apply { time += units.toMs(value) }

fun Date.humanizeDiff(date: Date = Date()): String {
    val t = time - date.time

    return when (val absT = abs(t)) {
        in TimeUnits.SECOND.toMs(1)..TimeUnits.SECOND.toMs(45) -> "несколько секунд"

        in TimeUnits.SECOND.toMs(45)..TimeUnits.SECOND.toMs(75) -> "минуту"

        in TimeUnits.SECOND.toMs(75)..TimeUnits.MINUTE.toMs(45) ->
            TimeUnits.MINUTE.plural(TimeUnits.MINUTE.fromMs(absT).toInt())

        in TimeUnits.MINUTE.toMs(45)..TimeUnits.MINUTE.toMs(75) -> "час"

        in TimeUnits.MINUTE.toMs(75)..TimeUnits.HOUR.toMs(22) ->
            TimeUnits.HOUR.plural(TimeUnits.HOUR.fromMs(absT).toInt())

        in TimeUnits.HOUR.toMs(22)..TimeUnits.HOUR.toMs(26) -> "день"

        in TimeUnits.HOUR.toMs(26)..TimeUnits.DAY.toMs(360) ->
            TimeUnits.DAY.plural(TimeUnits.DAY.fromMs(absT).toInt())

        in TimeUnits.SECOND.toMs(0)..TimeUnits.SECOND.toMs(1) -> return "только что"

        else -> return if (t < 0) "более года назад" else "более чем через год"
    }.let { if (t < 0) "$it назад" else "через $it" }
}
