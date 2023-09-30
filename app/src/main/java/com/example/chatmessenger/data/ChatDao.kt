package com.example.chatmessenger.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.chatmessenger.model.EnglishMessageModel
import com.example.chatmessenger.model.MathematicsMessageModel
import com.example.chatmessenger.model.MessageModel
import com.example.chatmessenger.model.PhysicsMessageModel

@Dao
interface ChatDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMessage(chatMessage: MessageModel)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEnglishMessage(chatMessage: EnglishMessageModel)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMathematicsMessage(chatMessage: MathematicsMessageModel)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPhysicsMessage(chatMessage: PhysicsMessageModel)
    @Update
    suspend fun update(chatMessage: MessageModel)
    @Query("Select * from chats_table")
    fun readAllChat():LiveData<List<MessageModel>>
    @Query("Select * from english_chats")
    fun readAllEnglishChat():LiveData<List<EnglishMessageModel>>
    @Query("Select * from physics_chats")
    fun readAllPhysicsChat():LiveData<List<PhysicsMessageModel>>

    @Query("Select * from mathematics_chats")
    fun readAllMathematicsChat():LiveData<List<MathematicsMessageModel>>
    @Query("Select id from chats_table where messageText =:messagetext")
    fun getid(messagetext: String) : Int
}