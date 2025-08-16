package app.cta4j.bus.dto;

import app.cta4j.bus.dto.serialization.StringToBooleanDeserializer;
import app.cta4j.bus.dto.serialization.StringToInstantDeserializer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.List;

@Schema(
    name = "BusDetour",
    description = "Represents a CTA bus detour, including its status, affected routes/directions, and time window."
)
public record BusDetourDto(
    @Schema(
        description = "Unique identifier for the detour, as provided by CTA.",
        example = "84A97FD3-0741-4004-884D-0ABB22DAFA28"
    )
    String id,

    @Schema(
        description = "Version number of this detour record for tracking updates.",
        example = "2"
    )
    @JsonAlias("ver")
    int version,

    @Schema(
        description = "Indicates whether the detour is currently active.",
        example = "false"
    )
    @JsonDeserialize(using = StringToBooleanDeserializer.class)
    @JsonAlias("st")
    boolean active,

    @Schema(
        description = "Description or label for the detour, as provided in the CTA data feed.",
        example = "IVD MultiRoute detour 47"
    )
    @JsonAlias("desc")
    String description,

    @ArraySchema(
        arraySchema = @Schema(
            description = "List of affected route and direction pairs for this detour."
        ),
        schema = @Schema(implementation = BusRouteDirectionDto.class)
    )
    @JsonAlias("rtdirs")
    List<BusRouteDirectionDto> routeDirections,

    @Schema(
        description = "Time when the detour begins, in UTC.",
        example = "2025-08-14T18:00:00Z",
        type = "string",
        format = "date-time"
    )
    @JsonDeserialize(using = StringToInstantDeserializer.class)
    @JsonAlias("startdt")
    Instant startDate,

    @Schema(
        description = "Time when the detour ends, in UTC.",
        example = "2025-08-15T04:00:00Z",
        type = "string",
        format = "date-time"
    )
    @JsonDeserialize(using = StringToInstantDeserializer.class)
    @JsonAlias("enddt")
    Instant endDate
) {
}
