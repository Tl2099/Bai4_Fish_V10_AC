plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "tl209.bai4_fish_v10_ac"
    compileSdk = 35

    defaultConfig {
        applicationId = "tl209.bai4_fish_v10_ac"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.0")
    implementation("androidx.activity:activity-ktx:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}