package com.example.android.dagger.splash

import android.content.Context
import android.content.Intent
import com.example.android.dagger.login.LoginActivity
import com.example.android.dagger.main.MainActivity
import com.example.android.dagger.registration.RegistrationActivity
import com.example.android.dagger.user.UserManager
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val userManager: UserManager) {

    fun getNextScreenIntent(context: Context): Intent {
        return if (!userManager.isUserLoggedIn()) {
            if (!userManager.isUserRegistered()) {
                Intent(context, RegistrationActivity::class.java)
            } else {
                Intent(context, LoginActivity::class.java)
            }
        } else {
            Intent(context, MainActivity::class.java)
        }
    }
}