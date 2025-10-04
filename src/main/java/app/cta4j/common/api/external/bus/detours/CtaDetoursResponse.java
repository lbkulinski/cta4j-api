package app.cta4j.common.api.external.bus.detours;

import com.fasterxml.jackson.annotation.JsonAlias;

public record CtaDetoursResponse(
    @JsonAlias("bustime-response")
    CtaDetoursBustimeResponse bustimeResponse
) {
}
