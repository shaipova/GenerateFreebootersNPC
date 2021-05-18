package com.example.generatefreebootersnpc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.generatefreebootersnpc.database.NPCDatabase
import com.example.generatefreebootersnpc.database.SavedNPC
import com.example.generatefreebootersnpc.databinding.FragmentSavedNPCBinding
import com.google.android.material.snackbar.Snackbar

class SavedNPCFragment : Fragment() {

    lateinit var binding: FragmentSavedNPCBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved_n_p_c, container, false)
        binding.setLifecycleOwner(this)

        val application = requireNotNull(this.activity).application
        val dataSource = NPCDatabase.getInstance(application).npcDatabaseDao
        val viewModelFactory = SavedNPCViewModelFactory(dataSource, application)
        val savedNPCViewModel = ViewModelProvider(this, viewModelFactory).get(SavedNPCViewModel::class.java)

        binding.savedNPCViewModel = savedNPCViewModel

        val adapter = SavedNPCAdapter()
        binding.recycleViewId.adapter = adapter


        savedNPCViewModel.npcs.observe(viewLifecycleOwner, Observer {
           it?.let {
               //adapter.data = it
               adapter.differ.submitList(it)
           }
        })

        savedNPCViewModel.showSnackBar.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        "Data delete",
                        Snackbar.LENGTH_LONG).show()
                savedNPCViewModel.doneShowingSnackbar()
            }
        })

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val npc = adapter.differ.currentList[position]
                savedNPCViewModel.deleteNPC(npc)
                }
            }


        ItemTouchHelper(itemTouchHelperCallback).apply{
            attachToRecyclerView(binding.recycleViewId)
        }

        return binding.root
    }


}