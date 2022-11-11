package com.example.models.exceptions;

public class FreteNaoEncontrado extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public FreteNaoEncontrado() {
      super("Não foi possível calcular valor do frete");
   }
}
