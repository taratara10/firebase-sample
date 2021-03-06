package com.kabos.firebasesample.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kabos.firebasesample.model.MemoItem
import com.kabos.firebasesample.model.PushNotification
import com.kabos.firebasesample.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: MyRepository): ViewModel() {

    val memoList = MutableLiveData<List<MemoItem>>()



    val callback = object : Callback{
        override fun onSuccess(memos: List<MemoItem>) {
           memoList.postValue(memos)
        }
    }

    fun addMemo(memoItem: MemoItem){
        repository.addMemo(memoItem)
    }

    fun listenToCollection() {
        repository.listenToCollection(callback)
    }

    fun sendNotification(notification: PushNotification){
        repository.sendNotification(notification)
    }
}

interface Callback {
    fun onSuccess(memos: List<MemoItem>)
}
