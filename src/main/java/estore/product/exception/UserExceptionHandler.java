package estore.product.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFound(Exception e, WebRequest request) {
        return handleExceptionInternal(e, "User not found",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<Object> productNotFoundException(ProductNotFoundException e, WebRequest request) {
        return handleExceptionInternal(e, "Product not found",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


    @ExceptionHandler({CategoryNotFoundException.class})
    public ResponseEntity<Object> categoryNotFoundException(CategoryNotFoundException e, WebRequest request) {
        return handleExceptionInternal(e, "Category not found",
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, "Following properties required", errorList);
        return handleExceptionInternal(ex, errorDetails, headers, errorDetails.getStatus(), request);    }


}
