package med.voll.api.domain.consulta.records;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.MotivoCancelamento;
import med.voll.api.domain.medico.DadosListagemMedico;
import med.voll.api.domain.paciente.DadosListagemPaciente;

import java.time.LocalDateTime;

public record DadosListagemConsulta(
        Long id,
        DadosListagemMedico dadosListagemMedico,
        DadosListagemPaciente dadosListagemPaciente,
        LocalDateTime data,
        MotivoCancelamento motivoCancelamento
) {

    public DadosListagemConsulta  (Consulta consulta){
        this(consulta.getId(), new DadosListagemMedico(consulta.getMedico()), new DadosListagemPaciente(consulta.getPaciente()), consulta.getData(), consulta.getMotivoCancelamento());

    }
}
