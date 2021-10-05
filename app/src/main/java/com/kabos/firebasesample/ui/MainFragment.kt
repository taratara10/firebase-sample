package com.kabos.firebasesample.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import com.kabos.firebasesample.MemoAdapter
import com.kabos.firebasesample.databinding.FragmentMainBinding
import com.kabos.firebasesample.model.Constants.Companion.TOPIC
import com.kabos.firebasesample.model.NotificationData
import com.kabos.firebasesample.model.PushNotification
import com.kabos.firebasesample.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainFragment: Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MyViewModel by activityViewModels()
    private val memoAdapter by lazy { MemoAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            rvMain.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = memoAdapter
                addItemDecoration(DividerItemDecoration(activity,LinearLayoutManager(activity).orientation))
            }

            btnAddMemo.setOnClickListener {
//                findNavController().navigate(R.id.action_nav_main_to_nav_dialog)
                getDeviceToken()
//                subscribeToTopic()
                sendMessage()
            }


            //購読開始 FireStoreが更新されると自動で流れてくる
            viewModel.listenToCollection()

            viewModel.memoList.observe(viewLifecycleOwner,{list ->
                memoAdapter.submitList(list)
            })
        }


    }

    // this emulator token is
    // d20AbIBTSCy95lWp3rAzWf:APA91bEKu55mrwILs3ARyYXWJvRpHXA8d0KsE5-TJvb4OyB5VGc1yrl34Ia1QGVJTSvQIKGRWjmWP3f5clQln1IZca7mYsERrTVI5qIsTkHOPlcIvqjwg1V2TBq1dnWLkOZt6sTf9Mc_
    fun getDeviceToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            Log.d(TAG, token)
            Toast.makeText(activity, token, Toast.LENGTH_SHORT).show()
        })
    }

    fun subscribeToTopic(){
        FirebaseMessaging.getInstance().subscribeToTopic("weather")
            .addOnCompleteListener { task ->
                var msg = "Success subscribe"
                if (!task.isSuccessful) {
                    msg = "Failed to subscribe to weather topic"
                }
                Log.d(TAG, msg)
                Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
            }
    }

    fun sendMessage(){
        PushNotification(
            NotificationData("test","message sample"),
            TOPIC
        ).also {
            viewModel.sendNotification(it)
        }
    }

}
