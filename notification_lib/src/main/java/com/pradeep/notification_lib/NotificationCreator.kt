package com.pradeep.notification_lib

import android.annotation.TargetApi
import android.os.Build
import androidx.core.app.NotificationCompat


@NotifyScopeMarker
class NotificationCreator internal constructor(private val notify: NotificationBuilder) {

    private var meta = Payload.Meta()
    private var alerts = NotificationBuilder.defaultConfig.defaultAlerting
    private var header = NotificationBuilder.defaultConfig.defaultHeader.copy()
    private var content: Payload.Content = Payload.Content.Default()
    private var actions: ArrayList<Action>? = null
    private var progress: Payload.Progress = Payload.Progress()


    fun meta(init: Payload.Meta.() -> Unit): NotificationCreator {
        this.meta.init()

        return this
    }


    fun alerting(key: String, init: Payload.Alerts.() -> Unit): NotificationCreator {
        // Clone object and assign the key.
        this.alerts = this.alerts.copy(channelKey = key).also(init)
        return this
    }


    fun header(init: Payload.Header.() -> Unit): NotificationCreator {
        this.header.init()
        return this
    }

    fun progress(init: Payload.Progress.() -> Unit): NotificationCreator {
        this.progress.init()
        return this
    }


    fun content(init: Payload.Content.Default.() -> Unit): NotificationCreator {
        this.content = Payload.Content.Default().also(init)
        return this
    }

    fun asTextList(init: Payload.Content.TextList.() -> Unit): NotificationCreator {
        this.content = Payload.Content.TextList().also(init)
        return this
    }


    fun asBigText(init: Payload.Content.BigText.() -> Unit): NotificationCreator {
        this.content = Payload.Content.BigText().also(init)
        return this
    }

    fun asBigPicture(init: Payload.Content.BigPicture.() -> Unit): NotificationCreator {
        this.content = Payload.Content.BigPicture().also(init)
        return this
    }

    fun asMessage(init: Payload.Content.Message.() -> Unit): NotificationCreator {
        this.content = Payload.Content.Message().also(init)
        return this
    }

    fun actions(init: ArrayList<Action>.() -> Unit): NotificationCreator {
        this.actions = ArrayList<Action>().also(init)
        return this
    }

    fun asBuilder(): NotificationCompat.Builder {
        return notify.asBuilder(RawNotification(meta, alerts, header, content, actions, progress))
    }


    @Deprecated(message = "Removed optional argument to alleviate confusion on ID that is used to create notification",
        replaceWith = ReplaceWith(
            "Notify.show()",
            "io.karn.notify.Notify"))
    fun show(id: Int?): Int {
        return notify.show(id, asBuilder())
    }

    fun show(): Int {
        return notify.show(null, asBuilder())
    }

    fun setWork() {
        notify.setWork()
    }


    @Deprecated(message = "Exposes function under the incorrect API -- NotifyCreator is reserved strictly for notification construction.",
        replaceWith = ReplaceWith(
            "Notify.cancelNotification(context, id)",
            "android.content.Context", "io.karn.notify.Notify"))
    @Throws(NullPointerException::class)
    fun cancel(id: Int) {
        // This should be safe to call from here because the Notify.with(context) function call
        // would have initialized the NotificationManager object. In any case, the function has been
        // annotated as one which can throw a NullPointerException.
        return NotificationBuilder.cancelNotification(id)
    }
}