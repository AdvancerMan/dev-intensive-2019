package ru.skillbranch.devintensive.models

import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    var lastVisit: Date? = Date(),
    var isOnline: Boolean = false
) {
    companion object Factory {
        private var maxId = -1
        fun makeUser(fullName: String): User {
            val words = fullName.split(Regex("""\\s"""))
            return User("${maxId++}", words.getOrNull(0), words.getOrNull(1), null)
        }
    }
}
