package br.com.rvm.poc.pessoaDataDogServiceApp.service;

import br.com.rvm.poc.pessoaDataDogServiceApp.model.Pessoa;
import br.com.rvm.poc.pessoaDataDogServiceApp.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public Pessoa criar(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    public Pessoa atualizar(String cpf, Pessoa pessoa) {
        pessoa.setCpf(cpf);
        return repository.save(pessoa);
    }

    public void deletar(String cpf) {
        repository.deleteById(cpf);
    }

    public Pessoa buscarPorCpf(String cpf) {
        return repository.findById(cpf).orElse(null);
    }

    public List<Pessoa> listarTodas() {
        return repository.findAll();
    }
}
