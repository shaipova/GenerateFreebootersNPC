package com.example.generatefreebootersnpc.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SavedNPC::class], version = 1, exportSchema = false)
abstract class NPCDatabase : RoomDatabase() {

    abstract val npcDatabaseDao: NPCDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: NPCDatabase? = null

        fun getInstance(context: Context): NPCDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NPCDatabase::class.java,
                        "npc_database").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}