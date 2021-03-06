package com.example.generatefreebootersnpc

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.example.generatefreebootersnpc.database.NPCDatabaseDao
import com.example.generatefreebootersnpc.database.SavedNPC
import kotlinx.coroutines.launch

class SavedNPCViewModel(
    val database: NPCDatabaseDao,
    application: Application) : AndroidViewModel(application) {

        private var _showSnackbar = MutableLiveData<Boolean>()
        val showSnackBar: LiveData<Boolean>
                get() = _showSnackbar

        val npcs = database.getAll()

        private fun insert(npc: SavedNPC){
                viewModelScope.launch {
                        database.insert(npc)
                }
        }


        fun doneShowingSnackbar() {
                _showSnackbar.value = false
        }

    fun deleteNPC(npc: SavedNPC) = viewModelScope.launch {
        database.deleteNPC(npc)
        _showSnackbar.value = true

    }


}