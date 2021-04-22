package com.example.generatefreebootersnpc

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.generatefreebootersnpc.database.NPCDatabaseDao

class SavedNPCViewModelFactory(
    private val dataSource: NPCDatabaseDao,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedNPCViewModel::class.java)) {
            return SavedNPCViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}