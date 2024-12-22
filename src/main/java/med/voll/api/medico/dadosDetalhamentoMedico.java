package med.voll.api.medico;

import med.voll.api.endereco.Endereco;

public record dadosDetalhamentoMedico(Long id, String nome, String crm, Especialidade especialidade, String telefone, String email, Endereco endereco, Boolean ativo) {
    public dadosDetalhamentoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getEspecialidade(), medico.getTelefone(), medico.getEmail(), medico.getEndereco(), medico.isAtivo());
    }
}
