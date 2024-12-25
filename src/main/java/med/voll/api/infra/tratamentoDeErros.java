package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class tratamentoDeErros {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException exception) {
        var erros = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(dadosErrosValidacao::new).toList());
    }
    private record dadosErrosValidacao(String campo, String mensagem) {
        public dadosErrosValidacao(org.springframework.validation.FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
