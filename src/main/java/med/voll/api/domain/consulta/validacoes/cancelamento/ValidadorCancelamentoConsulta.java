package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.consulta.records.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoConsulta {

    void validar(DadosCancelamentoConsulta dados);

}
