package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosRemarcacaoConsulta(
        @NotNull
        Long id,
        LocalDateTime data
) {
}
