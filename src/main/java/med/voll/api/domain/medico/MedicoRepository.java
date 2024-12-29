package med.voll.api.domain.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query("SELECT m FROM Medico m WHERE m.ativo = true AND m.especialidade = :especialidade AND :data NOT IN (SELECT c.data FROM Consulta c WHERE c.medico = m) ORDER BY FUNCTION('RAND') LIMIT 1")
    Medico escolherMedicoAleatorioComDataDisponivel(Especialidade especialidade, @NotNull @Future LocalDateTime data);

    @Query("SELECT m.ativo FROM Medico m WHERE m.id = :id")
    Boolean findAtivoById(Long id);
}
