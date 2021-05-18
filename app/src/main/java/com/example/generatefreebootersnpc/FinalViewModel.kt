package com.example.generatefreebootersnpc

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import com.example.generatefreebootersnpc.database.NPCDatabaseDao
import com.example.generatefreebootersnpc.database.SavedNPC
import kotlinx.coroutines.launch

class FinalViewModel(
    val database: NPCDatabaseDao,
    application: Application,
    val npc: NonPlayerCharacter,) : AndroidViewModel(application) {

    private var _npcName = MutableLiveData<String>()
    val npcName: LiveData<String>
        get() = _npcName

    fun nameUpdate(name: String) {
        _npcName.value = name
        npc.NPCname = name

            viewModelScope.launch {
                val saveNPC = SavedNPC()
                saveNPC.npcName = _npcName.value
                saveNPC.npcInfo = info
                insert(saveNPC)
            }
    }

    private var _navigateToStart = MutableLiveData<Boolean>()
    val navigateToStart: LiveData<Boolean>
        get() = _navigateToStart

    private var _editText = MutableLiveData<Boolean>()
    val editText: LiveData<Boolean>
        get() = _editText

    val info = """
            Heritage: ${npc.heritage}
            
            Gender: ${npc.gender}
            
            Alignment: ${npc.alignment}
            
            Occupation: ${npc.occupation}
            
            Motivation: ${npc.motivation}
            
            Virtue: ${npc.virtue}
            
            Vice: ${npc.vice}
        """.trimIndent()


    fun onCreateNew() {
        _navigateToStart.value = true
    }


    fun editTextName() {
        _editText.value = true
    }

    private suspend fun insert(saveCharacter: SavedNPC) = database.insert(saveCharacter)

}