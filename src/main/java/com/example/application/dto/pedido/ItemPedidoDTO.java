package com.example.application.dto.pedido;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.example.models.loja.ItemPedido;
import com.example.models.produto.Produto;

public class ItemPedidoDTO {

   private Produto produto;
   private int quantidade;
   private BigDecimal valorTotal;

   public ItemPedidoDTO(Produto produto, int quantidade) {
      this.produto = produto;
      this.quantidade = quantidade;
   }

   public ItemPedido criarItemPedido() {
      valorTotal = produto.getValor().multiply(new BigDecimal(quantidade)).setScale(2);
      valorTotal = valorTotal.add(produto.getTaxa().getValor()).setScale(2, RoundingMode.HALF_UP);
      return new ItemPedido(produto, quantidade, valorTotal);
   }
}
