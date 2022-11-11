package com.example.models.exceptions;

public class ProdutoNaoEncontrado extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public ProdutoNaoEncontrado(String descricao) {
      super("Produto: " + descricao + " não encontrado!");
   }
}
