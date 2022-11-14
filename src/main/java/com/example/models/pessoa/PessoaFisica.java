package com.example.models.pessoa;

import com.example.models.VOs.CPF;
import com.example.models.VOs.Email;
import com.example.models.VOs.Telefone;
import com.example.models.pessoa.interfaces.PessoaVO;

public class PessoaFisica extends Pessoa implements PessoaVO {

   private CPF cpf;

   public PessoaFisica(CPF cpf, String nome, Email email) {
      this.cpf = cpf;
      super.id = cpf.getNumero();
      super.nome = nome;
      super.email = email;
   }

   @Override
   public void adicionaTelefone(String ddd, String numero) {
      super.telefones.add(new Telefone(ddd, numero));
   }

   public CPF getCpf() {
      return cpf;
   }

   @Override
   public String toString() {
      String data = "-----------------------------------------------"
            + "\n| Nome: " + super.nome
            + "\n| CPF: " + this.cpf.getNumero()
            + "\n| Email: " + super.email.getEndereco();

      if (super.telefones != null && super.telefones.size() > 0) {
         for (Telefone telefone : telefones) {
            data += "\n| Fone: " + telefone;
         }
      }

      data += "\n-----------------------------------------------";

      return data;
   }
}
