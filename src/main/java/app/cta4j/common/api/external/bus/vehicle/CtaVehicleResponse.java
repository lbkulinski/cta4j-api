package app.cta4j.common.api.external.bus.vehicle;

import com.fasterxml.jackson.annotation.JsonAlias;

public record CtaVehicleResponse(
    @JsonAlias("bustime-response")
    CtaVehicleBustimeResponse bustimeResponse
) {
}
