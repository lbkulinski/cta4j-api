package app.cta4j.api.external.bus.predictions;

import com.fasterxml.jackson.annotation.JsonAlias;

public record CtaPredictionsResponse(
    @JsonAlias("bustime-response")
    CtaPredictionsBustimeResponse bustimeResponse
) {
}
