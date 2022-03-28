package com.pradeep.notification_lib


import android.app.NotificationManager

data class NotifyConfig(

    internal var notificationManager: NotificationManager? = null,

    internal var defaultHeader: Payload.Header = Payload.Header(),

    internal var defaultProgress: Payload.Progress = Payload.Progress(),

    internal var defaultAlerting: Payload.Alerts = Payload.Alerts()
) {
    fun header(init: Payload.Header.() -> Unit): NotifyConfig {
        defaultHeader.init()
        return this
    }

    fun alerting(key: String, init: Payload.Alerts.() -> Unit): NotifyConfig {
        // Clone object and assign the key.
        defaultAlerting = defaultAlerting.copy(channelKey = key)
        defaultAlerting.init()
        return this
    }

    fun progress(init: Payload.Progress.() -> Unit): NotifyConfig {
        defaultProgress.init()
        return this
    }
}