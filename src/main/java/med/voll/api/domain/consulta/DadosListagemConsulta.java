package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosListagemConsulta(
        Long idMedico,
        Long idPaciente,
        LocalDateTime data
) {
    public DadosListagemConsulta(Consulta consulta) {
        this(consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
