package com.kabos.firebasesample.viewmodel

import androidx.lifecycle.ViewModel
import com.kabos.firebasesample.model.MemoItem
import com.kabos.firebasesample.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MyViewModel @Inject constructor(private val repository: MyRepository): ViewModel() {

    fun addMemo(memoItem: MemoItem){
        repository.addMemo(memoItem)
    }
}
