package com.example.medicalapp

import android.app.Application
import com.example.medicalapp.basic.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MedicalApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Ініціалізація Koin з контекстом Android
        startKoin {
            androidContext(this@MedicalApp)  // Передаємо Android Context у Koin
            modules(appModule)
        }
    }
}

