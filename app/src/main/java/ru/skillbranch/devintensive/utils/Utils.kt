package ru.skillbranch.devintensive.utils

object Utils {
    private val whitespaces = Regex("""(\\s)*""")

    fun parseFullName(fullName: String?) =
        fullName
            ?.split(whitespaces)
            ?.filterNot { it.isBlank() }
            ?.let { it.getOrNull(0) to it.getOrNull(1) }
            ?: null to null

    private fun toInitial(name: String?) =
        name?.trimStart()?.firstOrNull()?.toUpperCase()?.toString() ?: ""

    fun toInitials(firstName: String?, lastName: String?) =
        (toInitial(firstName) + toInitial(lastName)).ifEmpty { null }
}
