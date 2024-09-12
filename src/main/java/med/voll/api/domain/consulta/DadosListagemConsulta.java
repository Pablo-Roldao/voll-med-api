package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.DadosListagemMedico;
import med.voll.api.domain.paciente.DadosListagemPaciente;

public record DadosListagemConsulta(
        Long id,
        DadosListagemMedico dadosListagemMedico,
        DadosListagemPaciente dadosListagemPaciente,
        MotivoCancelamento motivoCancelamento
) {

    public DadosListagemConsulta  (Consulta consulta){
        this(consulta.getId(), new DadosListagemMedico(consulta.getMedico()), new DadosListagemPaciente(consulta.getPaciente()), consulta.getMotivoCancelamento());

    }
}
