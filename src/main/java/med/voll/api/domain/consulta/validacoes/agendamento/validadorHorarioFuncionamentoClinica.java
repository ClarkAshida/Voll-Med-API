package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class validadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsultas {

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        var dataConsulta = dadosAgendamentoConsulta.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var sabado = dataConsulta.getDayOfWeek().equals(DayOfWeek.SATURDAY);
        var antesDaAberturaClinica = dataConsulta.getHour() < 7;
        var depoisDoFechamentoClinica = dataConsulta.getHour() > 18;

        if (domingo || sabado || antesDaAberturaClinica || depoisDoFechamentoClinica) {
            throw new RuntimeException("Horário de funcionamento da clínica: Segunda a Sexta das 7h às 18h");
        }
    }
}
