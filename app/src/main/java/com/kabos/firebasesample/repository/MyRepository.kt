package com.kabos.firebasesample.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase
import com.kabos.firebasesample.model.MemoItem

class MyRepository() {
    private lateinit var fireStore: FirebaseFirestore
    init {
        setup()
    }

    private fun setup() {
        // initialize firestore instance
        fireStore = Firebase.firestore

        // [START set_firestore_settings]
        val settings = firestoreSettings {
            isPersistenceEnabled = true
        }
        fireStore.firestoreSettings = settings
    }


    fun addMemo(memo: MemoItem){
        fireStore.collection("room1").document("doc1")
            .set(memo)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }
}
