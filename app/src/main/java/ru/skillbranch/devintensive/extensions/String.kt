package ru.skillbranch.devintensive.extensions

fun String.truncate(count: Int = 16) {
    var remaining = count
    return takeWhile { it.isWhitespace() || remaining-- > 0 }
        .let { if (it == this) it.trimEnd() else "${it.trimEnd()}..." }
}
