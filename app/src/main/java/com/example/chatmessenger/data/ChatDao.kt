package com.example.chatmessenger.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.chatmessenger.model.MessageModel

@Dao
interface ChatDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMessage(chatMessage: MessageModel)
    @Update
    suspend fun update(chatMessage: MessageModel)
    @Query("Select * from chats_table")
    fun readAllChat():LiveData<List<MessageModel>>
    @Query("Select id from chats_table where messageText =:messagetext")
    fun getid(messagetext: String) : Int
}