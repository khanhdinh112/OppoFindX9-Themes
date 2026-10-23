package com.naturerun.launcher

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

object OverlayPermission {
    fun isGranted(context: Context): Boolean = Settings.canDrawOverlays(context)
    fun openSettings(context: Context) { context.startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:${context.packageName}"))) }
}
