package prueba.softlond.constructorsas.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex) {
        String errorMessage = "Tipo de construcción desconocido. Los valores permitidos son: CASA, LAGO, CANCHA, EDIFICIO Y GIMNASIO.";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
