package app.cta4j.common.mapper;

import app.cta4j.busroute.dto.BusPredictionType;
import app.cta4j.common.model.TrainRoute;
import com.cta4j.model.train.Route;
import org.mapstruct.Named;

public final class CtaMappingHelpers {
    private CtaMappingHelpers() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    @Named("toTrainRoute")
    public static TrainRoute toTrainRoute(Route route) {
        if (route == null) {
            return null;
        }

        String string = route.toString()
                             .toUpperCase();

        return switch (string) {
            case "RED" -> TrainRoute.RED;
            case "BLUE" -> TrainRoute.BLUE;
            case "BROWN" -> TrainRoute.BROWN;
            case "GREEN" -> TrainRoute.GREEN;
            case "ORANGE" -> TrainRoute.ORANGE;
            case "PURPLE" -> TrainRoute.PURPLE;
            case "PINK" -> TrainRoute.PINK;
            case "YELLOW" -> TrainRoute.YELLOW;
            case "N/A" -> TrainRoute.N_A;
            default -> {
                String message = "A route with the name \"%s\" does not exist".formatted(string);

                throw new IllegalArgumentException(message);
            }
        };
    }

    @Named("toBusPredictionType")
    public static BusPredictionType toBusPredictionType(com.cta4j.model.bus.BusPredictionType predictionType) {
        if (predictionType == null) {
            return null;
        }

        String string = predictionType.toString()
                                      .toUpperCase();

        return switch (string) {
            case "ARRIVAL" -> BusPredictionType.ARRIVAL;
            case "DEPARTURE" -> BusPredictionType.DEPARTURE;
            default -> {
                String message = "A prediction type with the name \"%s\" does not exist".formatted(string);

                throw new IllegalArgumentException(message);
            }
        };
    }
}
