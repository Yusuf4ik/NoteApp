package itacademy.kg.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import itacademy.kg.notesapp.fragments.Notes
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    lateinit var nav: NavigationView
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
        nav.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.main -> Toast.makeText(this, "Main", Toast.LENGTH_SHORT).show()
                R.id.about -> Toast.makeText(this, "About", Toast.LENGTH_SHORT).show()
                R.id.exit -> exitProcess(0)
            }
            true
        }

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