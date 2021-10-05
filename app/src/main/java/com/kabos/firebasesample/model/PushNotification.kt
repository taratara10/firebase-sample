package com.kabos.firebasesample.model

data class PushNotification(
    val data: NotificationData,
    val to: String
)

data class NotificationData(
    val title: String,
    val message: String
)
