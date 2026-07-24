package br.costa.AghataEscada.exception;


import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String erro,
        String message,
        String path,
        List<Error> errors
) {
}
