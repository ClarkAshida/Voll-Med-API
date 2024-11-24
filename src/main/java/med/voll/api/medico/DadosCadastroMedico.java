package med.voll.api.medico;

import med.voll.api.endereco.DadosEndedeco;

public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndedeco endereco ) {
}
