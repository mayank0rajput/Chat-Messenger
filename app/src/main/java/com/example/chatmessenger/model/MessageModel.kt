package com.example.chatmessenger.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chats_table")
data class MessageModel(
    val messageText : String,
    val isUser : Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
}