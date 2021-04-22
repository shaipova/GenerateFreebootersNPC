package com.example.generatefreebootersnpc

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.generatefreebootersnpc.database.NPCDatabaseDao

class FinalViewModelFactory (
    private val dataSource: NPCDatabaseDao,
    private val application: Application,
    private val npc: NonPlayerCharacter
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FinalViewModel::class.java)) {
            return FinalViewModel(dataSource, application, npc) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}