package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    Page<Consulta> findAllByMotivoCancelamentoNull(Pageable paginacao);
    Page<Consulta> findAllByMotivoCancelamentoNotNull(Pageable paginacao);

    Boolean existsByMedicoIdAndData(Long idMedico, @NotNull @Future LocalDateTime data);

    Boolean existsByPacienteIdAndDataBetween(@NotNull Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
