package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.records.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsulta {

    private final MedicoRepository repository;

    public ValidadorMedicoAtivo(MedicoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
    if (dados.idMedico() ==null) {
        return;
    }

    var medicoAtivo = repository.findAtivoById(dados.idMedico());
    if (!medicoAtivo) {
        throw new ValidacaoException("Consulta não pode ser agendada com médico inativo!");
    }
    }
}
