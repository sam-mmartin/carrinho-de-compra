package com.example.models.VOs;

public class CNPJ {

   private String numero;

   public CNPJ(String numero) {
      boolean cnpj = numero.matches("\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}");

      if (numero == null || !cnpj) {
         throw new IllegalArgumentException("CNPJ inválido");
      }

      this.numero = numero;
   }

   public String getNumero() {
      return numero;
   }
}
