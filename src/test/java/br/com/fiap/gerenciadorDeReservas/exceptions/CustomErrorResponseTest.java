package br.com.fiap.gerenciadorDeReservas.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomErrorResponseTest {

    @Test
    void testHandleValidationExceptions() {
        // Simular um erro de validação
        FieldError fieldError = new FieldError("objeto", "campo", "mensagem de erro");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldError()).thenReturn(fieldError);
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
