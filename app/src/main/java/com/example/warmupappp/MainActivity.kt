package com.example.warmupappp

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.navigation.findNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.warmupappp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Splash screen kurulumu
        installSplashScreen()

        // View binding'i başlat
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Edge-to-edge görünümü etkinleştir
        enableEdgeToEdge()

        // NavHostFragment'i bulup NavController'ı başlatma
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        // İlk açılış kontrolü
        val sharedPref = getSharedPreferences("onboarding_pref", MODE_PRIVATE)
        val isFirstLaunch = sharedPref.getBoolean("isFirstLaunch", true)

        if (isFirstLaunch) {
            navController.navigate(R.id.viewPagerFragment)

            with(sharedPref.edit()) {
                putBoolean("isFirstLaunch", false)
                apply()
            }
        } else {
            navController.navigate(R.id.listFragment)
        }

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_all -> {
                    navController.navigate(R.id.listFragment)
                    true
                }
                R.id.navigation_favorites -> {
                    navController.navigate(R.id.favFragment)
                    true
                }
                else -> false
            }
        }

        // BottomNavigationView'i fragmentlara göre gizle/göster
        hideNavBars()
    }

    private fun hideNavBars() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.viewPagerFragment -> {
                    binding.bottomNavigationView.visibility=View.GONE
                }
                else -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }
    }
}
