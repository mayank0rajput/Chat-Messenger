package com.example.chatmessenger.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "english_chats")
data class EnglishMessageModel(
    val messageText : String,
    val isUser : Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
){}