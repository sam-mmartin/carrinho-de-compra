package com.example.models.pessoa.factory;

import java.time.LocalDate;

import com.example.models.VOs.CNPJ;
import com.example.models.VOs.Email;
import com.example.models.pessoa.PessoaJuridica;
import com.example.models.pessoa.interfaces.generics.FabricaPessoa;

public class FabricaPJ implements FabricaPessoa<PessoaJuridica> {

   private PessoaJuridica pessoaJuridica;

   public FabricaPJ comCnpjNomeEmail(String cnpj, String nomeFantasia, String razaoSocial, String email,
         String dataCriacao) {
      this.pessoaJuridica = new PessoaJuridica(new CNPJ(cnpj),
            nomeFantasia,
            razaoSocial,
            new Email(email),
            LocalDate.parse(dataCriacao));
      return this;
   }

   public FabricaPJ comTelefone(String ddd, String numero) {
      this.pessoaJuridica.adicionaTelefone(ddd, numero);
      return this;
   }

   @Override
   public PessoaJuridica criar() {
      return this.pessoaJuridica;
   }
}
