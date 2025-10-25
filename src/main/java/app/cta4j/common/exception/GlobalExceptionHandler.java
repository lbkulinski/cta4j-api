package app.cta4j.common.exception;

import com.rollbar.notifier.Rollbar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public final class GlobalExceptionHandler {
    private final Rollbar rollbar;

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    public GlobalExceptionHandler(Rollbar rollbar) {
        this.rollbar = rollbar;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public void handleException(ResponseStatusException exception) {
        throw exception;
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public void handleException(NoResourceFoundException exception) throws NoResourceFoundException {
        throw exception;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleException(Exception exception) {
        String message = "An exception occurred";

        log.error(message, exception);

        this.rollbar.error(exception, message);
    }
}
