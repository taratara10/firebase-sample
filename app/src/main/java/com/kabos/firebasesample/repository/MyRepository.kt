package com.kabos.firebasesample.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase
import com.kabos.firebasesample.model.MemoItem
import com.kabos.firebasesample.viewmodel.Callback

class MyRepository() {
    private lateinit var fireStore: FirebaseFirestore
    init {
        setup()
    }

    private fun setup() {
        // initialize fireStore instance
        fireStore = Firebase.firestore
        val settings = firestoreSettings {
            isPersistenceEnabled = true
        }
        fireStore.firestoreSettings = settings
    }


    fun addMemo(memo: MemoItem){
        fireStore.collection("room1")
            .add(memo)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }

    // 実際はgetMemo()の返り値でList<MemoItem>が欲しいけど、
    // addOnSuccessListenerの挙動がよく分からないので、一旦callbackで実装
    fun getMemo(callback: Callback) {
        fireStore.collection("room1")
            .get()
            .addOnSuccessListener { result ->
                val memos = mutableListOf<MemoItem>()
                for (document in result) {
                    val memo = document.toObject(MemoItem::class.java)
                    memos.add(memo)

                    Log.d(TAG, "${document.id} => ${document.data} ")
                    //doc1 => {title=sample2, userId=taro}のkey-Valueで返ってくる
                }
                callback.onSuccess(memos)
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
    }

    fun listenToCollection(callback: Callback){
        fireStore.collection("room1")
        .addSnapshotListener { value, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            val memos = mutableListOf<MemoItem>()
            for (document in value!!) {
                val memo = document.toObject(MemoItem::class.java)
                memos.add(memo)
            }
            Log.d(TAG, "listener is called")
            callback.onSuccess(memos)
        }
    }


}
