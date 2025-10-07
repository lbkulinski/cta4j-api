package app.cta4j.common.api.external.bus.prediction;

import com.fasterxml.jackson.annotation.JsonAlias;

public record CtaPredictionsResponse(
    @JsonAlias("bustime-response")
    CtaPredictionsBustimeResponse bustimeResponse
) {
}
