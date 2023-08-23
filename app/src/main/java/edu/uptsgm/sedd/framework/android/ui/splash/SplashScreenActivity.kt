package edu.uptsgm.sedd.framework.android.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import edu.uptsgm.sedd.databinding.ActivitySplashScreenBinding
import edu.uptsgm.sedd.framework.android.ui.login.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity  : AppCompatActivity() {

    private val splashMaxLifeTime = 6000L

    private lateinit var binding: ActivitySplashScreenBinding

    private var splashLifetimeJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        supportActionBar?.hide()

        setupSplashLifeTime()
    }

    override fun onResume() {
        super.onResume()
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private fun setupSplashLifeTime() {
        splashLifetimeJob = lifecycleScope.launch(Dispatchers.IO) {
            delay(splashMaxLifeTime)
            Intent(this@SplashScreenActivity, LoginActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }
    }

}