package com.example.chatmessenger.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "physics_chats")
data class PhysicsMessageModel(
    val messageText : String,
    val isUser : Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)