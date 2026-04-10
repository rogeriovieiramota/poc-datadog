package br.com.rvm.poc.pessoaDataDogServiceApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "pessoa")
@Data
public class Pessoa {

    @Id
    private String cpf;

    private String nome;

    private String email;
}
