package com.example.medicalapp.basic.koin

import androidx.room.Room
import com.example.medicalapp.viewmodel.MainViewModel
import com.example.medicalapp.data.room.AppDatabase
import com.example.medicalapp.data.room.MedicalCardRepository
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

// Оголошуємо модулі для Koin
val appModule = module {

    // Оголошуємо базу даних
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "medical_db")
            .fallbackToDestructiveMigration()  // Автоматична міграція з видаленням даних
            .build()
    }

    // Оголошуємо DAO через базу даних
    single { get<AppDatabase>().medicalCardDAO() }

    // Оголошуємо репозиторій
    single { MedicalCardRepository(get()) }

    // Оголошуємо ViewModel через Koin
    viewModel { MainViewModel(get()) }
}
