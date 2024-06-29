package com.example.notes
import NoteDataBaseHelper
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notes.MainActivity
import com.example.notes.Note
import com.example.notes.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivityMain3Binding
    private lateinit var db: NoteDataBaseHelper
    private var noteId: Int = -1
    private lateinit var originalNote: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDataBaseHelper(this)

        noteId = intent.getIntExtra("note_id", -1)
        val noteTitle = intent.getStringExtra("note_title") ?: ""
        val noteContent = intent.getStringExtra("note_content") ?: ""
        val noteColor = intent.getIntExtra("note_color", 0)

        originalNote = Note(noteId, noteTitle, noteContent, noteColor)

        binding.headText.setText(noteTitle)
        binding.Text.setText(noteContent)
        binding.colorPicker.setBackgroundColor(noteColor)
binding.arrow.setOnClickListener {
    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
}
        binding.headText.setOnKeyListener { _, _, _ ->
            updateNote()
            false
        }

        binding.Text.setOnKeyListener { _, _, _ ->
            updateNote()
            false
        }
        binding.edit.setOnClickListener {
            deleteNote()
        }
    }

    override fun onPause() {
        super.onPause()
        updateNote()
    }
    private fun deleteNote() {
        db.deleteNote(noteId)
        onBackPressed()
    }

    private fun updateNote() {
        val updatedTitle = binding.headText.text.toString()
        val updatedContent = binding.Text.text.toString()
        val updatedColor = (binding.colorPicker.background as? ColorDrawable)?.color ?: 0

        if (updatedTitle != originalNote.headname || updatedContent != originalNote.textabout || updatedColor != originalNote.color) {
            val updatedNote = Note(noteId, updatedTitle, updatedContent, updatedColor)
            db.updateTable(updatedNote)
            originalNote = updatedNote
            Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show()
        }
    }
}
