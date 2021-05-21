package itacademy.kg.notesapp.fragments

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import itacademy.kg.notesapp.R
import itacademy.kg.notesapp.Utils
import itacademy.kg.notesapp.data.Note
import java.text.SimpleDateFormat
import java.util.*
import java.sql.Date as Date


class DescriptionsFragment : Fragment() {

    lateinit var descriptionsDesc: TextView
    lateinit var descriptionTitle: TextView
    lateinit var descData: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_descriptions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        descData = view.findViewById(R.id.date)
        descriptionsDesc = view.findViewById(R.id.descriptionDescription)
        descriptionTitle = view.findViewById(R.id.descriptionTitle)

        val bundle = this.arguments
        val memo = bundle?.getSerializable(Utils.KEY) as Note
        val nameOfNote = memo.nameOfNote
        val descOfNote = memo.descriptions

//      val dataOfNote = memo.date.toString()
        val selectedDate = "${memo.date.day}/${memo.date.month + 1}/${memo.date.year}"
        descriptionTitle.text = nameOfNote
        descriptionsDesc.text = descOfNote

        descData.text = selectedDate

    }
}






