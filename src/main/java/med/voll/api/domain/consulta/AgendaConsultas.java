package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoConsultas;
import med.voll.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidadorAgendamentoConsultas> validadores;
    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendarConsulta(DadosAgendamentoConsulta dadosAgendamento) {
        if (!pacienteRepository.existsById(dadosAgendamento.idPaciente())) {
            throw new IllegalArgumentException("Paciente não encontrado");
        }
        if (dadosAgendamento.idMedico() != null && !medicoRepository.existsById(dadosAgendamento.idMedico())) {
            throw new IllegalArgumentException("Médico não encontrado");
        }

        validadores.forEach(validador -> validador.validar(dadosAgendamento));

        var paciente = pacienteRepository.getReferenceById(dadosAgendamento.idPaciente());
        var medico = escolherMedico(dadosAgendamento);

        if (medico == null) {
            throw new RuntimeException("Não foi possível encontrar um médico disponível para a data informada");
        }

        var consulta = new Consulta(null, medico, paciente, dadosAgendamento.data(), null);
        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);
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

    public void cancelarConsulta(DadosCancelamentoConsulta dadosCancelamento) {
        if (!consultaRepository.existsById(dadosCancelamento.idConsulta())) {
            throw new RuntimeException("Id da consulta informado não existe!");
        }

        validadoresCancelamento.forEach(validador -> validador.validar(dadosCancelamento));

        var consulta = consultaRepository.getReferenceById(dadosCancelamento.idConsulta());
        consulta.cancelar(dadosCancelamento.motivo());
    }
}
