package com.pradeep.notification_lib


internal data class RawNotification(
    internal val meta: Payload.Meta,
    internal val alerting: Payload.Alerts,
    internal val header: Payload.Header,
    internal val content: Payload.Content,
    internal val actions: ArrayList<Action>?,
    internal val progress: Payload.Progress
)