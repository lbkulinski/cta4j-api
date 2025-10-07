package app.cta4j.common.api.external.bus.detour;

import com.fasterxml.jackson.annotation.JsonAlias;

public record CtaDetoursResponse(
    @JsonAlias("bustime-response")
    CtaDetoursBustimeResponse bustimeResponse
) {
}
