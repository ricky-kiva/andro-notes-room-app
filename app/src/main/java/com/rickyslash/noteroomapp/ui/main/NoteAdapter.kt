package com.rickyslash.noteroomapp.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rickyslash.noteroomapp.database.Note
import com.rickyslash.noteroomapp.databinding.ItemNoteBinding
import com.rickyslash.noteroomapp.helper.NoteDiffCallback
import com.rickyslash.noteroomapp.ui.insert.NoteAddUpdateActivity

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    // making listNotes
    private val listNotes = ArrayList<Note>()

    fun setListNotes(listNotes: List<Note>) {
        // instantiating NoteDiffCallback()
        val diffCallback = NoteDiffCallback(this.listNotes, listNotes)
        // this return 'DiffResult' that contains information about items that has been added, removed, or updated between old & new list
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        // clearing old listNotes then adds all listNotes
        this.listNotes.clear()
        this.listNotes.addAll(listNotes)
        // dispatching updates to the adapter
        diffResult.dispatchUpdatesTo(this)
    }

    inner class NoteViewHolder(private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            with(binding) {
                tvItemTitle.text = note.title
                tvItemDate.text = note.date
                tvItemDescription.text = note.desc
                cvItemNote.setOnClickListener {
                    val intent = Intent(it.context, NoteAddUpdateActivity::class.java)
                    intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
                    it.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    override fun getItemCount(): Int = listNotes.size
}