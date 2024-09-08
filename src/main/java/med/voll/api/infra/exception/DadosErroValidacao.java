package med.voll.api.infra.exception;

import org.springframework.validation.FieldError;

public record DadosErroValidacao(String campo, String menssagem) {
    public DadosErroValidacao (FieldError erro) {
        this(erro.getField(), erro.getDefaultMessage());
    }
}
