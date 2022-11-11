package com.example.models.loja;

import java.math.BigDecimal;

import com.example.models.produto.Produto;

public class ItemPedido {

   private Produto produto;
   private int quantidade;
   private BigDecimal valorTotal;

   public ItemPedido(Produto produto, int quantidade, BigDecimal valorTotal) {
      this.produto = produto;
      this.quantidade = quantidade;
      this.valorTotal = valorTotal;
   }

   public Produto getProduto() {
      return produto;
   }

   public int getQuantidade() {
      return quantidade;
   }

   public void setQuantidade(int quantidade) {
      this.quantidade = quantidade;
   }

   public BigDecimal getValorTotal() {
      return valorTotal;
   }

   public void setValorTotal(BigDecimal valorTotal) {
      this.valorTotal = valorTotal;
   }

   @Override
   public String toString() {
      return produto
            + "\nQuantidade: " + quantidade
            + "\nSubtotal: R$" + valorTotal;
   }
}
