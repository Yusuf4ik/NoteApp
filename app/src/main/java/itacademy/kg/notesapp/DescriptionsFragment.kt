package itacademy.kg.notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import itacademy.kg.notesapp.data.Note
import org.w3c.dom.Text


class DescriptionsFragment : Fragment() {

    lateinit var descriptionsDesc : TextView
    lateinit var descriptionTitle : TextView
    lateinit var descData : TextView

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
      val dataOfNote = memo.date.toString()
        descriptionTitle.text = nameOfNote
        descriptionsDesc.text = descOfNote
       descData.text = dataOfNote

    }
}


