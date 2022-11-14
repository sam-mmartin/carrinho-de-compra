package com.example.models.exceptions;

public class PedidoNaoEncontrado extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public PedidoNaoEncontrado(String idPedido) {
      super("Pedido n°: " + idPedido + "não encontrado");
   }
}
