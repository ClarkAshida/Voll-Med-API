package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class validadorPacienteInativo implements ValidadorAgendamentoConsultas {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {

        var paciente = pacienteRepository.findAtivoById(dadosAgendamentoConsulta.idPaciente());
        if (!paciente) {
            throw new RuntimeException("A consulta não pode ser agendada com um paciente inativo");
        }
    }
}
