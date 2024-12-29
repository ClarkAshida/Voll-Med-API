package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class validadorMedicoInativo implements ValidadorAgendamentoConsultas {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {

        var medicoAtivo = medicoRepository.findAtivoById(dadosAgendamentoConsulta.idMedico());

        if (dadosAgendamentoConsulta.idMedico() == null) {
            return;
        }
        if (!medicoAtivo) {
            throw new RuntimeException("A consulta não pode ser agendada com um médico inativo");
        }

    }
}
