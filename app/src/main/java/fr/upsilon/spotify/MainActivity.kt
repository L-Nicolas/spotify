package fr.upsilon.spotify

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.upsilon.spotify.view.RankFragment
import fr.upsilon.spotify.view.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var navBar: BottomNavigationView
    private lateinit var hostView: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        navBar = findViewById(R.id.nav_bar)

        loadFragment(RankFragment())

        val navigationView = findViewById<BottomNavigationView>(R.id.nav_bar)
        navigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.tab_rank -> {
                    loadFragment(RankFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.tab_search -> {
                    loadFragment(SearchFragment())
                    return@setOnItemSelectedListener true
                }
                /*R.id.tab_favorites -> {
                    loadFragment(LiveFragment())
                    return@setOnItemSelectedListener true
                }*/
                else -> false
            }
        }
        //hostView = findViewById(R.id.host_view)

        /*val navHost = supportFragmentManager.findFragmentById(R.id.host_view) as NavHostFragment
        setupWithNavController(navBar, navHost.navController)*/
    }

    @SuppressLint("ResourceType")
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.setReorderingAllowed(true)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}