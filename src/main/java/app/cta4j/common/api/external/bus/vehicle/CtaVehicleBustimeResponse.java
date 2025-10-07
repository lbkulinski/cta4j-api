package app.cta4j.common.api.external.bus.vehicle;

import java.util.List;

public record CtaVehicleBustimeResponse(
    List<CtaVehicle> vehicle
) {
}
