package com.example.generatefreebootersnpc

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.generatefreebootersnpc.databinding.FragmentAlignmentBinding

class AlignmentFragment : Fragment() {

    lateinit var binding: FragmentAlignmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_alignment, container, false)

        val npc = AlignmentFragmentArgs.fromBundle(requireArguments()).`object`

        fun radioChecked(): Any {
            val checkedId = binding.radioGroupAlignment.checkedRadioButtonId
            // Do nothing if nothing is checked (id == -1)
            return if (-1 != checkedId) {
                when (checkedId) {
                    R.id.radioButtonGood -> npc.prevailingAlignment = "good"
                    R.id.radioButtonLawful -> npc.prevailingAlignment = "lawful"
                    R.id.radioButtonNeutral -> npc.prevailingAlignment = "neutral"
                    R.id.radioButtonChaotic -> npc.prevailingAlignment = "chaotic"
                    R.id.radioButtonEvil -> npc.prevailingAlignment = "evil"
                    else -> npc.prevailingAlignment = "something wrong"
                }
            } else "something wrong"
        }

        binding.buttonSubmit.setOnClickListener { view: View ->
            if (radioChecked() != "something wrong") {
               view.findNavController().navigate(
                   AlignmentFragmentDirections.actionAlignmentFragmentToFinalFragment(npc)
               )
            }
            else Toast.makeText(activity,"choose alignment", Toast.LENGTH_SHORT).show()
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