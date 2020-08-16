package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.utils.Utils

fun String.truncate(count: Int = 16) {
    var remaining = count
    return takeWhile { it.isWhitespace() || remaining-- > 0 }
        .let { if (it == this) it.trimEnd() else "${it.trimEnd()}..." }
}

private val htmlRegex = Regex("""(<[^>]*>|&[^;]*;|"[^"]*"|'[^']*')""")

fun String.stripHtml() =
    replace(htmlRegex, " ").replace(Utils.whitespacesRegex, " ")
