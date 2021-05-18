package com.example.generatefreebootersnpc.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.generatefreebootersnpc.NonPlayerCharacter

@Dao
interface NPCDatabaseDao {
    @Insert
    suspend fun insert(npc: SavedNPC)

    @Update
    suspend fun update(npc: SavedNPC)

    @Query("SELECT * FROM saved_npc_table")
    fun getAll(): LiveData<List<SavedNPC>>

    @Delete
    suspend fun deleteNPC(npc: SavedNPC)
}