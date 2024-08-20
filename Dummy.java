import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // Only include non-null fields in the JSON output
public class ResponseWrapper<T> {
    private String status;
    private String message;
    private T data;

    public ResponseWrapper(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseWrapper(String status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

// usage
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SampleController {

    @GetMapping("/getData")
    public ResponseEntity<ResponseWrapper<String>> getData() {
        String data = "Sample Data";
        ResponseWrapper<String> response = new ResponseWrapper<>("success", "Data retrieved successfully", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/postData")
    public ResponseEntity<ResponseWrapper<Void>> postData(@RequestBody SomeRequest request) {
        // Process the request here
        ResponseWrapper<Void> response = new ResponseWrapper<>("success", "Data saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper<Void>> handleException(Exception ex) {
        ResponseWrapper<Void> response = new ResponseWrapper<>("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
