plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt") // Додано плагін kapt
    id("com.google.gms.google-services") // Google Services Plugin
    id("com.google.firebase.crashlytics") // Firebase Crashlytics Plugin
}

android {
    namespace = "com.example.medicalapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.medicalapp"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.room:room-runtime:2.5.2")
    kapt("androidx.room:room-compiler:2.5.2") // Залежність для Room через kapt
    implementation("com.google.firebase:firebase-crashlytics:19.2.1")
    implementation("com.google.firebase:firebase-analytics:21.2.2") // Додано для роботи Firebase
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("io.insert-koin:koin-android:3.4.0") // Koin для Android
    implementation("io.insert-koin:koin-core:3.4.0") // Основний модуль Koin
}
