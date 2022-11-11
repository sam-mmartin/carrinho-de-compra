package com.example.models.exceptions;

import com.example.models.VOs.CPF;

public class PFNaoEncontrada extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public PFNaoEncontrada(CPF cpf) {
      super("Pessoa com CPF: " + cpf.getNumero() + " não encontrada!");
   }
}
