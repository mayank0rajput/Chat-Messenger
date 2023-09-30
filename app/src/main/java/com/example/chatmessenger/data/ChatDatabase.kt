package com.example.chatmessenger.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.chatmessenger.model.EnglishMessageModel
import com.example.chatmessenger.model.MathematicsMessageModel
import com.example.chatmessenger.model.MessageModel
import com.example.chatmessenger.model.PhysicsMessageModel

@Database(entities = [MessageModel::class,
    EnglishMessageModel::class,
    MathematicsMessageModel::class,
    PhysicsMessageModel::class], version = 2,
    autoMigrations = [AutoMigration(from = 1, to = 2)],
    exportSchema = true)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun getdao() : ChatDao

    companion object {
        @Volatile
        private var INSTANCE: ChatDatabase? = null

        fun getDatabase(context: Context): ChatDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChatDatabase::class.java,
                    "chat_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}