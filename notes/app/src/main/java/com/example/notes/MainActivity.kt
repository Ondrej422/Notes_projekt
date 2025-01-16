// MainActivity.kt
package com.example.notes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNote: EditText
    private lateinit var textViewDate: TextView
    private lateinit var textViewSavedNote: TextView
    private lateinit var buttonSave: Button
    private lateinit var buttonViewNotes: Button

    private var notesList = mutableListOf<Pair<String, String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNote = findViewById(R.id.editTextNote)
        textViewDate = findViewById(R.id.textViewDate)
        textViewSavedNote = findViewById(R.id.textViewSavedNote)
        buttonSave = findViewById(R.id.buttonSave)
        buttonViewNotes = findViewById(R.id.buttonViewNotes)

        loadSavedNotes()

        buttonSave.setOnClickListener {
            saveNote()
        }

        buttonViewNotes.setOnClickListener {
            val intent = Intent(this, SavedNotesActivity::class.java)
            val stringList = notesList.map { "${it.first}: ${it.second}" }
            intent.putStringArrayListExtra("notesList", ArrayList(stringList))
            startActivity(intent)
        }
    }

    private fun loadSavedNotes() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val noteCount = sharedPref.getInt("noteCount", 0)

        for (i in 0 until noteCount) {
            val note = sharedPref.getString("note_$i", null)
            val date = sharedPref.getString("date_$i", null)
            if (note != null && date != null) {
                notesList.add(Pair(note, date))
            }
        }
    }

    private fun saveNote() {
        val noteText = editTextNote.text.toString()
        val currentDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

        if (noteText.isNotEmpty()) {
            textViewDate.text = "Saved on: $currentDate"
            textViewSavedNote.text = "Saved note: $noteText"

            notesList.add(Pair(noteText, "Saved on: $currentDate"))

            val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
            val noteCount = sharedPref.getInt("noteCount", 0)

            with(sharedPref.edit()) {
                putString("note_$noteCount", noteText)
                putString("date_$noteCount", "Saved on: $currentDate")
                putInt("noteCount", noteCount + 1)
                apply()
            }
        }
    }
}