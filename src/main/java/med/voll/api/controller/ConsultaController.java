package med.voll.api.controller;

import med.voll.api.domain.consulta.AgendaDeConsultas;

import jakarta.validation.Valid;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.consulta.DadosListagemConsulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    private final AgendaDeConsultas agenda;

    public ConsultaController(AgendaDeConsultas agenda) {
        this.agenda = agenda;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsulta> agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {

        var consulta = agenda.agendar(dados);

        return ResponseEntity.ok(new DadosDetalhamentoConsulta(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData()));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{ativa}")
    public ResponseEntity<Page<DadosListagemConsulta>> listar(Pageable pageable, @PathVariable boolean ativa) {
        var page = ativa
                ? agenda.pegarTodasAtivas(pageable).map(DadosListagemConsulta::new)
                : agenda.pegarTodasCanceladas(pageable).map(DadosListagemConsulta::new);

        return ResponseEntity.ok(page);
    }

}
