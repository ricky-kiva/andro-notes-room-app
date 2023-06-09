package com.rickyslash.noteroomapp.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.rickyslash.noteroomapp.database.Note
import com.rickyslash.noteroomapp.repository.NoteRepository

class NoteAddUpdateViewModel(application: Application): ViewModel() {

    // making instance for NoteRepository
    private val mNoteRepository: NoteRepository = NoteRepository(application)

    fun insert(note: Note) {
        mNoteRepository.insert(note)
    }

    fun update(note: Note) {
        mNoteRepository.update(note)
    }

    fun delete(note: Note) {
        mNoteRepository.delete(note)
    }
}