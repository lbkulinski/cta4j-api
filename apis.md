## Bus Endpoints
| Endpoint                                         | Description                                   |
|--------------------------------------------------|-----------------------------------------------|
| /api/routes                                      | Get bus routes                                |
| /api/routes/{route}/directions                   | Get directions for a specific route           |
| /api/routes/{route}/directions/{direction}/stops | Get stops for a specific direction of a route |
| /api/stops/{stopId}/arrivals                     | Get arrivals for a specific stop              |

Also some kind of detours endpoint... Not sure what that will look like. The prior endpoint was `/api/routes/{{routeId}}/directions/{{direction}}/detours`

## Train Endpoints
| Endpoint                             | Description                          |
|--------------------------------------|--------------------------------------|
| /api/stations                        | Get train stations                   |
| /api/stations/{{stationId}}/arrivals | Get arrivals for a specific station  |
| /api/trains/{{run}}                  | Get information for a specific train |
