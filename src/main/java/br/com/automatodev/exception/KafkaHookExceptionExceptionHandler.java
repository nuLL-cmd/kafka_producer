package br.com.automatodev.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class KafkaHookExceptionExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(KafkaHookException.class)
    private ResponseEntity<Object> handleGeraLogException(KafkaHookException ex, WebRequest request) {

        HttpStatus status =
                Objects.isNull(ex.getStatus()) ? HttpStatus.INTERNAL_SERVER_ERROR : ex.getStatus();
        Long timestamp = System.currentTimeMillis();

        ExceptionDto dto =
                ExceptionDto.builder()
                        .mensagem(ex.getLocalizedMessage())
                        .timestamp(timestamp)
                        .status(status.value())
                        .build();

        return new ResponseEntity<>(dto, new HttpHeaders(), dto.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<CamposErro> campos = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            campos.add(new CamposErro(((FieldError) error).getField(), error.getDefaultMessage()));
        }

        ExceptionDto dto =
                ExceptionDto.builder()
                        .mensagem("Um ou mais campos possuem ocorrências.")
                        .status(status.value())
                        .timestamp(System.currentTimeMillis())
                        .camposErro(campos)
                        .build();

        return handleExceptionInternal(ex, dto, headers, status, request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        List<String> erros = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            erros.add(
                    violation.getRootBeanClass().getName()
                            + " "
                            + violation.getPropertyPath()
                            + ": "
                            + violation.getMessage());
        }

        ExceptionDto dto =
                ExceptionDto.builder()
                        .mensagem(ex.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .camposValidacao(erros)
                        .build();

        return new ResponseEntity<>(dto, new HttpHeaders(), dto.getStatus());
    }
}
