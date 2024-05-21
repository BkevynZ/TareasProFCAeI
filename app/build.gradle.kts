plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.tareasprofcaei"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tareasprofcaei"
        minSdk = 21
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("com.airbnb.android:lottie:6.4.0")
    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.28")
    implementation ("com.etebarian:meow-bottom-navigation:1.2.0")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor  ("com.github.bumptech.glide:compiler:4.12.0")


}