package com.example.generatefreebootersnpc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.generatefreebootersnpc.database.SavedNPC

class SavedNPCAdapter : RecyclerView.Adapter<SavedNPCAdapter.ViewHolder>() {

    var data = listOf<SavedNPC>()
        set(value) {
            field = value
            notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : SavedNPCAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.npc_card, parent, false) as TextView
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = "${item.npcName} \n\n${item.npcInfo}"
    }

    override fun getItemCount() : Int {
        return data.size
    }

    inner class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

}
