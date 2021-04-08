package com.dev7odaa.noteapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dev7odaa.noteapp.model.Note
import java.security.AccessControlContext
import java.util.concurrent.locks.Lock

@Database(entities = [Note::class], version = 1)
abstract class NotesDatabase: RoomDatabase() {

     abstract fun getNoteDao(): NoteDao

     companion object{
         @Volatile
         private var instance: NotesDatabase? = null
         private val LOCK = Any()

         operator fun invoke(context: Context) = instance?: synchronized(LOCK){
             instance?:
             creatDatabase(context).also { instance = it }
         }

         fun creatDatabase(context: Context) = Room.databaseBuilder(
             context.applicationContext,
             NotesDatabase::class.java,
             "note_db"
         ).build()
     }



}