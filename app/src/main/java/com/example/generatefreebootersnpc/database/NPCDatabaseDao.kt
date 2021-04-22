package com.example.generatefreebootersnpc.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NPCDatabaseDao {
    @Insert
    suspend fun insert(npc: SavedNPC)

    @Update
    suspend fun update(npc: SavedNPC)

    @Query("SELECT * FROM saved_npc_table")
    fun getAll(): LiveData<List<SavedNPC>>

    @Query("DELETE FROM saved_npc_table")
    suspend fun deleteAll()
}