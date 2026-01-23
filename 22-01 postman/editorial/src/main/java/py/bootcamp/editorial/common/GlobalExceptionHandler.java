package py.bootcamp.editorial.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Maneja ResponseStatusException, cuando hay error (400 BAD_REQUEST, 404 NOT_FOUND, etc.) devuelve un json "bonito" con formato ApiResponse
    @ExceptionHandler(ResponseStatusException.class)
    public org.springframework.http.ResponseEntity<ApiResponse<Object>> handleResponseStatus(ResponseStatusException ex) {
        HttpStatus status = (HttpStatus) ex.getStatusCode();
        return org.springframework.http.ResponseEntity.status(status)
                .body(ApiResponse.fail(ex.getReason() != null ? ex.getReason() : "Error", null));
    }

    // Maneja errores genericos y devuelve un json con "Error Interno"
    @ExceptionHandler(Exception.class)
    public org.springframework.http.ResponseEntity<ApiResponse<Object>> handleGeneric(Exception ex) {
        return org.springframework.http.ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, "Error interno", Map.of("error", ex.getClass().getSimpleName()), Instant.now()));
    }
}
