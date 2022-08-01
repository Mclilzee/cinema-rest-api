package come.projects.cinemarestapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(TicketPurchasingException.class)
    public ResponseEntity<CustomErrorMessage> handleTicketPurchasingException(TicketPurchasingException e, WebRequest request) {

        CustomErrorMessage body = new CustomErrorMessage(e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
