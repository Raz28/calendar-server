package com.gsv28rus.calendar

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource


@SpringBootApplication
class CalendarApplication {

    @Bean
    fun getFirebase(): FirebaseAuth? {
        val serviceAccount = ClassPathResource("/firebase-adminsdk.json").inputStream

        val options = FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://sandbox-61235.firebaseio.com")
                .build()
        FirebaseApp.initializeApp(options)

        return FirebaseAuth.getInstance()
    }
//
//    @Bean
//    fun getActionCodeSettings() {
//        val actionCodeSettings = ActionCodeSettings.builder()
//                .setHandleCodeInApp(true)
//                .setAndroidPackageName("com.example.android")
//                .setAndroidInstallApp(true)
//                .setAndroidMinimumVersion("19")
//                .build()
//    }
}

fun main(args: Array<String>) {
    runApplication<CalendarApplication>(*args)
}
