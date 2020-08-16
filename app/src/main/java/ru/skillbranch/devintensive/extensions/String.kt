package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.utils.Utils

fun String.truncate(count: Int = 16): String {
    var remaining = count
    return takeWhile { c ->
        (c.isWhitespace() || remaining > 0)
            .also { if (it) remaining-- }
    }
        .let { if (it == this) it.trimEnd() else "${it.trimEnd()}..." }
}

private fun getRegexSegments(vararg beginToEnd: Pair<Char, Char>) =
    beginToEnd.joinToString("|", "(", ")") { (begin, end) -> "$begin[^$end]*$end" }

private val htmlRegex = Regex(getRegexSegments('<' to '>', '&' to ';', '"' to '"', '\'' to '\''))

fun String.stripHtml() =
    replace(htmlRegex, " ")
        .replace(Utils.whitespacesRegex, " ")
        .trim()
