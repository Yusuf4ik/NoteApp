package itacademy.kg.notesapp

import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import itacademy.kg.notesapp.data.Note
import itacademy.kg.notesapp.fragments.Notes
import java.text.SimpleDateFormat
import java.util.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    lateinit var nav: NavigationView
    val gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firstFragment = Notes()
        setFragment(firstFragment)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        nav = findViewById(R.id.nav)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener{
            openAddDialog()
        }
        nav.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.main -> Toast.makeText(this, "Main", Toast.LENGTH_SHORT).show()
                R.id.about -> Toast.makeText(this, "About", Toast.LENGTH_SHORT).show()
                R.id.exit -> exitProcess(0)

            }
            true
        }


    }
    private fun openAddDialog() {
        val dialog = AlertDialog.Builder(this)
                .setTitle("Add Note")
                .setMessage("Please fill in the blanks to create a new note!")
                .setIcon(R.drawable.ic_baseline_add_24)

        val view = LayoutInflater.from(this).inflate(R.layout.dialog_add, null)
        dialog.setView(view)

        val noteTitle = view.findViewById<TextInputLayout>(R.id.noteTitle_add)
        val noteDesc = view.findViewById<TextInputLayout>(R.id.noteDesc_add)
        val selectedDateView = view.findViewById<TextView>(R.id.selectedDate)
        var selectedDate = ""

        view.findViewById<Button>(R.id.selectDate_add).setOnClickListener {
            val myCalendar = Calendar.getInstance()
            val year = myCalendar.get(Calendar.YEAR)
            val month = myCalendar.get(Calendar.MONTH)
            val day = myCalendar.get(Calendar.DAY_OF_MONTH)

            this.let {
                DatePickerDialog(
                        it,
                        { _, selectedYear, selectedMonth, selectedDay ->
                            selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                            selectedDateView.text = (selectedDate)
                        },
                        year,
                        month,
                        day
                ).show()
            }
        }
        dialog.setPositiveButton("Add Note") { _, _ ->
            if (noteTitle.editText?.text.toString().isNotEmpty()
                    && noteDesc.editText?.text.toString().isNotEmpty()
                    && selectedDate != ""
            ) {
                val note = Note(
                        noteTitle.editText?.text.toString(),
                        noteDesc.editText?.text.toString(),
                        convertDate(selectedDate),
                false
                )
                val sharedPreferences =
                        getSharedPreferences(Utils.SHARED_DB_NAME, Context.MODE_PRIVATE)
                if (sharedPreferences?.getString(Utils.DATA_LIST, null) != null) {
                    val listType = object : TypeToken<MutableList<Note>>() {}.type
                    val json = sharedPreferences.getString(Utils.DATA_LIST, null)
                    val userNotes: MutableList<Note> = gson.fromJson(json, listType)
                    userNotes.add(note)
                    setSharedPreferences(userNotes)
                }
            }
            setFragment(Notes())
        }

        dialog.setNegativeButton("Cancel") { _, _ ->

        }
        dialog.show()
    }
    private fun convertDate(pickedDate: String): Date {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        return sdf.parse(pickedDate) as Date
    }
    private fun setSharedPreferences(userNotes: MutableList<Note>) {
        val sharedPreference = getSharedPreferences(Utils.SHARED_DB_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreference?.edit()
        val userNotesString = gson.toJson(userNotes)
        editor?.putString(Utils.DATA_LIST, userNotesString)
        editor?.apply()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, Notes())
            commit()
        }


    }
}