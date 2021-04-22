package com.example.generatefreebootersnpc.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_npc_table")
data class SavedNPC(
    @PrimaryKey(autoGenerate = true)
    var npcId: Long = 0L,

    @ColumnInfo(name = "npc_name")
    var npcName: String? = "some name",

    @ColumnInfo(name = "npc_info")
    var npcInfo: String = "some info"
)