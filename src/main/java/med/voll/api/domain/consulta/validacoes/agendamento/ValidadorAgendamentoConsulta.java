package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.records.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoConsulta {

    void validar(DadosAgendamentoConsulta dados);

}
