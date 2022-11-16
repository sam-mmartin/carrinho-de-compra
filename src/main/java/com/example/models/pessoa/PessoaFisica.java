package com.example.models.pessoa;

import java.util.List;

import com.example.models.VOs.CPF;
import com.example.models.VOs.Email;
import com.example.models.VOs.Telefone;
import com.example.models.pessoa.interfaces.PessoaVO;

public class PessoaFisica implements PessoaVO {

   private String nome;
   private Email email;
   private CPF cpf;
   private List<Telefone> telefones;

   public PessoaFisica(CPF cpf, String nome, Email email) {
      this.cpf = cpf;
      this.nome = nome;
      this.email = email;
   }

   @Override
   public void adicionaTelefone(String ddd, String numero) {
      this.telefones.add(new Telefone(ddd, numero));
   }

   public CPF getCpf() {
      return cpf;
   }

   @Override
   public String toString() {
      String data = "-----------------------------------------------"
            + "\n| Nome: " + this.nome
            + "\n| CPF: " + this.cpf.getNumero()
            + "\n| Email: " + this.email.getEndereco();

      if (this.telefones != null && this.telefones.size() > 0) {
         for (Telefone telefone : telefones) {
            data += "\n| Fone: " + telefone;
         }
      }

      data += "\n-----------------------------------------------";

      return data;
   }
}
