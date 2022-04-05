package com.pradeep.notification_lib

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class NotificationBuilder internal constructor(internal var context: Context){


    companion object {

        const val CHANNEL_DEFAULT_KEY = "application_notification"

        const val CHANNEL_DEFAULT_NAME = "Application notifications."

        const val CHANNEL_DEFAULT_DESCRIPTION = "General application notifications."

        const val IMPORTANCE_MIN = NotificationCompat.PRIORITY_MIN

        const val IMPORTANCE_LOW = NotificationCompat.PRIORITY_LOW

        const val IMPORTANCE_NORMAL = NotificationCompat.PRIORITY_DEFAULT

        const val IMPORTANCE_HIGH = NotificationCompat.PRIORITY_HIGH

        const val IMPORTANCE_MAX = NotificationCompat.PRIORITY_MAX

        const val NO_LIGHTS = 0

        // This is the initial configuration of the Notify NotifyCreator.
        internal var defaultConfig = NotifyConfig()

        fun defaultConfig(init: NotifyConfig.() -> Unit) {
            defaultConfig.init()
        }

        fun with(context: Context): NotificationCreator {
            return NotificationCreator(NotificationBuilder(context))
        }


        @Deprecated(message = "NotificationManager might not have been initialized and can throw a NullPointerException -- provide a context.",
            replaceWith = ReplaceWith("Notify.cancelNotification(context, id)"))
        @Throws(NullPointerException::class)
        fun cancelNotification(id: Int) {
            return NotificationInterop.cancelNotification(defaultConfig.notificationManager!!, id)
        }


        fun cancelNotification(context: Context, id: Int) {
            if (defaultConfig.notificationManager == null) {
                defaultConfig.notificationManager = context.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            }

            return NotificationInterop.cancelNotification(defaultConfig.notificationManager!!, id)
        }
    }

    init {
        this.context = context.applicationContext

        // Initialize notification manager instance.
        if (defaultConfig.notificationManager == null) {
            defaultConfig.notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }

        NotificationChannelInterop.with(defaultConfig.defaultAlerting)
    }

    internal fun asBuilder(payload: RawNotification): NotificationCompat.Builder {
        return NotificationInterop.buildNotification(this, payload)
    }


    internal fun show(id: Int?, builder: NotificationCompat.Builder): Int {
        return NotificationInterop.showNotification(defaultConfig.notificationManager!!, id, builder)
    }

    internal fun setWork() {
        //1st notification
        val data = Data.Builder().putInt("appName_notification_id_1", 0).build()
        val delay = 5L
        scheduleNotification(delay, data)

        val data1 = Data.Builder().putInt("appName_notification_id_2", 0).build()
        val delay1 = 40L
        scheduleNotification(delay1, data1)
    }

    private fun scheduleNotification(delay: Long, data: Data) {
        val notificationWork = OneTimeWorkRequest.Builder(NotifyWork::class.java)
            .setInitialDelay(delay, TimeUnit.SECONDS).setInputData(data).build()
        val instanceWorkManager = WorkManager.getInstance(context)
        instanceWorkManager.beginUniqueWork("appName_notification_work_$delay", ExistingWorkPolicy.REPLACE, notificationWork)
            .enqueue()
    }
}