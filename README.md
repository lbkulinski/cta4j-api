# cta4j API

Real‑time Chicago Transit Authority (CTA) data — simple, fast, and free.

This repository hosts the REST API behind [cta4j.app](https://cta4j.app). It aggregates CTA train and bus data into a clean, consistent schema for easy integration in apps, dashboards, and small projects.

---

## Base URL

```
https://api.cta4j.app
```

> All examples below use the production base URL. Responses are JSON.

---

## OpenAPI

* **Spec version:** v0 (OpenAPI 3.1)
* **Download:** `GET /v3/api-docs`

---

## Quick start

```bash
# 1) List train stations
curl -s https://api.cta4j.app/api/stations | jq '.[0:5]'

# 2) Upcoming train arrivals at a station (by stationId)
curl -s https://api.cta4j.app/api/stations/40380/arrivals | jq '[.[] | {stationName, etaLabel, arrivalTime, route}]'

# 3) Follow a specific train by run number
curl -s https://api.cta4j.app/api/trains/902 | jq '{coordinates, arrivals: (.arrivals[0:3])}'

# 4) Explore bus directory (routes → directions → stops)
curl -s https://api.cta4j.app/api/routes | jq '.[0:5]'

# 5) Upcoming bus arrivals for a stop on a route
curl -s https://api.cta4j.app/api/routes/22/stops/14734/arrivals | jq '[.[] | {routeDesignator, destination, etaLabel}]'
```

---

## Endpoints

### Trains

#### `GET /api/trains/{run}` — Get a specific train by **run**

Returns the train’s current coordinates plus its next station arrivals.

**Path params**

* `run` (string) — CTA train run number.

**200 Response**

```json
{
  "coordinates": { "latitude": 41.883, "longitude": -87.632, "heading": 90 },
  "arrivals": [
    {
      "stationId": "40380",
      "stopId": "30174",
      "stationName": "Clark/Lake",
      "stopDescription": "Inner Loop Platform",
      "run": "902",
      "route": "BLUE",
      "destinationStopId": "30002",
      "destinationName": "Forest Park",
      "direction": 5,
      "predictionTime": "2025-10-25T18:39:12Z",
      "arrivalTime": "2025-10-25T18:41:00Z",
      "approaching": false,
      "scheduled": false,
      "delayed": false,
      "faulted": false,
      "flags": "",
      "etaMinutes": 2,
      "etaLabel": "2 min"
    }
  ]
}
```

---

### Stations (Trains)

#### `GET /api/stations` — List all train stations

Returns `[Station]`.

**200 Response (truncated)**

```json
[
  { "id": "40380", "name": "Clark/Lake" },
  { "id": "41400", "name": "Belmont" }
]
```

#### `GET /api/stations/{stationId}/arrivals` — Upcoming arrivals for a station

Returns `[StationArrival]` with ETA fields that are convenient for UIs.

**Path params**

* `stationId` (string)

**200 Response (truncated)**

```json
[
  {
    "stationId": "40380",
    "stopId": "30174",
    "stationName": "Clark/Lake",
    "stopDescription": "Inner Loop Platform",
    "run": "902",
    "route": "BLUE",
    "destinationStopId": "30002",
    "destinationName": "Forest Park",
    "direction": 5,
    "predictionTime": "2025-10-25T18:39:12Z",
    "arrivalTime": "2025-10-25T18:41:00Z",
    "approaching": false,
    "scheduled": false,
    "delayed": false,
    "faulted": false,
    "flags": "",
    "latitude": 41.883,
    "longitude": -87.632,
    "heading": 90,
    "etaMinutes": 2,
    "etaLabel": "2 min"
  }
]
```

---

### Bus directory & arrivals

#### `GET /api/routes` — List bus routes

Returns `[Route]`.

```json
[
  { "id": "22", "name": "Clark" },
  { "id": "151", "name": "Sheridan" }
]
```

#### `GET /api/routes/{routeId}/directions` — List directions for a route

```json
["Northbound", "Southbound"]
```

#### `GET /api/routes/{routeId}/directions/{direction}/stops` — List stops for a route+direction

Returns `[Stop]`.

```json
[
  { "id": "14734", "name": "Clark & Lake" }
]
```

#### `GET /api/routes/{routeId}/directions/{direction}/detours` — Active detours for a route+direction

Returns `[Detour]`.

```json
[
  {
    "id": "D-12345",
    "version": "1",
    "active": true,
    "description": "Stops skipped between X and Y due to construction.",
    "routeDirections": [ { "route": "22", "direction": "Northbound" } ],
    "startTime": "2025-10-25T10:00:00Z",
    "endTime": "2025-10-26T04:00:00Z"
  }
]
```

#### `GET /api/routes/{routeId}/stops/{stopId}/arrivals` — Upcoming bus arrivals

Returns `[StopArrival]` with ETA helpers.

```json
[
  {
    "predictionType": "ARRIVAL",
    "stopName": "Clark & Lake",
    "stopId": "14734",
    "vehicleId": "7901",
    "distanceToStop": 220,
    "route": "22",
    "routeDesignator": "22",
    "routeDirection": "Southbound",
    "destination": "Howard",
    "arrivalTime": "2025-10-25T18:41:00Z",
    "delayed": false,
    "etaMinutes": 2,
    "etaLabel": "2 min"
  }
]
```

---

### Buses

#### `GET /api/buses/{id}` — Get a specific bus by vehicle **id**

Returns vehicle coordinates plus next arrivals.

```json
{
  "id": "7901",
  "route": "22",
  "destination": "Howard",
  "delayed": false,
  "coordinates": { "latitude": 41.885, "longitude": -87.630, "heading": 180 },
  "arrivals": [
    {
      "predictionType": "ARRIVAL",
      "stopName": "Clark & Lake",
      "stopId": "14734",
      "vehicleId": "7901",
      "distanceToStop": 220,
      "route": "22",
      "routeDesignator": "22",
      "routeDirection": "Southbound",
      "destination": "Howard",
      "arrivalTime": "2025-10-25T18:41:00Z",
      "delayed": false,
      "etaMinutes": 2,
      "etaLabel": "2 min"
    }
  ]
}
```

---

## Data models (schemas)

> Types shown here mirror the OpenAPI 3.1 schema and are stable for v0.

* **Station**: `{ id: string, name: string }`
* **Stop**: `{ id: string, name: string }`
* **Train**: `{ coordinates: TrainCoordinates, arrivals: UpcomingTrainArrival[] }`
* **TrainCoordinates**: `{ latitude: number, longitude: number, heading: int }`
* **UpcomingTrainArrival**: train‑focused view of an upcoming stop with ETA helpers
* **StationArrival**: station‑focused view of upcoming trains, includes `latitude`/`longitude`/`heading`
* **Route**: `{ id: string, name: string }`
* **Detour**: `{ id, version, active, description, routeDirections[], startTime, endTime }`
* **Bus**: `{ id, route, destination, delayed, coordinates, arrivals[] }`
* **UpcomingBusArrival / StopArrival**: bus arrival views with `etaMinutes` and `etaLabel`

Route enums used by trains: `RED | BLUE | BROWN | GREEN | ORANGE | PURPLE | PINK | YELLOW | N_A`.

---

## Conventions

* **Time format**: All timestamps are ISO‑8601 UTC (e.g., `2025-10-25T18:41:00Z`).
* **Units**: Distances are feet; headings are degrees; `etaMinutes` is an integer.
* **Booleans**: `approaching`, `scheduled`, `delayed`, `faulted` are explicit flags to simplify UI logic.

---

## Errors

* **200 OK** — Success
* **4xx** — Client errors (e.g., invalid ids)
* **500 Internal Server Error** — Upstream provider issues or unexpected errors

Error bodies are JSON; an example might look like:

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Station not found"
}
```

---

## CORS

CORS is enabled for the public API so browser apps can call it directly. If you hit issues, file an issue in this repo with the failing origin and endpoint.

---

## Rate limits & fair use

This is a community resource. Please:

* Cache responses when possible (many endpoints update ~every 20–30s).
* Avoid tight polling loops; 5–10s cadence is usually sufficient for UIs.
* Contact the maintainer if you have issues.

---

## Versioning

The API is not currently versioned. Breaking changes will be documented in **CHANGELOG.md**. Expect additive changes (new fields) between major versions.

---

## Examples by language

### Java (using any HTTP client)

```java
void main() {
    HttpRequest req = HttpRequest.newBuilder()
                                 .uri(URI.create("https://api.cta4j.app/api/stations/40380/arrivals"))
                                 .build();

    var resp = HttpClient.newHttpClient()
                         .send(req, HttpResponse.BodyHandlers.ofString());

    System.out.println(resp.body());
}
```

### JavaScript / TypeScript (browser)

```ts
const res = await fetch("https://api.cta4j.app/api/routes/22/stops/14734/arrivals");

const arrivals = await res.json();

console.log(arrivals.map(a => a.etaLabel));
```

### cURL

```bash
curl -s https://api.cta4j.app/api/trains/902 | jq '.'
```

---

## Local development

* Java 25+
* Spring Boot
* `./mvnw spring-boot:run` or build the container with the provided Dockerfile.

---

## Support & feedback

* File issues for bugs, incorrect data, or feature requests.
* Include sample requests/responses, timestamps (UTC), and station/route identifiers to help reproduce.

---

## Attribution

This project is not affiliated with the Chicago Transit Authority. CTA data is provided by public APIs; availability and accuracy can vary.
