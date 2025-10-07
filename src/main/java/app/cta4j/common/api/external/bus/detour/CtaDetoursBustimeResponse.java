package app.cta4j.common.api.external.bus.detour;

import java.util.List;

public record CtaDetoursBustimeResponse(
    List<CtaDetour> dtrs
) {
}
