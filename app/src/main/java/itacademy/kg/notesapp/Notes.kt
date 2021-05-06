package itacademy.kg.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


 class Notes : Fragment() {
  lateinit var adapter: NoteAdapter
    private lateinit var recyclerView : RecyclerView
     private lateinit var notes: ArrayList<Note>




     override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }
     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)
         //fulling ArrayList
         notes = ArrayList<Note>()
         notes.add(Note("header1", "desription1", 0))
         notes.add(Note("header2", "desription2", 0))
         notes.add(Note("header3", "desription3", 0))
         //Building RecyclerView
         recyclerView = view.findViewById(R.id.recyclerView)
         recyclerView.layoutManager = LinearLayoutManager(context)
         val tick: ImageButton? = view.findViewById(R.id.imageButton)

         var isRead = false
         recyclerView.setHasFixedSize(true)
         adapter = NoteAdapter.NoteHolder
         recyclerView.adapter = adapter
         adapter.setNoteItemClickListener(object : NoteOnClickListener {
             override fun onDoneNote(position: Int) {

                 if(!isRead) {
                     isRead = true
                     tick?.setImageResource(R.drawable.ic_baseline_check_box_24)
                 }else{
                     isRead = false
                     tick?.setImageResource(R.drawable.ic_baseline_check_box_outline_blank_24)

                 }
                 adapter.notifyItemChanged(position)
             }
         })
     }


}