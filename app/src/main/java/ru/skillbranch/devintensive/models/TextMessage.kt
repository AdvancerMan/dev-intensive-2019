package ru.skillbranch.devintensive.models

import java.util.*

class TextMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    val text: String? = null
) : BaseMessage(id, from, chat, isIncoming, date) {
    override fun formatMessage() = formatMessage("сообщение: \"$text\"")
}
