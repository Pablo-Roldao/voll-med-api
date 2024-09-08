package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        Long id,
        String nome,
        String email,
        DadosEndereco endereco
) {
}
