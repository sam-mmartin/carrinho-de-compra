package com.example.models.VOs;

public class Telefone {

   private String ddd;
   private String numero;

   public Telefone(String ddd, String numero) {
      this.ddd = ddd;
      this.numero = numero;
   }

   public String getNumero() {
      return "(" + ddd + ")" + numero;
   }
}
