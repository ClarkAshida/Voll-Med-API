package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class validadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {
        var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dadosAgendamentoConsulta.idMedico(), dadosAgendamentoConsulta.data());
        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new RuntimeException("Médico possui outra consulta no mesmo horário");
        }
    }
}
