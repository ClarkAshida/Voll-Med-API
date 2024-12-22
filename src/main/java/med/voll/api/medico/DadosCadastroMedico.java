package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;

public record DadosCadastroMedico(
    @NotBlank
    String nome,
    @NotBlank
    @Email
    String email,
    @NotBlank
    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter de 10 a 11 dígitos.")
    String telefone,
    @NotBlank
    @Pattern(regexp = "\\d{4,6}", message = "O CRM deve conter de 4 a 6 dígitos.")
    String crm,
    @NotNull
    Especialidade especialidade,
    @NotNull
    @Valid
    DadosEndereco endereco
) {
}
