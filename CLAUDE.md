# CLAUDE.md

## Project Overview

Spring Boot REST API for CTA bus and train data. Backed by DynamoDB on AWS, deployed via Docker to ECR. Uses the `cta4j-java-sdk` for all CTA API calls.

## API Endpoints

**Bus**
- `GET /api/bus/routes` — all bus routes
- `GET /api/bus/routes/{routeId}/directions` — directions for a route
- `GET /api/bus/routes/{routeId}/directions/{direction}/stops` — stops for a route+direction
- `GET /api/bus/stops/{stopId}` — a specific stop
- `GET /api/bus/stops/{stopId}/arrivals` — arrivals for a stop (optional `?routeId=` query param)
- `GET /api/bus/vehicles/{vehicleId}` — a specific vehicle
- `GET /api/bus/vehicles/{vehicleId}/arrivals` — arrivals for a vehicle
- `GET /api/bus/detours` — detours (optional `?routeId=` and `?direction=` query params)

**Train**
- `GET /api/train/stations` — all train stations
- `GET /api/train/stations/{stationId}/arrivals` — arrivals for a station
- `GET /api/train/runs/{run}` — info for a specific train run

## Architecture

- **Controllers** — HTTP concerns only (path/query binding, returning DTOs)
- **Services** — business logic; validate against DB first, then call CTA SDK for live data
- **Repositories** — DynamoDB access via `DynamoDbEnhancedClient`; return data only, no business logic; return `Optional<T>` for single-item lookups that may be absent, and empty collections (never `null`) when nothing matches — do not throw domain exceptions
- **Services** own not-found handling: unwrap a repository's `Optional<T>` with `.orElseThrow(() -> new SomeNotFoundException(...))` (or check for an empty collection) so the decision of what "missing" means stays in the business layer, not the data-access layer
- **`@ConfigurationProperties` records** — one record per config namespace, validated with JSR-303

Package layout: transit type (`bus`, `train`) then layer (`controller`, `service`, `repository`, `dto`, `model`, `exception`, `mapper`). Shared types live in `common`.

## Annotation Ordering

Always apply annotations in this order: **Spring → OpenAPI/other → JSpecify**

```java
@RestController
@RequestMapping("/api/...")
@Tag(name = "...", description = "...")
@NullMarked
public final class MyController { }
```

## Null Safety

- All new classes, records, interfaces, and enums in `src/main` must have `@NullMarked` (from `org.jspecify.annotations`).
- Test classes do not use `@NullMarked`.
- `@Configuration` bean-definition classes do not use `@NullMarked` (`@ConfigurationProperties` records still do — see below).
- Exception classes (`RuntimeException` subclasses) do not use `@NullMarked`.
- Use `@Nullable` on parameters or return types that can be null.

## Configuration Properties

All `@ConfigurationProperties` classes must be records annotated with `@Validated`. Use JSR-303 constraints directly on fields:

```java
@ConfigurationProperties(prefix = "app.example")
@Validated
@NullMarked
public record ExampleProperties(
    @NotBlank String name,
    @Min(1) @Max(100) int pageSize
) {}
```

Register each record in `@EnableConfigurationProperties` on `Application.java`.

## DynamoDB Conventions

DynamoDB model records use Lombok `@Builder` + `@DynamoDbImmutable`. Annotate keys via `@DynamoDbPartitionKey` and `@DynamoDbSortKey`.

Table: `cta-bus-routes`
- PK: `id`
- Attributes:
  - `name`
  - `hexColor`
  - `designator`

Table: `cta-bus-route-directions`
- PK: `routeId`
- Attributes:
  - `directions` (list of strings)

Table: `cta-bus-route-stops`
- PK: `routeId`
- SK: `direction`
- Attributes:
  - `stops` (list of `{id, name}`)

Table: `cta-bus-stops`
- PK: `id`
- Attributes:
  - `name`
  - `latitude`
  - `longitude`

Table: `cta-train-stations`
- PK: `mapId`
- SK: `stopId`
- Attributes:
  - `direction` (`NORTH`/`EAST`/`SOUTH`/`WEST`)
  - `stopName`, `name`, `descriptiveName`
  - `adaAccessible`
  - `lines` (list of `TrainLine`)
  - `latitude`, `longitude`

Use `@Cacheable` on repository methods with distinct cache names per method (e.g. `"stopsByRouteId"` vs `"stopsByRouteIdAndDirection"`).

## Validation Pattern

Always validate a stop or station exists in DynamoDB before calling the CTA SDK. This avoids relying on CTA's generic error messages for 404 decisions.

## Testing

- Unit tests use `@ExtendWith(MockitoExtension.class)` — no Spring context
- Assertions use AssertJ (`assertThat`, `assertThatThrownBy`)
- Use `ArgumentCaptor` to verify what was passed to mocks, not just that a method was called
- Raw `DynamoDbTable<T>` mocks require `@SuppressWarnings("rawtypes")` on the field and `@SuppressWarnings("unchecked")` on methods that use them

## Code Style

- No `ResponseEntity` in service methods
- No comments unless the why is non-obvious
- Prefer `Objects.requireNonNull` for null guards in public service/repository methods
- `final` on all classes that are not designed for extension, except classes with `@Cacheable`-annotated methods — Spring's CGLIB proxying requires those to stay non-final
- Lombok `@Builder` + `@DynamoDbImmutable` for DynamoDB model records
- Always reference instance fields and methods with `this.`
- Method/constructor parameter lists: keep on one line if ≤100 chars; otherwise wrap with one parameter per line (closing `)` and opening `{` each on their own line)
- Record component lists: 1 component stays on one line; 2+ components always wrap one per line, regardless of length
- Assignment statements that exceed 100 chars: break after `=` first, putting the full right-hand side on the next line; only fall back to wrapping the call's argument list one-per-line if the right-hand side still doesn't fit on that line
