package com.kabos.firebasesample.repository

import com.kabos.firebasesample.model.Constants.Companion.CONTENT_TYPE
import com.kabos.firebasesample.model.Constants.Companion.SERVER_KEY
import com.kabos.firebasesample.model.PushNotification
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationAPI {
    @Headers("Authorization: key=$SERVER_KEY", "Content-Type:$CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postNotification(
        @Body notification: PushNotification
    ): Response<ResponseBody>
}
