package com.naturerun.launcher

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.launcherStore by preferencesDataStore("nature_run_settings")
class LauncherPreferences(private val context: Context) {
    private val widgetKey = booleanPreferencesKey("widget_enabled")
    private val glassKey = booleanPreferencesKey("soft_glass")
    val widgetEnabled: Flow<Boolean> = context.launcherStore.data.map { it[widgetKey] ?: true }
    val softGlass: Flow<Boolean> = context.launcherStore.data.map { it[glassKey] ?: true }
    suspend fun setWidgetEnabled(enabled: Boolean) { context.launcherStore.edit { it[widgetKey] = enabled } }
    suspend fun setSoftGlass(enabled: Boolean) { context.launcherStore.edit { it[glassKey] = enabled } }
}
