package com.example.models.pessoa.factory;

import com.example.models.VOs.CPF;
import com.example.models.VOs.Email;
import com.example.models.pessoa.PessoaFisica;
import com.example.models.pessoa.interfaces.FabricaPessoaFisica;

public class FabricaPF implements FabricaPessoaFisica {

   private PessoaFisica pessoaFisica;

   public FabricaPF comCpfNomeEmail(String cpf, String nome, String email) {
      this.pessoaFisica = new PessoaFisica(new CPF(cpf), nome, new Email(email));
      return this;
   }

   public FabricaPF comTelefone(String ddd, String numero) {
      this.pessoaFisica.adicionaTelefone(ddd, numero);
      return this;
   }

   public PessoaFisica criar() {
      return this.pessoaFisica;
   }
}
