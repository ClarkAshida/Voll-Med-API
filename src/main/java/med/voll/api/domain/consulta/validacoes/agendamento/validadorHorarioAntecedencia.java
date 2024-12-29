package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

@Component
public class validadorHorarioAntecedencia implements ValidadorAgendamentoConsultas {

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        var dataConsulta = dadosAgendamentoConsulta.data();
        var agora = java.time.LocalDateTime.now();
        var diferencaMinutos = java.time.Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaMinutos < 30) {
            throw new RuntimeException("Consulta deve ser agendada com no mínimo 30 minutos de antecedência");
        }
    }
}
