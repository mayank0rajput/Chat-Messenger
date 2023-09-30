package com.example.chatmessenger.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mathematics_chats")
data class MathematicsMessageModel(
    val messageText : String,
    val isUser : Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
){}