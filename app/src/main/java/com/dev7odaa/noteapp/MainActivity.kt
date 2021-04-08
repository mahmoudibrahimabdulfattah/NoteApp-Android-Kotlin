package com.dev7odaa.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.dev7odaa.noteapp.databinding.ActivityMainBinding
import com.dev7odaa.noteapp.db.NotesDatabase
import com.dev7odaa.noteapp.repository.NoteRepository
import com.dev7odaa.noteapp.viewModel.NoteViewModel
import com.dev7odaa.noteapp.viewModel.NoteViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setUpViewModel()
    }


    private fun setUpViewModel(){
        val noteRepository = NoteRepository(NotesDatabase(this))

        val viewModelProviderFactory = NoteViewModelProviderFactory(application, noteRepository)

        noteViewModel = ViewModelProvider(this, viewModelProviderFactory).get(NoteViewModel::class.java)
    }
}