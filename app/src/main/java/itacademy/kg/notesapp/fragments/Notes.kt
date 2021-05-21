package itacademy.kg.notesapp.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import itacademy.kg.notesapp.R
import itacademy.kg.notesapp.Utils
import itacademy.kg.notesapp.adapters.MemoAdapter
import itacademy.kg.notesapp.adapters.MemoAdapter.NoteOnClickListener
import itacademy.kg.notesapp.data.Note
import java.text.SimpleDateFormat
import java.util.*


class Notes : Fragment(), NoteOnClickListener  {
    private lateinit var adapter: MemoAdapter
    private lateinit var recyclerView: RecyclerView
    private var notes = mutableListOf<Note>()
    private var isRead : Boolean = false
    private lateinit var listener: NoteOnClickListener
    private val gson = Gson()

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





        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)

        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val selectedDate  = "$day/${month + 1}/$year"
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val date = sdf.parse(selectedDate) // String -> Date

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        adapter = context?.let { MemoAdapter(it, notes, this) }!!
        recyclerView.adapter = adapter
//        registerForContextMenu(recyclerView)
        notes.clear()
        updateList()
        setSharedPreferences(notes)


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

    private fun updateList(){
        val preferences = context?.getSharedPreferences(Utils.SHARED_DB_NAME, Context.MODE_PRIVATE)
        if (preferences?.getString(Utils.DATA_LIST, null) != null) {
            notes.addAll(
                    gson.fromJson(
                            preferences.getString(Utils.DATA_LIST, null),
                            Array<Note>::class.java
                    )
            )
            Log.i("MyData", notes.size.toString())
        }
    }

    private fun setSharedPreferences(userNotes: MutableList<Note>) {
        val sharedPreference =
                context?.getSharedPreferences(Utils.SHARED_DB_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreference?.edit()
        val userNotesString = gson.toJson(userNotes)
        editor?.putString(Utils.DATA_LIST, userNotesString)
        editor?.apply()
    }

    override fun onDoneNote(position: Int) {

        Toast.makeText(context, "Delete click", Toast.LENGTH_SHORT).show()
        notes.remove(notes[position])
        setSharedPreferences(notes)
        adapter.notifyDataSetChanged()
        view?.let { checkListSize(it) }


    }

    override fun onEdit(position: Int) {
        val bundle = Bundle()
        bundle.putSerializable(Utils.KEY, notes[position])

        val fragment = EditFragment()
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
                .replace(R.id.fragment, fragment)
                .addToBackStack(null)
                .commit()
    }
    private fun checkListSize(view: View) {
        if (notes.size == 0) {
//            view.findViewById<TextView>(R.id.message).visibility = View.VISIBLE
        } else {
//            view.findViewById<TextView>(R.id.message).visibility = View.GONE
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        setSharedPreferences(notes)
    }




}





