// SavedNotesActivity.kt
package com.example.notes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SavedNotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_notes)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val notesList = intent.getStringArrayListExtra("notesList")?.map {
            val parts = it.split(": ", limit = 2)
            Pair(parts[0], parts[1])
        } ?: listOf() // No need for toMutableList() anymore

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewNotes)
        recyclerView.adapter = NoteAdapter(notesList)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        recyclerView.layoutManager = layoutManager

        recyclerView.setHasFixedSize(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}