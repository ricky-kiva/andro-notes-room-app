package com.rickyslash.noteroomapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rickyslash.noteroomapp.R
import com.rickyslash.noteroomapp.databinding.ActivityMainBinding
import com.rickyslash.noteroomapp.helper.ViewModelFactory
import com.rickyslash.noteroomapp.ui.insert.NoteAddUpdateActivity

class MainActivity : AppCompatActivity() {

    private var _activityMainBinding: ActivityMainBinding? = null
    private val binding get() = _activityMainBinding

    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val mainViewModel = obtainViewModel(this@MainActivity)
        mainViewModel.getAllNotes().observe(this) { noteList ->
            if (noteList != null) {
                adapter.setListNotes(noteList)
            }
        }

        adapter = NoteAdapter()

        binding?.rvNotes?.layoutManager = LinearLayoutManager(this)
        binding?.rvNotes?.setHasFixedSize(true)
        binding?.rvNotes?.adapter = adapter

        binding?.fabAdd?.setOnClickListener { view ->
            if (view.id == R.id.fab_add) {
                val intent = Intent(this@MainActivity, NoteAddUpdateActivity::class.java)
                startActivity(intent)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _activityMainBinding = null
    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MainViewModel::class.java]
    }
}

// Component of Room:
// - Database: contains database holder. Act as main access point to database
// --- Need to be abstract class inherited from RoomDatabase
// --- Include entity list related to database, inside annotation
// --- contains abstract method with 0 argument & returns DAO class
// - Entity: represents table inside database
// - DAO: contains methods to access database (Data Access Object)

// What happened:
// - App use Room Database to get DAO related to that database
// - App will use every DAO to get entity of the database & save changes back to the database
// - APp will use entity to get & state value corresponding to table column inside database

// Room is an ORM Library (Object Relational Mapping)
// - it will map database object to Java object
// - it provides abstraction layer above SQLite to enable app to access database with SQLite functionality

// Difference between SQLite & Room Persistence:
// - There is SQL validation in Room
// - Need to manually update SQL query when SQLite database changed
// - Room can map database object to Java object without boilerplate
// - Room is suitable for LiveData