package com.example.notes

import NoteDataBaseHelper
import android.content.Intent
import android.icu.lang.UCharacter.VerticalOrientation
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dayList: ArrayList<DayofWeek>
    private lateinit var typeList: ArrayList<TypeOfNote>
    private lateinit var db: NoteDataBaseHelper
    private lateinit var noteList: ArrayList<Note>
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = NoteDataBaseHelper(this)

        noteList = ArrayList(db.getAllNotes())
        noteAdapter = NoteAdapter(noteList, this, db)
        val sglm=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.recyclernote.layoutManager = sglm
        sglm.gapStrategy=StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS

        binding.recyclernote.adapter = noteAdapter

        binding.btnlar.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(intent)
        }
        setupRecycler()
    }

    override fun onResume() {
        super.onResume()
        noteAdapter.refreshData(ArrayList(db.getAllNotes()))
    }

    private fun setupRecycler() {
        dayList = ArrayList<DayofWeek>()
        dayList.add(DayofWeek("Tue", "23", "Apr"))
        dayList.add(DayofWeek("Wed", "24", "Apr"))
        dayList.add(DayofWeek("Thu", "25", "Apr"))
        dayList.add(DayofWeek("Fri", "26", "Apr"))
        dayList.add(DayofWeek("Sat", "27", "Apr"))
        dayList.add(DayofWeek("Sun", "28", "Apr"))
        dayList.add(DayofWeek("Mon", "29", "Apr"))
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerDay.layoutManager = layoutManager
        val adapter = DayAdapter(dayList)
        binding.recyclerDay.adapter = adapter

        typeList = ArrayList<TypeOfNote>()
        val type1 = TypeOfNote("All")
        val type2 = TypeOfNote("Important")
        val type3 = TypeOfNote("Lecture notes")
        val type4 = TypeOfNote("To-do lists")
        val type5 = TypeOfNote("Shopping")
        typeList.add(type1)
        typeList.add(type2)
        typeList.add(type3)
        typeList.add(type4)
        typeList.add(type5)
        val layoutManager2 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerTime.layoutManager = layoutManager2
        val adapter2 = TypeAdapter(typeList)
        binding.recyclerTime.adapter = adapter2
    }
}
