package com.example.generatefreebootersnpc

import android.app.Activity
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.generatefreebootersnpc.FinalFragmentArgs
import com.example.generatefreebootersnpc.FinalFragmentDirections
import com.example.generatefreebootersnpc.database.NPCDatabase
import com.example.generatefreebootersnpc.databinding.FragmentFinalBinding

class FinalFragment : Fragment() {

    lateinit var binding: FragmentFinalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_final, container, false)

        val application = requireNotNull(this.activity).application

        val arguments = FinalFragmentArgs.fromBundle(requireArguments())

        // Create an instance of the ViewModel Factory
        val dataSource = NPCDatabase.getInstance(application).npcDatabaseDao
        val viewModelFactory = FinalViewModelFactory(dataSource, application, arguments.`object`)

        // Get a reference to the ViewModel associated with this fragment
        val finalViewModel = ViewModelProvider(this, viewModelFactory).get(FinalViewModel::class.java)

        binding.setLifecycleOwner(this)
        binding.finalViewModel = finalViewModel

        setHasOptionsMenu(true)

        finalViewModel.navigateToStart.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                if (finalViewModel.npc.NPCname == "some name") {
                    Toast.makeText(activity, "... and Name?", Toast.LENGTH_SHORT).show()
                } else {
                    view?.findNavController()?.navigate(
                            FinalFragmentDirections.actionFinalFragmentToHeritageFragment()
                    )
                }
            }
        })

        finalViewModel.navigateToSavedNPC.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                view?.findNavController()?.navigate(
                        FinalFragmentDirections.actionFinalFragmentToSavedNPCFragment()
                )
            }
        })

        finalViewModel.editText.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.apply {
                    val name = editTextName.text.toString()
                    finalViewModel.nameUpdate(name)
                    editTextName.visibility = View.GONE
                    textViewName.visibility = View.VISIBLE
                }

                // hide keybord
                val imm =
                    context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view?.getWindowToken(), 0)
            }
        })


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }
}