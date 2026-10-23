package com.naturerun.launcher

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.*
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.layout.*
import androidx.glance.text.*

class RunningWidget : GlanceAppWidget() { override suspend fun provideGlance(context: android.content.Context, id: GlanceId) { provideContent { Box(GlanceModifier.fillMaxSize().background(Color(0xFFF2F7F2)).cornerRadius(22.dp).padding(16.dp)) { Column { Text("TRAIL RUNNING", style = TextStyle(color = ColorProvider(Color(0xFF789D88)), fontSize = 11.sp)); Text("24.8 km", style = TextStyle(color = ColorProvider(Color(0xFF31483D)), fontSize = 25.sp, fontWeight = FontWeight.Bold)); Text("684 m elevation  •  5:42 /km", style = TextStyle(color = ColorProvider(Color(0xFF789D88)), fontSize = 11.sp)); Spacer(GlanceModifier.height(8.dp)); Text("This week  ↗", style = TextStyle(color = ColorProvider(Color(0xFF486659)), fontSize = 12.sp)) } } } } }
