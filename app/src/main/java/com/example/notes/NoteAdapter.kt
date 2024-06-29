package com.example.notes

import NoteDataBaseHelper
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.ActivityNoteClassBinding
import kotlin.random.Random

class NoteAdapter(
    private var notes: List<Note>,
    private val context: Context,
    private val db: NoteDataBaseHelper
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(val binding: ActivityNoteClassBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding =
            ActivityNoteClassBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        val resources= holder.itemView.context.resources
        holder.binding.head.text = note.headname
        holder.binding.content.text = note.textabout
        holder.binding.noteContainer.background.setTint(note.color)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, MainActivity3::class.java).apply {
                putExtra("note_id", note.id)
                putExtra("note_title", note.headname)
                putExtra("note_content", note.textabout)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun refreshData(newNotes: List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }
}
