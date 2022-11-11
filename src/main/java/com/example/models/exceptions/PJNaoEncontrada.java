package com.example.models.exceptions;

import com.example.models.VOs.CNPJ;

public class PJNaoEncontrada extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public PJNaoEncontrada(CNPJ cnpj) {
      super("Pessoa com CNPJ: " + cnpj.getNumero() + " não encontrada!");
   }
}
