package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String complemento;
    private String numero;

    public Endereco(DadosEndedeco dadosEndedeco) {
        this.logradouro = dadosEndedeco.logradouro();
        this.bairro = dadosEndedeco.bairro();
        this.cep = dadosEndedeco.cep();
        this.cidade = dadosEndedeco.cidade();
        this.uf = dadosEndedeco.uf();
        this.complemento = dadosEndedeco.complemento();
        this.numero = dadosEndedeco.numero();
    }

    public void atualizarEndereco(DadosEndedeco dadosEndedeco) {
        if (dadosEndedeco.logradouro() != null) {
            this.logradouro = dadosEndedeco.logradouro();
        }
        if (dadosEndedeco.bairro() != null) {
            this.bairro = dadosEndedeco.bairro();
        }
        if (dadosEndedeco.cep() != null) {
            this.cep = dadosEndedeco.cep();
        }
        if (dadosEndedeco.cidade() != null) {
            this.cidade = dadosEndedeco.cidade();
        }
        if (dadosEndedeco.uf() != null) {
            this.uf = dadosEndedeco.uf();
        }
        if (dadosEndedeco.complemento() != null) {
            this.complemento = dadosEndedeco.complemento();
        }
        if (dadosEndedeco.numero() != null) {
            this.numero = dadosEndedeco.numero();
        }
    }
}
