# Nature Run Launcher

Android launcher concept for OPPO Find X9 Pro, built with Kotlin, Jetpack Compose, Material 3 and Jetpack Glance.

## Included

- Alpine Light white launcher home
- App Library with installed-app discovery and search
- Running Dashboard
- Standalone home-screen widget
- Launcher-as-default-home intent
- Settings surface for wallpaper, glass intensity and overlay states
- `CorosRepository` data contract shared by Home, Dashboard and widget

## Run

Open this folder in Android Studio Hedgehog or newer, let Gradle sync, then run on an Android 8+ device. To test the launcher intent, press Home and choose Nature Run. To add the widget, long-press the ColorOS home screen and select Nature Run.

## Build without Android Studio

Push this project to GitHub, open the **Actions** tab, select **Build Nature Run APK**, and choose **Run workflow**. Download the `nature-run-debug-apk` artifact, extract `app-debug.apk`, send it to the phone, enable installation from that source, and tap the APK to install it.

## Next production integrations

- Health Connect for real distance, pace and heart-rate data
- DataStore for user settings
- Runtime permission flow for `SYSTEM_ALERT_WINDOW` before any floating overlay
- Wallpaper picker and packaged Alpine wallpapers
- WorkManager periodic widget refresh within Android limits

## COROS sync architecture

The chat connector successfully returned the current COROS snapshot, but connector credentials must not be embedded in an APK. Use a small authenticated backend (or your own server) that calls COROS, normalizes the response to `CorosSnapshot`, and let the Android app fetch only that normalized JSON. Replace `DemoCorosRepository` with that network implementation, then refresh the widget through WorkManager.

## Cloudflare Worker

The `cloudflare/` folder contains the secure API layer. From that folder run `npm install`, `npx wrangler login`, `npx wrangler secret put COROS_API_BASE`, `npx wrangler secret put COROS_ACCESS_TOKEN`, then `npx wrangler deploy`. The Android app should call `GET /api/coros/summary`; the COROS token stays in Cloudflare and never ships in the APK.
