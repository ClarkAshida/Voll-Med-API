package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import med.voll.api.domain.endereco.Endereco;

public record dadosDetalhamentoPaciente(Long id, String nome, String cpf, String telefone, String email, @Valid Endereco endereco, Boolean ativo) {
    public dadosDetalhamentoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getTelefone(), paciente.getEmail(), paciente.getEndereco(), paciente.isAtivo());
    }
}
