package app.cta4j.train.dto;

import app.cta4j.train.dto.serialization.StringToBooleanDeserializer;
import app.cta4j.train.dto.serialization.StringToInstantDeserializer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;

@Schema(
    name = "TrainArrival",
    description = "Represents a predicted CTA train arrival at a specific station and stop."
)
public record TrainArrivalDto(
    @Schema(
        description = "CTA station ID where the train will arrive, from the official data feed.",
        example = "41320"
    )
    @JsonAlias("staId")
    int stationId,

    @Schema(
        description = "CTA stop ID within the station, indicating the specific platform or boarding location.",
        example = "30256"
    )
    @JsonAlias("stpId")
    int stopId,

    @Schema(
        description = "Name of the station where the train will arrive.",
        example = "Belmont"
    )
    @JsonAlias("staNm")
    String stationName,

    @Schema(
        description = "Description of the stop or platform, often including travel direction.",
        example = "Service toward 95th or Loop"
    )
    @JsonAlias("stpDe")
    String stopDescription,

    @Schema(
        description = "Unique run number assigned to the train for tracking.",
        example = "819"
    )
    @JsonAlias("rn")
    int run,

    @Schema(
        description = "Route the train is serving, identified by its CTA route code.",
        example = "RED"
    )
    @JsonAlias("rt")
    TrainRoute route,

    @Schema(
        description = "Stop ID for the train’s destination.",
        example = "30089"
    )
    @JsonAlias("destSt")
    int destinationStopId,

    @Schema(
        description = "Name of the train’s destination station.",
        example = "95th/Dan Ryan"
    )
    @JsonAlias("destNm")
    String destinationName,

    @Schema(
        description = "Numeric code representing the direction of travel.",
        example = "5"
    )
    @JsonAlias("trDr")
    int direction,

    @Schema(
        description = "Time when the prediction was generated, in UTC.",
        example = "2025-08-14T19:45:53Z",
        type = "string",
        format = "date-time"
    )
    @JsonDeserialize(using = StringToInstantDeserializer.class)
    @JsonAlias("prdt")
    Instant predictionTime,

    @Schema(
        description = "Predicted time when the train will arrive at the stop, in UTC.",
        example = "2025-08-14T19:46:53Z",
        type = "string",
        format = "date-time"
    )
    @JsonDeserialize(using = StringToInstantDeserializer.class)
    @JsonAlias("arrT")
    Instant arrivalTime,

    @Schema(
        description = "Indicates whether the train is currently approaching the station.",
        example = "true"
    )
    @JsonDeserialize(using = StringToBooleanDeserializer.class)
    @JsonAlias("isApp")
    boolean approaching,

    @Schema(
        description = """
        Indicates whether this arrival is based on a scheduled timetable rather than real-time tracking.""",
        example = "false"
    )
    @JsonDeserialize(using = StringToBooleanDeserializer.class)
    @JsonAlias("isSch")
    boolean scheduled,

    @Schema(
        description = "Indicates whether the train is experiencing a schedule fault or disruption.",
        example = "false"
    )
    @JsonDeserialize(using = StringToBooleanDeserializer.class)
    @JsonAlias("isFlt")
    boolean faulted,

    @Schema(
        description = "Indicates whether the train is delayed beyond its scheduled or predicted arrival.",
        example = "false"
    )
    @JsonDeserialize(using = StringToBooleanDeserializer.class)
    @JsonAlias("isDly")
    boolean delayed,

    @Schema(
        description = "Latitude of the train's current location, in decimal degrees.",
        example = "41.9452"
    )
    @JsonAlias("lat")
    BigDecimal latitude,

    @Schema(
        description = "Longitude of the train's current location, in decimal degrees.",
        example = "-87.65353"
    )
    @JsonAlias("lon")
    BigDecimal longitude,

    @Schema(
        description = "Compass heading of the train in degrees, where 0 is north.",
        example = "178"
    )
    @JsonAlias("heading")
    int heading
) {
}
