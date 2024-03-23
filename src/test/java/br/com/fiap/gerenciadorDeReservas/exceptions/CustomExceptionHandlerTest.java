package br.com.fiap.gerenciadorDeReservas.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomExceptionHandlerTest {
    CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();

    @Test
    void testHandleValidationExceptions() {
        // Criar um erro de campo simulado
        FieldError fieldError = new FieldError("objeto", "campo", "mensagem de erro");

        // Criar um BindingResult simulado que contém o erro de campo
        BindingResult bindingResult = new DirectFieldBindingResult(null, "objeto");
        bindingResult.addError(fieldError);

        // Criar uma instância de MethodArgumentNotValidException com o BindingResult simulado
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

        // Criar uma instância de CustomExceptionHandler
        CustomExceptionHandler exceptionHandler = new CustomExceptionHandler();

        // Chamar o método handleValidationExceptions com a exceção simulada
        ResponseEntity<CustomErrorResponse> responseEntity = exceptionHandler.handleValidationExceptions(ex);

        // Verificar se o status code é BAD_REQUEST
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        // Verificar se a mensagem de erro na resposta é a mesma que a mensagem de erro no FieldError
        assertEquals("mensagem de erro", responseEntity.getBody().getMensagem());

        // Verificar se o campo na resposta é o mesmo que o campo no FieldError
        assertEquals("campo", responseEntity.getBody().getCampo());

        // Verificar se o timestamp na resposta não é nulo
        LocalDateTime timestamp = responseEntity.getBody().getTimestamp();
        assertEquals(LocalDateTime.class, timestamp.getClass()); // Verificar se o tipo é LocalDateTime
    }
}
