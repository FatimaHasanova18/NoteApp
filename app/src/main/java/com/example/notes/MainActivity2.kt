package com.example.notes
import NoteDataBaseHelper
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.notes.databinding.ActivityMain2Binding
import kotlin.random.Random

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var db:NoteDataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.arrow.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        db = NoteDataBaseHelper(this)
        binding.savebtn.setOnClickListener {
            val title = binding.headText.text.toString()
            val content = binding.Text.text.toString()
            val color = getRandomColor()
            val note = Note(
                id = 0,
                headname = title,
                textabout = content,
                color = color
            )
            db.insertTable(note)
            finish()
            Toast.makeText(this, "Note Saved", Toast.LENGTH_LONG).show()
        }
    }
    private fun getRandomColor(): Int {
        return Color.rgb(Random.nextInt(256), Random.nextInt(260), Random.nextInt(270))
    }
}