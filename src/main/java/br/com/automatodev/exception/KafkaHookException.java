package br.com.automatodev.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class KafkaHookException extends RuntimeException {

    private HttpStatus status;

    public KafkaHookException(String message) {
        super(message);
    }

    public KafkaHookException(String messsage, HttpStatus status) {
        super(messsage);
        this.status = status;
    }
}
