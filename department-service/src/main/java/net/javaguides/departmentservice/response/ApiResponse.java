package net.javaguides.departmentservice.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse<T> {

    private LocalDateTime timestamp;
    private int status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorTitle;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String path;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;

    public ApiResponse(HttpStatusCode statusCode, String message, T data) {
        this.timestamp = LocalDateTime.now();
        this.status = statusCode.value();
        this.message = message;
        this.data = data;
    }

    public ApiResponse(HttpStatusCode statusCode, String errorTitle, String message, List<String> errors, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = statusCode.value();
        this.errorTitle = errorTitle;
        this.message = message;
        this.errors = errors;
        this.path = path;

    }

    public ResponseEntity<ApiResponse<T>> sendResponse() {
        return ResponseEntity.status(this.status).body(this);
    }

    public static ResponseEntity<Object> error(HttpStatusCode statusCode, HttpHeaders headers, String errorTitle, String message, List<String> errors, String path) {
        return ResponseEntity.status(statusCode).headers(headers).body(new ApiResponse<>(statusCode, errorTitle, message, errors, path));
    }
}