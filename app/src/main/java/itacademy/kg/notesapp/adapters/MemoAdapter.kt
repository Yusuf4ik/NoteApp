package itacademy.kg.notesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import itacademy.kg.notesapp.DescriptionsFragment
import itacademy.kg.notesapp.R
import itacademy.kg.notesapp.data.Note

class MemoAdapter(
        var notes : List<Note>,
        private var listener: NoteOnClickListener


) : RecyclerView.Adapter<MemoAdapter.Holder>() {
    fun setNoteItemClickListener(listener:NoteOnClickListener) {
        this.listener = listener
    }


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val title = itemView.findViewById<TextView>(R.id.nameOfNote)
        val body = itemView.findViewById<TextView>(R.id.descriptions)
        val image = itemView.findViewById<ImageView>(R.id.imageButton)
        val edit = itemView.findViewById<ImageView>(R.id.imageButton2)
        val parent = itemView.findViewById<CardView>(R.id.parent)
        init {
            parent.setOnClickListener(this)
            image.setOnClickListener(this)
            edit.setOnClickListener(this)

        }
        override fun onClick(v: View?) {
            when(v?.id){
                R.id.parent -> listener.onCardClick(adapterPosition)
                R.id.imageButton -> listener.onDoneNote(adapterPosition)
                R.id.imageButton2 -> listener.onEdit(adapterPosition)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int){
        val currentNote: Note = notes[position]
        holder.title.text = currentNote.nameOfNote
        holder.body.text = currentNote.descriptions





    }



    override fun getItemCount(): Int {
        return notes.size
    }


    interface NoteOnClickListener{
        fun onCardClick(position: Int)
        fun onDoneNote(position: Int)
        fun onEdit(position: Int)

    }

}