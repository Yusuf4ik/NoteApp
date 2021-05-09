package itacademy.kg.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import itacademy.kg.notesapp.fragments.Notes

class MainActivity : AppCompatActivity() {
    lateinit var titleDescription : TextView
    lateinit var descriptionDescription : TextView
    lateinit var dateDate : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firstFragment = Notes()
        setFragment(firstFragment)
    }
    private fun setFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, Notes())
            commit()
        }


    }
}