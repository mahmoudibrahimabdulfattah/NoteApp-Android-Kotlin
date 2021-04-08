package com.dev7odaa.noteapp.repository

import com.dev7odaa.noteapp.db.NotesDatabase
import com.dev7odaa.noteapp.model.Note

class NoteRepository(private val db: NotesDatabase) {


    suspend fun addNote(note: Note) = db.getNoteDao().addNote(note)
    suspend fun updateNote(note: Note) = db.getNoteDao().updateNote(note)
    suspend fun deleteNote(note: Note) = db.getNoteDao().deleteNote(note)
    fun getAllNotes() = db.getNoteDao().getAllNotes()


}