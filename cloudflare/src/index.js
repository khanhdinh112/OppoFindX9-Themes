const json = (data, status = 200, origin = "*") => new Response(JSON.stringify(data), { status, headers: { "content-type": "application/json; charset=utf-8", "access-control-allow-origin": origin, "access-control-allow-methods": "GET,OPTIONS", "access-control-allow-headers": "content-type,authorization" } });
export default { async fetch(request, env) {
  const origin = env.ALLOWED_ORIGIN || "*";
  if (request.method === "OPTIONS") return new Response(null, { headers: { "access-control-allow-origin": origin, "access-control-allow-methods": "GET,OPTIONS", "access-control-allow-headers": "content-type,authorization" } });
  const url = new URL(request.url);
  if (url.pathname === "/health") return json({ ok: true, service: "nature-run-coros-api" }, 200, origin);
  if (url.pathname !== "/api/coros/summary" || request.method !== "GET") return json({ error: "not_found" }, 404, origin);
  if (!env.COROS_API_BASE || !env.COROS_ACCESS_TOKEN) return json({ error: "coros_not_configured" }, 503, origin);
  try {
    const upstream = await fetch(`${env.COROS_API_BASE.replace(/\/$/, "")}/summary`, { headers: { authorization: `Bearer ${env.COROS_ACCESS_TOKEN}`, accept: "application/json" } });
    if (!upstream.ok) return json({ error: "coros_upstream_error", status: upstream.status }, 502, origin);
    const raw = await upstream.json();
    return json({ weeklyDistanceKm: Number(raw.weeklyDistanceKm || raw.weekly_distance_km || 0), elevationM: Number(raw.elevationM || raw.elevation_m || 0), averagePace: raw.averagePace || raw.average_pace || "—", recoveryPercent: Number(raw.recoveryPercent || raw.recovery_percent || 0), loadRatio: Number(raw.loadRatio || raw.load_ratio || 0), latest: raw.latest || null, syncedAt: new Date().toISOString() }, 200, origin);
  } catch { return json({ error: "sync_failed" }, 502, origin); }
} };
