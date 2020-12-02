package br.com.zup.estrelas.zquads.exception;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import br.com.zup.estrelas.zquads.dto.ErrorDTO;
import br.com.zup.estrelas.zquads.dto.ResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final int STR_NOME_DO_CAMPO = 0;
    private static final int IGNORA_POS_PONTO = 1;

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({GenericException.class})
    public @ResponseBody ResponseDTO handleErrosDeNegocio(Exception e) {
        return new ResponseDTO(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody List<ErrorDTO> handleValidationError(MethodArgumentNotValidException e) {

        List<ErrorDTO> validationError = new ArrayList<>();

        for (ObjectError error : e.getBindingResult().getAllErrors()) {

            String fullFieldName = error.getCodes()[STR_NOME_DO_CAMPO];

            if (nonNull(error.getCodes()) && nonNull(error.getCodes()[STR_NOME_DO_CAMPO])) {

                validationError.add(new ErrorDTO(
                        fullFieldName.substring(fullFieldName.lastIndexOf(".") + IGNORA_POS_PONTO),
                        error.getDefaultMessage()));
            }
        }

        return validationError;
    }

}
