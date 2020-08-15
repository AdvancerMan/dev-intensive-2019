package ru.skillbranch.devintensive.utils

object Utils {
    private val whitespaces = Regex("""(\\s)*""")
    fun parseFullName(fullName: String?) =
        fullName
            ?.split(whitespaces)
            ?.filterNot { it.isBlank() }
            ?.let { it.getOrNull(0) to it.getOrNull(1) }
            ?: null to null
}
