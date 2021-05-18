package com.example.generatefreebootersnpc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.generatefreebootersnpc.database.SavedNPC

class SavedNPCAdapter : RecyclerView.Adapter<SavedNPCAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : SavedNPCAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.npc_card, parent, false) as TextView
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.textView.apply {
            text = "${item.npcName} \n\n${item.npcInfo}"

            setOnItemClickListener {
                onItemClickListener?.let {
                    it(item)
                }
            }
        }
    }

    override fun getItemCount() : Int {
        return differ.currentList.size
    }

    inner class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)


    private val differCallback = object : DiffUtil.ItemCallback<SavedNPC>(){
        override fun areItemsTheSame(oldItem: SavedNPC, newItem: SavedNPC): Boolean {
            return oldItem.npcId == newItem.npcId
        }

        override fun areContentsTheSame(oldItem: SavedNPC, newItem: SavedNPC): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    private var onItemClickListener: ((SavedNPC) -> Unit)? = null

    private fun setOnItemClickListener(listener: (SavedNPC) -> Unit) {
        onItemClickListener = listener
    }
}
