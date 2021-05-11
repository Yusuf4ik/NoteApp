package itacademy.kg.notesapp.fragments

import android.R.attr
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import itacademy.kg.notesapp.DescriptionsFragment
import itacademy.kg.notesapp.R
import itacademy.kg.notesapp.Utils
import itacademy.kg.notesapp.adapters.MemoAdapter
import itacademy.kg.notesapp.adapters.MemoAdapter.NoteOnClickListener
import itacademy.kg.notesapp.data.Note
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates


class Notes : Fragment(), NoteOnClickListener  {
    private lateinit var adapter: MemoAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var notes: MutableList<Note>
    private var isRead : Boolean = false
    private lateinit var listener: NoteOnClickListener


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


        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val selectedDate  = "$day/${month + 1}/$year"
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val date = sdf.parse(selectedDate) // String -> Date

        notes.add(Note("header1", "desription1", date, false))
        notes.add(Note("header2", "desription2", date, false))
        notes.add(Note("header3", "desription3", date, false))
        notes.add(Note("header4", "desription4", date, false))

        notes.add(Note("header5", "desription5", date, false))
        notes.add(Note("header6", "desription6", date, false))


        //Building RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        adapter = MemoAdapter(notes, this)
        recyclerView.adapter = adapter


    }

    override fun onCardClick(position: Int) {
        val bundle = Bundle()
        bundle.putSerializable(Utils.KEY, notes[position])
        val fragment = DescriptionsFragment()
        fragment.arguments = bundle
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, fragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onDoneNote(position: Int) {


        if(notes[position].readen){
            view?.findViewById<ImageView>(R.id.imageButton)?.setImageResource(R.drawable.ic_baseline_check_box_outline_blank_24)
        }else if(!notes[position].readen){
            view?.findViewById<ImageView>(R.id.imageButton)?.setImageResource(R.drawable.ic_baseline_check_box_24)
            notes.removeAt(position)

        }

    }

    override fun onEdit(position: Int) {
    }



}





