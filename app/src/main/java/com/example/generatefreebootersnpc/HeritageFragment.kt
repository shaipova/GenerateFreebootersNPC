package com.example.generatefreebootersnpc

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.generatefreebootersnpc.databinding.FragmentHeritageBinding


class HeritageFragment : Fragment() {

    lateinit var binding: FragmentHeritageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_heritage, container, false)

        val npc = NonPlayerCharacter()

        fun radioChecked(): Any {
            val checkedId = binding.radioGroup.checkedRadioButtonId
            // Do nothing if nothing is checked (id == -1)
            return if (-1 != checkedId) {
                when (checkedId) {
                    R.id.radioButtonElf -> npc.prevailingHeritage = "elf"
                    R.id.radioButtonHalfling -> npc.prevailingHeritage = "halfling"
                    R.id.radioButtonDwarf -> npc.prevailingHeritage = "dwarf"
                    R.id.radioButtonHuman -> npc.prevailingHeritage = "human"
                    else -> npc.prevailingHeritage = "something wrong"
                }
            } else "something wrong"
        }

        binding.passDataButton.setOnClickListener { view: View ->
            if (radioChecked() != "something wrong") {
                view.findNavController().navigate(
                    HeritageFragmentDirections.actionHeritageFragmentToAlignmentFragment(npc)
                )
            }
            else Toast.makeText(activity,"choose heritage", Toast.LENGTH_SHORT).show()
        }

        setHasOptionsMenu(true)

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