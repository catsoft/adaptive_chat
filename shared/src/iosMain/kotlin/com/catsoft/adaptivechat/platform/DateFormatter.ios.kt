package com.catsoft.adaptivechat.platform

import platform.Foundation.*

actual fun formatTimestamp(timestamp: Long): String {
    val date = NSDate.dateWithTimeIntervalSince1970(timestamp.toDouble() / 1000.0)
    val formatter = NSDateFormatter()
    formatter.dateFormat = "HH:mm"
    return formatter.stringFromDate(date)
}

actual fun formatDate(timestamp: Long): String {
    val now = NSDate().timeIntervalSince1970.toLong() * 1000
    val diff = now - timestamp
    
    return when {
        diff < 60000 -> "Just now"
        diff < 3600000 -> "${diff / 60000}m ago"
        diff < 86400000 -> "${diff / 3600000}h ago"
        else -> {
            val date = NSDate.dateWithTimeIntervalSince1970(timestamp.toDouble() / 1000.0)
            val formatter = NSDateFormatter()
            formatter.dateFormat = "MMM dd"
            return formatter.stringFromDate(date)
        }
    }
}
