package br.com.rvm.poc.pessoaDataDogServiceApp.controller;

import br.com.rvm.poc.pessoaDataDogServiceApp.model.Pessoa;
import br.com.rvm.poc.pessoaDataDogServiceApp.service.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Pessoa> criar(@RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(service.criar(pessoa));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable String cpf,
                                            @RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(service.atualizar(cpf, pessoa));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletar(@PathVariable String cpf) {
        service.deletar(cpf);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Pessoa> buscar(@PathVariable String cpf) {
        Pessoa pessoa = service.buscarPorCpf(cpf);
        if (pessoa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pessoa);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }
}
