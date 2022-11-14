package com.example.models.VOs;

public class IdPessoa {

   private int tipo;
   private CPF cpf;
   private CNPJ cnpj;

   public int getTipo() {
      return tipo;
   }

   public void setTipo(int tipo) {
      this.tipo = tipo;
   }

   public CPF getCpf() {
      return cpf;
   }

   public void setCpf(CPF cpf) {
      this.cpf = cpf;
   }

   public CNPJ getCnpj() {
      return cnpj;
   }

   public void setCnpj(CNPJ cnpj) {
      this.cnpj = cnpj;
   }

}
