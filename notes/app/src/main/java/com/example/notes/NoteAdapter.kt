// NoteAdapter.kt
package com.example.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val notes: List<Pair<String, String>>) : // Changed back to List
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteContent: TextView = itemView.findViewById(R.id.textViewNoteContent)
        val noteDate: TextView = itemView.findViewById(R.id.textViewNoteDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentItem = notes[position]
        holder.noteContent.text = currentItem.first
        holder.noteDate.text = currentItem.second
    }

    override fun getItemCount(): Int = notes.size
}