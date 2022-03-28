package com.pradeep.notification_lib

import androidx.annotation.IntDef

annotation class Experimental

@DslMarker
annotation class NotifyScopeMarker

@Retention(AnnotationRetention.SOURCE)
@IntDef(NotificationBuilder.IMPORTANCE_MIN,
    NotificationBuilder.IMPORTANCE_LOW,
    NotificationBuilder.IMPORTANCE_NORMAL,
    NotificationBuilder.IMPORTANCE_HIGH,
    NotificationBuilder.IMPORTANCE_MAX)
annotation class NotificationImportance