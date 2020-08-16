package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.humanizeDiff
import java.util.*

abstract class BaseMessage(
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()
) {
    abstract fun formatMessage(): String

    protected fun formatMessage(messageType: String) =
        (from?.firstName ?: "Неизвестный пользователь") +
                " ${if (isIncoming) "получил" else "отправил"} $messageType ${date.humanizeDiff()}"

    companion object AbstractFactory {
        private var maxId = -1
        fun makeMessage(from: User?, chat: Chat, date: Date, type: String, payload: Any?, isIncoming: Boolean = false) =
            when(type) {
                "text" -> TextMessage("${++maxId}", from, chat, isIncoming, date, payload as String)
                "image" -> ImageMessage("${++maxId}", from, chat, isIncoming, date, payload as String)
                else -> throw IllegalArgumentException("Illegal type $type")
            }
    }
}
