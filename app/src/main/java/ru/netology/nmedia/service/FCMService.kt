package ru.netology.nmedia.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson

class FCMService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.i("fcm_token", token)
        //  println(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        Log.i("fcm_message", message.notification?.body.toString()) //Logcat tag: System.out: token
         //println(Gson().toJson(message))               // Logcat tag: System.out
         //Log.d("My_Firebase", Gson().toJson(message))  // Logcat tag: My_Firebase
    }
}