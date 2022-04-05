package com.pradeep.notification_lib

import android.content.Context
import androidx.work.ListenableWorker.Result.success
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotifyWork(val context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {

        NotificationBuilder.with(context).asBigText {
            title = "12nd milestone"
            text = "10k... steps completed"
            bigText = "You have completed milestone..."
            expandedText = ""
        }.show()

        return success()
    }
}