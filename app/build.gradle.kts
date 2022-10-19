import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

val file = rootProject.file("secrets.properties")
val properties = Properties()
properties.load(FileInputStream(file))

android {
    compileSdkVersion = AppConfig.compileSdk
    buildToolsVersion = AppConfig.buildToolsVersion

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation

        buildConfigField("String", "BASE_URL", properties["BASE_URL"] as String)
        resValue("string", "AD_APP_ID", properties["AD_APP_ID"] as String)
        buildConfigField("String", "INTERSTITIAL_AD_UNIT_ID", properties["INTERSTITIAL_AD_UNIT_ID"] as String)
        resValue("string", "BANNER_AD_UNIT_ID", properties["BANNER_AD_UNIT_ID"] as String)
        buildConfigField("String", "APP_OPEN_AD_UNIT_ID", properties["APP_OPEN_AD_UNIT_ID"] as String)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }

    flavorDimensions.add(AppConfig.dimension)
    productFlavors {
        create("staging") {
            applicationIdSuffix = ".staging"
            dimension = AppConfig.dimension
        }

        create("production") {
            dimension = AppConfig.dimension
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }

    packagingOptions {
        resources.excludes.add("META-INF/notice.txt")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("com.google.firebase:firebase-database-ktx:20.0.5")
    implementation("com.google.firebase:firebase-firestore:24.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.2")

    implementation (platform("com.google.firebase:firebase-bom:30.4.0"))
    implementation("com.google.firebase:firebase-config-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx:23.0.8")
    implementation("com.google.firebase:firebase-crashlytics-ndk:18.2.13")
    implementation("com.google.android.play:app-update:2.0.0")
    implementation("com.google.android.play:app-update-ktx:2.0.0")
    implementation("com.google.firebase:firebase-crashlytics:18.2.11")
    implementation("com.google.firebase:firebase-analytics:21.1.0")
    implementation("com.google.firebase:firebase-crashlytics-ktx:18.2.11")
    implementation("com.google.android.gms:play-services-ads:21.2.0")

    implementation("com.github.1902shubh:AdmobOpenAds:1.0.1")
    implementation("com.google.android.play:review-ktx:2.0.0")

    implementationAppLibraries()
    implementationTestLibraries()
    implementationAndroidTestLibraries()
    implementationCompose()
    implementationKtor()
    implementationKoin()
    implementationHilt()
    implementationRoom()
    implementationCoil()
    implementationSerialization()
}