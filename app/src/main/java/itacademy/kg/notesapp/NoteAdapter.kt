package itacademy.kg.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    private lateinit var goodArrayList: ArrayList<Note>
    private lateinit var listener: NoteOnClickListener
    fun setNoteItemClickListener(listener: NoteOnClickListener) {
        this.listener = listener
    }


    class NoteHolder(itemView: View, listener: NoteOnClickListener) : RecyclerView.ViewHolder(@NonNull itemView : View) {

        init {

            tick.setOnClickListener {
                val position = adapterPosition
                listener.onDoneNote(position)
                if (!isRead) {
                    isRead = true
                    tick.setImageResource(R.drawable.ic_baseline_check_box_24)
                } else {
                    isRead = false
                    tick.setImageResource(R.drawable.ic_baseline_check_box_outline_blank_24)

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}

fun NoteAdapterr(notes: ArrayList<Note>) {
    this.goodArrayList = notes
}

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
    val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
    return NoteHolder(view, listener)
}

override fun onBindViewHolder(holder: NoteHolder, position: Int) {
    val currentItem: Note = goodArrayList!![position]
    holder.nameOfNote.text = currentItem.nameOfNote
    holder.dateOfNote.text = currentItem.date.toString()
    holder.description.text = currentItem.descriptions
}


override fun getItemCount(): Int {
    return goodArrayList!!.size
}


}
