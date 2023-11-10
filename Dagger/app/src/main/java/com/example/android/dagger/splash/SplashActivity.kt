package com.example.android.dagger.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.dagger.MyApplication
import com.example.android.dagger.R
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.timerTask

const val SPLASH_DELAY_MILLIS = 2000L

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Timer().schedule(timerTask {
            startActivity(splashViewModel.getNextScreenIntent(this@SplashActivity))
            finish()
        }, SPLASH_DELAY_MILLIS)
    }
}