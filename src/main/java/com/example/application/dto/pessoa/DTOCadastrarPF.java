package com.example.application.dto.pessoa;

import com.example.models.VOs.CPF;
import com.example.models.VOs.Email;
import com.example.models.pessoa.PessoaFisica;

public class DTOCadastrarPF {

   private String nome;
   private String cpf;
   private String email;

   public DTOCadastrarPF(String nome, String cpf, String email) {
      this.nome = nome;
      this.cpf = cpf;
      this.email = email;
   }

   public PessoaFisica criarPessoa() {
      return new PessoaFisica(
            new CPF(cpf),
            nome,
            new Email(email));
   }
}
