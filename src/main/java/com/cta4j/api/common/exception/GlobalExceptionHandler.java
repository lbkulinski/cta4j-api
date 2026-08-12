package com.cta4j.api.common.exception;

import com.cta4j.api.bus.detour.exception.InvalidDetourRequestException;
import com.cta4j.api.bus.route.exception.RouteNotFoundException;
import com.cta4j.api.bus.stop.exception.StopNotFoundException;
import com.cta4j.api.bus.vehicle.exception.VehicleNotFoundException;
import com.rollbar.notifier.Rollbar;
import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.NullMarked;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
@NullMarked
public final class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final Rollbar rollbar;

    private static final String BAD_REQUEST_DETAIL = "The request was invalid.";
    private static final String BAD_REQUEST_TITLE = "Bad Request";

    private static final String NOT_FOUND_DETAIL = "The requested resource was not found.";
    private static final String NOT_FOUND_TITLE = "Not Found";

    private static final String INTERNAL_ERROR_DETAIL = "An unexpected error occurred.";
    private static final String INTERNAL_ERROR_TITLE = "Internal Server Error";

    @Autowired
    public GlobalExceptionHandler(Rollbar rollbar) {
        this.rollbar = rollbar;
    }

    @ExceptionHandler(InvalidDetourRequestException.class)
    public ResponseEntity<ProblemDetail> handleBadRequestException(HttpServletRequest request) {
        ProblemDetail problemDetail = buildProblemDetail(HttpStatus.BAD_REQUEST, request);

        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler({
        RouteNotFoundException.class,
        StopNotFoundException.class,
        VehicleNotFoundException.class
    })
    public ResponseEntity<ProblemDetail> handleNotFoundException(HttpServletRequest request) {
        ProblemDetail problem = buildProblemDetail(HttpStatus.NOT_FOUND, request);

        return ResponseEntity.of(problem).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleException(Exception e, HttpServletRequest request) {
        String message = "Unexpected error for %s".formatted(request.getRequestURI());

        log.error(message, e);
        this.rollbar.error(e, message);

        ProblemDetail problem = buildProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, request);

        return ResponseEntity.of(problem).build();
    }

    private record TitleDetail(String title, String detail) {}

    private static ProblemDetail buildProblemDetail(HttpStatus status, HttpServletRequest request) {
        TitleDetail titleDetail = switch (status) {
            case BAD_REQUEST -> new TitleDetail(BAD_REQUEST_TITLE, BAD_REQUEST_DETAIL);
            case NOT_FOUND -> new TitleDetail(NOT_FOUND_TITLE, NOT_FOUND_DETAIL);
            default -> new TitleDetail(INTERNAL_ERROR_TITLE, INTERNAL_ERROR_DETAIL);
        };

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, titleDetail.detail);

        URI instance = URI.create(request.getRequestURI());

        problem.setTitle(titleDetail.title);
        problem.setInstance(instance);

        return problem;
    }
}
