import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementationAppLibraries(){
    implementation(listOf(
        "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}",
        "androidx.core:core-ktx:${Versions.coreKtx}",
        "androidx.appcompat:appcompat:${Versions.appcompat}",
        "com.google.android.material:material:${Versions.material}",
        "androidx.legacy:legacy-support-v4:${Versions.legacy}",
    ))
}

fun DependencyHandler.implementationCompose(){
    implementation(listOf(
        "androidx.compose.ui:ui:${Versions.compose}",
        "androidx.compose.ui:ui-tooling:${Versions.compose}",
        "androidx.compose.material:material:${Versions.compose}",
        "androidx.activity:activity-compose:${Versions.activityCompose}",
        "androidx.navigation:navigation-compose:${Versions.navigation}",
        "androidx.constraintlayout:constraintlayout-compose:${Versions.compose}",
        "androidx.compose.material:material-icons-extended:${Versions.compose}",
        "com.google.accompanist:accompanist-pager:0.23.1",
        "com.airbnb.android:lottie-compose:${Versions.lottie}"
    ))
}

fun DependencyHandler.implementationKtor(){
    implementation(listOf(
        "io.ktor:ktor-client-android:${Versions.ktor}",
        "io.ktor:ktor-client-serialization:${Versions.ktor}",
        "io.ktor:ktor-client-logging-jvm:${Versions.ktor}",
        "io.ktor:ktor-client-content-negotiation:${Versions.ktor}",
        "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"

    ))
}

fun DependencyHandler.implementationSerialization(){
    implementation(listOf(
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}"
    ))
}

fun DependencyHandler.implementationRoom(){
    implementation(listOf(
        "androidx.room:room-runtime:${Versions.room}",
        "androidx.room:room-ktx:${Versions.room}"
    ))
    annotationProcessor(listOf(
        "androidx.room:room-compiler:${Versions.room}"
    ))
    kapt(listOf(
        "androidx.room:room-compiler:${Versions.room}"
    ))
}

fun DependencyHandler.implementationKoin(){
    implementation(listOf(
        "io.insert-koin:koin-core:${Versions.koin}",
        "io.insert-koin:koin-android:${Versions.koin}",
        "io.insert-koin:koin-androidx-compose:${Versions.koin}",
        "io.insert-koin:koin-androidx-navigation:${Versions.koin}"

    ))
}

fun DependencyHandler.implementationHilt(){
    implementation(listOf(
        "com.google.dagger:hilt-android:${Versions.hilt}",
        "androidx.hilt:hilt-navigation-compose:1.0.0"
    ))
    kapt(listOf(
        "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    ))
}

fun DependencyHandler.implementationGlide(){
    implementation(listOf(
        "com.github.bumptech.glide:glide:${Versions.glide}"
    ))
    annotationProcessor(listOf(
        "com.github.bumptech.glide:compiler:${Versions.glide}",
    ))
}

fun DependencyHandler.implementationCoil(){
    implementation(listOf(
        "io.coil-kt:coil-compose:${Versions.coil}"
    ))
}

fun DependencyHandler.implementationFirebase(){
    implementation(listOf(
        "com.google.firebase:firebase-bom:30.2.0"
    ))
}

fun DependencyHandler.implementationAndroidTestLibraries(){
    androidTestImplementation(listOf(
        "androidx.test.ext:junit:${Versions.extJunit}",
        "androidx.test.espresso:espresso-core:${Versions.espresso}"
    ))
}

fun DependencyHandler.implementationTestLibraries(){
    testImplementation(listOf(
        "junit:junit:${Versions.junit}"
    ))
}
