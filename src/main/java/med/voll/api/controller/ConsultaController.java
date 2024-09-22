package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.records.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.records.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.records.DadosDetalhamentoConsulta;
import med.voll.api.domain.consulta.records.DadosListagemConsulta;
import med.voll.api.domain.consulta.AgendaDeConsultas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    private final AgendaDeConsultas agenda;

    public ConsultaController(AgendaDeConsultas agenda) {
        this.agenda = agenda;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsulta> agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {

        var dtoConsulta = agenda.agendar(dados);

        return ResponseEntity.ok(dtoConsulta);
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
