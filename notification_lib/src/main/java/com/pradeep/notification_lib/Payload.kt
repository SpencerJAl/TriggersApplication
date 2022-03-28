package com.pradeep.notification_lib

import android.annotation.TargetApi
import android.app.PendingIntent
import android.graphics.Bitmap
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat


sealed class Payload{

    data class Meta(

        var clickIntent: PendingIntent? = null,

        var clearIntent: PendingIntent? = null,

        var cancelOnClick: Boolean = true,

        var category: String? = null,

        var group: String? = null,

        var localOnly: Boolean = false,

        var sticky: Boolean = false,

        var timeout: Long = 0L,
        )

    data class Alerts(

        @NotificationCompat.NotificationVisibility var lockScreenVisibility: Int = NotificationCompat.VISIBILITY_PRIVATE,

        val channelKey: String = NotificationBuilder.CHANNEL_DEFAULT_KEY,

        var channelName: String = NotificationBuilder.CHANNEL_DEFAULT_NAME,

        var channelDescription: String = NotificationBuilder.CHANNEL_DEFAULT_DESCRIPTION,

        @NotificationImportance var channelImportance: Int = NotificationBuilder.IMPORTANCE_NORMAL,

        @ColorInt var lightColor: Int = NotificationBuilder.NO_LIGHTS,

        var vibrationPattern: List<Long> = ArrayList(),

        var sound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),

        @TargetApi(Build.VERSION_CODES.O)
        var showBadge: Boolean = true
    )


    data class Header(

        @DrawableRes var icon: Int = R.drawable.ic_baseline_notifications_24,

        @ColorInt var color: Int = 0x4A90E2,

        var headerText: CharSequence? = null,

        var showTimestamp: Boolean = true
    )


    class Progress constructor(


        var enablePercentage: Boolean = false,


        var progressPercent: Int = 0,


        var showProgress: Boolean = false

    )


    sealed class Content {


        interface Standard {

            var title: CharSequence?

            var text: CharSequence?
        }

        interface SupportsLargeIcon {

            var largeIcon: Bitmap?
        }


        interface Expandable {

            var expandedText: CharSequence?
        }


        data class Default(
            override var title: CharSequence? = null,
            override var text: CharSequence? = null,
            override var largeIcon: Bitmap? = null
        ) : Content(), Standard, SupportsLargeIcon


        data class TextList(
            override var title: CharSequence? = null,
            override var text: CharSequence? = null,
            override var largeIcon: Bitmap? = null,

            var lines: List<CharSequence> = ArrayList()
        ) : Content(), Standard, SupportsLargeIcon

        data class BigText(
            override var title: CharSequence? = null,
            override var text: CharSequence? = null,
            override var largeIcon: Bitmap? = null,
            override var expandedText: CharSequence? = null,

            var bigText: CharSequence? = null
        ) : Content(), Standard, SupportsLargeIcon, Expandable


        data class BigPicture(
            override var title: CharSequence? = null,
            override var text: CharSequence? = null,
            override var largeIcon: Bitmap? = null,
            override var expandedText: CharSequence? = null,

            var image: Bitmap? = null
        ) : Content(), Standard, SupportsLargeIcon, Expandable


        data class Message(
            override var largeIcon: Bitmap? = null,

            var conversationTitle: CharSequence? = null,

            var userDisplayName: CharSequence = "",

            var messages: List<NotificationCompat.MessagingStyle.Message> = ArrayList()
        ) : Content(), SupportsLargeIcon
    }
}
