package br.com.rvm.poc.pessoaDataDogServiceApp.repository;

import br.com.rvm.poc.pessoaDataDogServiceApp.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, String> {
}