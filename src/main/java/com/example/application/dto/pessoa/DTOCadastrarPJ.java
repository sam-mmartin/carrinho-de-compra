package com.example.application.dto.pessoa;

import java.time.LocalDate;

import com.example.models.VOs.CNPJ;
import com.example.models.VOs.Email;
import com.example.models.pessoa.PessoaJuridica;

public class DTOCadastrarPJ {

   private String cnpj;
   private String nomeFantasia;
   private String razaoSocial;
   private String email;
   private String dataCriacao;

   public DTOCadastrarPJ(String cnpj, String nomeFantasia, String razaoSocial, String email, String dataCriacao) {
      this.cnpj = cnpj;
      this.nomeFantasia = nomeFantasia;
      this.razaoSocial = razaoSocial;
      this.email = email;
      this.dataCriacao = dataCriacao;
   }

   public PessoaJuridica criarPessoa() {
      return new PessoaJuridica(
            new CNPJ(cnpj),
            nomeFantasia,
            razaoSocial,
            new Email(email),
            LocalDate.parse(dataCriacao));
   }
}
