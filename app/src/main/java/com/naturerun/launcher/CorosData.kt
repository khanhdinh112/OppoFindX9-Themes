package com.naturerun.launcher

/** Contract used by Home, Dashboard and the Glance widget.
 * The production implementation should call your secure sync API; never ship COROS tokens in the APK.
 */
data class CorosRun(val date: String, val title: String, val distanceKm: Double, val duration: String, val pace: String, val heartRate: Int)
data class CorosSnapshot(val weeklyDistanceKm: Double, val elevationM: Int, val averagePace: String, val recoveryPercent: Int, val loadRatio: Double, val latest: CorosRun?)

interface CorosRepository { suspend fun snapshot(): CorosSnapshot }

class DemoCorosRepository : CorosRepository {
    override suspend fun snapshot() = CorosSnapshot(
        weeklyDistanceKm = 26.46,
        elevationM = 684,
        averagePace = "6:59 /km",
        recoveryPercent = 100,
        loadRatio = 0.75,
        latest = CorosRun("2026-10-19", "Chạy nhẹ 5 km", 5.01, "39:55", "7:58 /km", 130)
    )
}
