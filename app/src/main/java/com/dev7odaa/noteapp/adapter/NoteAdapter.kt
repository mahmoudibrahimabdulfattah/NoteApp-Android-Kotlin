package com.dev7odaa.noteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dev7odaa.noteapp.databinding.NoteLayoutAdapterBinding
import com.dev7odaa.noteapp.fragments.HomeFragmentDirections
import com.dev7odaa.noteapp.model.Note

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var binding: NoteLayoutAdapterBinding? = null

    class NoteViewHolder(itemBinding: NoteLayoutAdapterBinding): RecyclerView.ViewHolder(itemBinding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        binding = NoteLayoutAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding!!)

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = differ.currentList[position]

        holder.itemView.apply {
            binding?.tvNoteTitle?.text = currentNote.noteTitle
            binding?.tvNoteBody?.text = currentNote.noteBody
        }.setOnClickListener { mView ->

            val direction = HomeFragmentDirections
                    .actionHomeFragmentToUpdateNoteFragment(currentNote)

            mView.findNavController().navigate(direction)

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}