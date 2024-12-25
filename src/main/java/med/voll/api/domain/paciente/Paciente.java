package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;
    private boolean ativo;

    public Paciente(@Valid DadosCadastroPaciente dadosPaciente) {
        this.nome = dadosPaciente.nome();
        this.email = dadosPaciente.email();
        this.telefone = dadosPaciente.telefone();
        this.cpf = dadosPaciente.cpf();
        this.endereco = new Endereco(dadosPaciente.endereco());
        this.ativo = true;
    }

    public void atualizarDadosPaciente(@Valid DadosAtualizacaoPaciente dadosAtualizacao) {
        if (dadosAtualizacao.nome() != null) {
            this.nome = dadosAtualizacao.nome();
        }
        if (dadosAtualizacao.email() != null) {
            this.email = dadosAtualizacao.email();
        }
        if (dadosAtualizacao.telefone() != null) {
            this.telefone = dadosAtualizacao.telefone();
        }
        if (dadosAtualizacao.endereco() != null) {
            this.endereco.atualizarEndereco(dadosAtualizacao.endereco());
        }
        if (dadosAtualizacao.ativo() != null) {
            this.ativo = dadosAtualizacao.ativo();
        }
    }

    public void desativarPaciente() {
        this.ativo = false;
    }
}
