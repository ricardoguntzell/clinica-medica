package br.com.guntz.clinica.medica.api.infra.exception;

import br.com.guntz.clinica.medica.api.domain.exception.NegocioException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> tratarErro404() {
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> tratarErro405() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> tratarErro400(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Um ou mais campos estão inválidos");
        problemDetail.setType(URI.create("https://guntz.clinica-medica.com.br/erros/campos-invalidos"));

        var erros = ex.getFieldErrors().stream()
                .map(ErrosDeValidacao::new)
                .toList();

        problemDetail.setProperty("campos", erros);

        return ResponseEntity.badRequest().body(problemDetail);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> tratarErroDeNegocio400(NegocioException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(ex.getMessage());
        problemDetail.setType(URI.create("https://guntz.clinica-medica.com.br/erros/negocio"));

        return ResponseEntity.badRequest().body(problemDetail);
    }

    private record ErrosDeValidacao(String Campo, String mensagem) {

        public ErrosDeValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}
