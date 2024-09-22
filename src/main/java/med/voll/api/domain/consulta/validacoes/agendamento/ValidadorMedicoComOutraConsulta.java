package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.records.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsulta implements ValidadorAgendamentoConsulta {

    private final ConsultaRepository repository;

    public ValidadorMedicoComOutraConsulta(ConsultaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Médico(a) já possui outra consulta agendada nesse mesmo horário!");
        }
    }
}
