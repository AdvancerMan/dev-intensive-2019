package ru.skillbranch.devintensive.utils

import java.util.*

object Utils {
    val whitespacesRegex = Regex("""(\s)+""")

    fun parseFullName(fullName: String?) =
        fullName
            ?.split(whitespacesRegex)
            ?.filterNot { it.isBlank() }
            ?.let { it.getOrNull(0) to it.getOrNull(1) }
            ?: null to null

    private fun toInitial(name: String?) =
        name?.trimStart()?.firstOrNull()?.toUpperCase()?.toString() ?: ""

    fun toInitials(firstName: String?, lastName: String?) =
        (toInitial(firstName) + toInitial(lastName)).ifEmpty { null }

    private val dictionary: Map<Char, String> = mutableListOf(
        'а' to "a",
        'б' to "b",
        'в' to "v",
        'г' to "g",
        'д' to "d",
        'е' to "e",
        'ё' to "e",
        'ж' to "zh",
        'з' to "z",
        'и' to "i",
        'й' to "i",
        'к' to "k",
        'л' to "l",
        'м' to "m",
        'н' to "n",
        'о' to "o",
        'п' to "p",
        'р' to "r",
        'с' to "s",
        'т' to "t",
        'у' to "u",
        'ф' to "f",
        'х' to "h",
        'ц' to "c",
        'ч' to "ch",
        'ш' to "sh",
        'щ' to "sh'",
        'ъ' to "",
        'ы' to "i",
        'ь' to "",
        'э' to "e",
        'ю' to "yu",
        'я' to "ya"
    )
        .apply { addAll(map {
            it.first.toUpperCase() to
                    (it.second.getOrNull(0)?.toUpperCase()?.toString() ?: "") +
                    it.second.drop(1)
        }) }
        .toMap()

    fun transliteration(payload: String, divider: String = " ") =
        payload
            .split(whitespacesRegex)
            .filterNot { it.isBlank() }
            .joinToString(divider) { word ->
                word.map { dictionary[it] ?: it.toString() }.joinToString("")
            }
}
