package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
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
        fun makeUser(fullName: String?): User {
            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User("${maxId++}", firstName, lastName, null)
        }
    }

    class Builder {
        lateinit var id: String
        var firstName: String? = null
        var lastName: String? = null
        var avatar: String? = null
        var rating: Int = 0
        var respect: Int = 0
        var lastVisit: Date? = Date()
        var isOnline: Boolean = false

        fun id(id: String) { this.id = id }
        fun firstName(firstName: String?) { this.firstName = firstName }
        fun lastName(lastName: String?) { this.lastName = lastName }
        fun avatar(avatar: String?) { this.avatar = avatar }
        fun rating(rating: Int) { this.rating = rating }
        fun respect(respect: Int) { this.respect = respect }
        fun lastVisit(lastVisit: Date?) { this.lastVisit = lastVisit }
        fun isOnline(isOnline: Boolean) { this.isOnline = isOnline }
        fun build() = User(id, firstName, lastName, avatar, rating, respect, lastVisit, isOnline)
    }
}
