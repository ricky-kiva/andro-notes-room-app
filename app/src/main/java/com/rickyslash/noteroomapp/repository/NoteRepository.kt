package com.rickyslash.noteroomapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.rickyslash.noteroomapp.database.Note
import com.rickyslash.noteroomapp.database.NoteDao
import com.rickyslash.noteroomapp.database.NoteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

// NoteRepository serves as bridge between database & app
// it handles database transactions, errors, & threading
class NoteRepository(application: Application) {
    private val mNotesDao: NoteDao
    // this 'executorService' will 'execute given tasks sequentially in order' 'within single thread'
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NoteRoomDatabase.getDatabase(application) // this will get 'INSTANCE' from 'NoteRoomDatabase'
        // get the 'abstract method' from the 'instance'
        mNotesDao = db.noteDao()
    }

    // this methods to below is overriding methods inside NoteDao

    // this method not using 'executorService' because it's not modifying database
    fun getAllNotes(): LiveData<List<Note>> = mNotesDao.getAllNotes()

    fun insert(note: Note) {
        executorService.execute { mNotesDao.insert(note) }
    }

    fun update(note: Note) {
        executorService.execute { mNotesDao.update(note) }
    }

    fun delete(note: Note) {
        executorService.execute { mNotesDao.delete(note) }
    }

}