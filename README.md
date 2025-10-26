# 🚏 cta4j API

Real‑time Chicago Transit Authority (CTA) data — simple, fast, and free.

The **cta4j API** powers [cta4j.app](https://cta4j.app) and provides clean, unified endpoints for accessing CTA **train** and **bus** data. Built for developers who want reliable, human‑friendly transit information without managing raw XML feeds or API keys.

---

## 🚆 Overview

The API aggregates CTA’s public data into an easy-to-use REST interface:

* Consistent JSON schema for both train and bus predictions
* Simplified endpoints and identifiers (no API keys required)
* Designed for public consumption by apps, dashboards, or personal projects

---

## 🌐 Base URL

```
https://api.cta4j.app
```

> All responses are JSON.

---

## 📄 OpenAPI Specification

The full OpenAPI spec is available for developers who want to generate clients or explore endpoints interactively.

**Endpoints:**

* `GET /v3/api-docs` — JSON format (OpenAPI 3.1)

You can view or import it directly in tools like [Swagger Editor](https://editor.swagger.io/) or [Postman](https://www.postman.com/).

Example:

```bash
curl -s https://api.cta4j.app/v3/api-docs | jq '.'
```

---

## 🧩 Example Usage

### Fetch upcoming train arrivals for a station

```bash
curl -s https://api.cta4j.app/api/stations/40380/arrivals | jq '[.[] | {stationName, etaLabel, arrivalTime, route}]'
```

### Follow a train by its run number

```bash
curl -s https://api.cta4j.app/api/trains/902 | jq '{coordinates, arrivals: (.arrivals[0:3])}'
```

### Fetch upcoming bus arrivals for a stop

```bash
curl -s https://api.cta4j.app/api/routes/22/stops/14734/arrivals | jq '[.[] | {routeDesignator, destination, etaLabel}]'
```

---

## 📚 Endpoints

### Trains

* `GET /api/trains/{run}` — Get details and upcoming stops for a specific train.
* `GET /api/stations` — List all CTA train stations.
* `GET /api/stations/{stationId}/arrivals` — Get upcoming train arrivals at a given station.

### Buses

* `GET /api/routes` — List all bus routes.
* `GET /api/routes/{routeId}/directions` — Get available directions for a bus route.
* `GET /api/routes/{routeId}/directions/{direction}/stops` — List stops for a route and direction.
* `GET /api/routes/{routeId}/stops/{stopId}/arrivals` — Get upcoming bus arrivals for a stop.
* `GET /api/buses/{id}` — Get live data for a specific bus.

---

## ⚙️ Conventions

* **Timestamps**: ISO‑8601 UTC (e.g., `2025‑10‑25T18:41:00Z`)
* **Units**: Distances in feet; headings in degrees
* **Flags**: Boolean fields like `approaching`, `delayed`, `scheduled` simplify UI logic

---

## 🧠 Design Goals

* **No keys required** → instant, public access
* **Consistent schema** → same shape for trains and buses
* **Lightweight** → built for developers and hobbyists
* **Transparent** → no rate throttling for reasonable use

---

## 🛠️ Planned Improvements

* Add **service alerts** endpoint
* Add **test coverage**
* Add **real‑time updates via WebSocket/SSE**
* Expand to support **Pace**, **Metra**, and other cities
* Improve **data caching** and **error granularity**

Have an idea or feature request? Open an issue — contributions are welcome!

---

## 🧾 License

This project is licensed under the [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0).
Copyright © 2025 Logan Bailey Kulinski.

---

## 🗺️ Links

* [Project Website](https://cta4j.app)
* [Java SDK](https://github.com/lbkulinski/cta4j-java-sdk)
* [CTA Developer Portal](https://www.transitchicago.com/developers/)
* [Bus Tracker API](https://www.transitchicago.com/developers/bustracker/)
* [Train Tracker API](https://www.transitchicago.com/developers/traintrackerapply/)

---

*Built with ❤️ by [Logan Kulinski](https://lbku.net)*
