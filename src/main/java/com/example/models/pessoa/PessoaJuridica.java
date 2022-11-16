package com.example.models.pessoa;

import java.time.LocalDate;
import java.util.List;

import com.example.models.VOs.CNPJ;
import com.example.models.VOs.Email;
import com.example.models.VOs.Telefone;
import com.example.models.pessoa.interfaces.PessoaVO;

public class PessoaJuridica implements PessoaVO {

   private String nomeFantasia;
   private List<Telefone> telefones;
   private Email email;
   private CNPJ cnpj;
   private String razaoSocial;
   private LocalDate dataCriacao;

   public PessoaJuridica(CNPJ cnpj, String nomeFantasia, String razaoSocial, Email email, LocalDate dataCriacao) {
      this.cnpj = cnpj;
      this.nomeFantasia = nomeFantasia;
      this.razaoSocial = razaoSocial;
      this.email = email;
      this.dataCriacao = dataCriacao;
   }

   @Override
   public void adicionaTelefone(String ddd, String numero) {
      this.telefones.add(new Telefone(ddd, numero));
   }

   public String getCnpj() {
      return cnpj.getNumero();
   }

   public String getRazaoSocial() {
      return razaoSocial;
   }

   public LocalDate getDataCriacao() {
      return dataCriacao;
   }

   @Override
   public String toString() {
      String data = "-----------------------------------------------"
            + "\n| Nome Fantasia: " + this.nomeFantasia
            + "\n| Razão Social: " + this.razaoSocial
            + "\n| CNPJ: " + this.cnpj.getNumero()
            + "\n| Data de Constituição: " + this.dataCriacao
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
