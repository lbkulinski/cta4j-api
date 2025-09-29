package app.cta4j.train.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Duration;
import java.time.Instant;

@Schema(
    name = "FollowTrainArrival",
    description = "Represents a predicted CTA train arrival with a specific run number."
)
public record LocationArrival(
    @Schema(
        description = "CTA station ID where the train will arrive, from the official data feed.",
        example = "41320",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    int stationId,

    @Schema(
        description = "CTA stop ID within the station, indicating the specific platform or boarding location.",
        example = "30256",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    int stopId,

    @Schema(
        description = "Name of the station where the train will arrive.",
        example = "Belmont",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    String stationName,

    @Schema(
        description = "Description of the stop or platform, often including travel direction.",
        example = "Service toward 95th or Loop",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    String stopDescription,

    @Schema(
        description = "Unique run number assigned to the train for tracking.",
        example = "819",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    int run,

    @Schema(
        description = "Route the train is serving, identified by its CTA route code.",
        example = "RED",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    Route route,

    @Schema(
        description = "Stop ID for the train’s destination.",
        example = "30089",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    int destinationStopId,

    @Schema(
        description = "Name of the train’s destination station.",
        example = "95th/Dan Ryan",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    String destinationName,

    @Schema(
        description = "Numeric code representing the direction of travel.",
        example = "5",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    int direction,

    @Schema(
        description = "Time when the prediction was generated, in UTC.",
        example = "2025-08-14T19:45:53Z",
        type = "string",
        format = "date-time",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    Instant predictionTime,

    @Schema(
        description = "Predicted time when the train will arrive at the stop, in UTC.",
        example = "2025-08-14T19:46:53Z",
        type = "string",
        format = "date-time",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    Instant arrivalTime,

    @Schema(
        description = "Indicates whether the train is currently approaching the station.",
        example = "true",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    boolean approaching,

    @Schema(
        description = """
        Indicates whether this arrival is based on a scheduled timetable rather than real-time tracking.""",
        example = "false",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    boolean scheduled,

    @Schema(
        description = "Indicates whether the train is delayed beyond its scheduled or predicted arrival.",
        example = "false",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    boolean delayed,

    @Schema(
        description = "Indicates whether the train is experiencing a schedule fault or disruption.",
        example = "false",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    boolean faulted,

    @Schema(
        description = "Additional flags or notes about the train's arrival. Not presently used.",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    String flags
) {
    private static long minutesBetween(Instant from, Instant to) {
        long mins = Duration.between(from, to)
                            .toMinutes();

        return Math.max(mins, 0L);
    }

    @JsonGetter("etaMinutes")
    @Schema(
        description = "Minutes until arrival, rounded down. 0 means Due.",
        example = "3",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    public Long etaMinutes() {
        return minutesBetween(this.predictionTime, this.arrivalTime);
    }

    @JsonGetter("etaLabel")
    @Schema(
        description = "Human-friendly ETA: 'Due' when ≤ 1 minute, otherwise 'Xm'.",
        example = "Due",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    public String etaLabel() {
        long mins = minutesBetween(this.predictionTime, this.arrivalTime);

        return (mins <= 1) ? "Due" : mins + "m";
    }
}
