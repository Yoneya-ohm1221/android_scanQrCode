package com.example.qrcode
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, HomeFragment())
        transaction.commit()
        navView.setOnNavigationItemSelectedListener {
            var fm: Fragment = HomeFragment()
            when (it.itemId) {
                R.id.nav_createqr -> fm = HomeFragment()
                R.id.nav_scanqr -> fm = ScanqrFragment()
            }
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment, fm)
            transaction.commit()
            return@setOnNavigationItemSelectedListener true
        }
    }
}