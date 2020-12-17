package br.com.zup.estrelas.zquads.exception;

import static java.util.Objects.nonNull;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import br.com.zup.estrelas.zquads.dto.ErrorDTO;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final int FIELD_NAME_POSITION = 0;
    private static final int DOT_POSITION = 1;

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({GenericException.class})
    public @ResponseBody ResponseDTO handleBusinessErrors(Exception e) {
        return new ResponseDTO(e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({BadCredentialsException.class, DisabledException.class,
        IllegalArgumentException.class, ExpiredJwtException.class})
    public @ResponseBody ResponseDTO handleSecurityErrors(Exception e) {
        return new ResponseDTO(e.getMessage());
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody List<ErrorDTO> handleValidationError(MethodArgumentNotValidException e) {

        List<ErrorDTO> validationError = new ArrayList<>();

        for (ObjectError error : e.getBindingResult().getAllErrors()) {

            String fullFieldName = error.getCodes()[FIELD_NAME_POSITION];

            if (nonNull(error.getCodes()) && nonNull(error.getCodes()[FIELD_NAME_POSITION])) {

                validationError.add(new ErrorDTO(
                        fullFieldName.substring(fullFieldName.lastIndexOf(".") + DOT_POSITION),
                        error.getDefaultMessage()));
            }
        }

        return validationError;
    }
}
