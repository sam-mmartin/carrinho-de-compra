package com.example.models.VOs;

public class Email {

   private String endereco;

   public Email(String endereco) {
      if (endereco == null || !endereco.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9._]+\\.[a-zA-Z]{2,}$")) {
         throw new IllegalArgumentException("Endereço de e-mail inválido");
      }

      this.endereco = endereco;
   }

   public String getEndereco() {
      return endereco;
   }
}
