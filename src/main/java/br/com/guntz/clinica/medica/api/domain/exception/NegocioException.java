package br.com.guntz.clinica.medica.api.domain.exception;

public class NegocioException extends RuntimeException {

    public NegocioException() {
    }

    public NegocioException(String message) {
        super(message);
    }
}
