package med.voll.api.domain.consulta;

import aj.org.objectweb.asm.commons.Remapper;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AgendaConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendarConsulta(DadosAgendamentoConsulta dadosAgendamento) {
        if (!pacienteRepository.existsById(dadosAgendamento.idPaciente())) {
            throw new IllegalArgumentException("Paciente não encontrado");
        }
        if (dadosAgendamento.idMedico() != null && !medicoRepository.existsById(dadosAgendamento.idMedico())) {
            throw new IllegalArgumentException("Médico não encontrado");
        }


        var paciente = pacienteRepository.getReferenceById(dadosAgendamento.idPaciente());
        var medico = escolherMedico(dadosAgendamento);
        var consulta = new Consulta(null, medico, paciente, dadosAgendamento.data(), null);
        consultaRepository.save(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dadosAgendamento) {
        if (dadosAgendamento.idMedico() != null) {
            return medicoRepository.getReferenceById(dadosAgendamento.idMedico());
        }
        if (dadosAgendamento.especialidade() != null) {
            throw new RuntimeException("Especialidade é obrigatória quando não se informa o médico");
        }
        return medicoRepository.escolherMedicoAleatorioComDataDisponivel(dadosAgendamento.especialidade(), dadosAgendamento.data());
    }

    public void cancelarConsulta(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new RuntimeException("Id da consulta informado não existe!");
        }

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
}