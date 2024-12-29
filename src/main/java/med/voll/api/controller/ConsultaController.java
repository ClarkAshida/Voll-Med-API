package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaConsultas agendaConsultas;
    @Autowired
    private ConsultaRepository consultaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(@RequestBody @Valid DadosAgendamentoConsulta dadosAgendamento) {
        var dtoConsulta = agendaConsultas.agendarConsulta(dadosAgendamento);
        return ResponseEntity.ok(dtoConsulta);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemConsulta>> listarConsultas(@PageableDefault(size = 10, sort = {"data"} ) Pageable paginacao) {
        var page = consultaRepository.findAll(paginacao).map(DadosListagemConsulta::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoConsulta> detalharConsulta(@PathVariable Long id) {
        var consulta = consultaRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(new DadosDetalhamentoConsulta(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData(), consulta.getMotivoCancelamento()));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelarConsulta(@RequestBody @Valid DadosCancelamentoConsulta dadosCancelamentoConsulta) {
        agendaConsultas.cancelarConsulta(dadosCancelamentoConsulta);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity remarcarConsulta(@RequestBody @Valid DadosRemarcacaoConsulta dadosRemarcacaoConsulta) {
        var consulta = consultaRepository.getReferenceById(dadosRemarcacaoConsulta.id());
        consulta.remarcarConsulta(dadosRemarcacaoConsulta);
        return ResponseEntity.ok(new DadosDetalhamentoConsulta(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData(), consulta.getMotivoCancelamento()));
    }
}
