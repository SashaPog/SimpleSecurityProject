package demoapp.newjwtspringsecurity.exceptions;

import demoapp.newjwtspringsecurity.dto.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1️⃣ Validation — @Valid DTO errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException ex,
                                                             HttpServletRequest req) {

        String firstMessage = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .findFirst()
            .map(e -> e.getField() + " " + e.getDefaultMessage())
            .orElse("Validation failed");

        log.warn("Validation failed: {}", firstMessage);

        return build(HttpStatus.BAD_REQUEST, "Validation error", firstMessage, req);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodValidation(HandlerMethodValidationException ex,
                                                                   HttpServletRequest req) {
        log.warn("method Validation failure", ex);
        return build(HttpStatus.BAD_REQUEST, "Validation error", ex.getMessage(), req);
    }

    // 2️⃣ Binding errors (@ModelAttribute, form-data)
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiErrorResponse> handleBind(BindException ex, HttpServletRequest req) {
        String msg = ex.getAllErrors().stream().findFirst()
            .map(e -> e.getDefaultMessage())
            .orElse("Bind error");

        log.warn("Binding failed: {}", msg);

        return build(HttpStatus.BAD_REQUEST, "Binding error", msg, req);
    }

    // 3️⃣ Security — доступ заборонено
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handleAccessDenied(AccessDeniedException ex,
                                                               HttpServletRequest req) {
        log.warn("Access denied: {}", ex.getMessage());
        return build(HttpStatus.FORBIDDEN, "Forbidden", ex.getMessage(), req);
    }

    // 4️⃣ HTTP method not allowed
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex,
                                                                   HttpServletRequest req) {
        log.warn("Method not allowed: {}", ex.getMessage());
        return build(HttpStatus.METHOD_NOT_ALLOWED, "Method not allowed", ex.getMessage(), req);
    }

    // 5️⃣ Wrong Content-Type
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiErrorResponse> handleMediaType(HttpMediaTypeNotSupportedException ex,
                                                            HttpServletRequest req) {
        log.warn("Unsupported media type: {}", ex.getMessage());
        return build(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Unsupported media type", ex.getMessage(), req);
    }

    // 7️⃣ Catch-all — все, що не оброблено
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneral(Exception ex, HttpServletRequest req) {
        log.error("Unexpected error", ex);
        String classEx = ex.getClass().getSimpleName();
        return build(HttpStatus.INTERNAL_SERVER_ERROR,
            "Internal server error",
            ex.getMessage() != null ? classEx : "Something went wrong",
            req);
    }

    // 📦 helper (єдиний формат відповіді)
    private ResponseEntity<ApiErrorResponse> build(HttpStatus status,
                                                   String error,
                                                   String message,
                                                   HttpServletRequest req) {
        var body = new ApiErrorResponse(
            Instant.now(),
            status.value(),
            error,
            message,
            req.getRequestURI()
        );
        return ResponseEntity.status(status).body(body);
    }
}
