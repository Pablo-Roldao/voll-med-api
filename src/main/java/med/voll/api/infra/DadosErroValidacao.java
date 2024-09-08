package med.voll.api.infra;

import org.springframework.validation.FieldError;

public record DadosErroValidacao(String campo, String menssagem) {
    public DadosErroValidacao (FieldError erro) {
        this(erro.getField(), erro.getDefaultMessage());
    }
}
