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
import com.google.android.material.snackbar.Snackbar

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
                    view?.findNavController()?.navigate(
                            FinalFragmentDirections.actionFinalFragmentToHeritageFragment()
                    )
                }
        })


        finalViewModel.editText.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.apply {
                    val name = editTextName.text.toString()
                    finalViewModel.nameUpdate(name)
                    editTextName.visibility = View.INVISIBLE
                    textViewName.visibility = View.VISIBLE
                    textAddName.visibility = View.INVISIBLE
                }

                Snackbar.make(binding.root, "NPC saved", Snackbar.LENGTH_LONG).show()

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