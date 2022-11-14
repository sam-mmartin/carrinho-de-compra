package com.example.application.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.models.loja.ItemPedido;
import com.example.models.loja.interfaces.Carrinho;

public class ServiceCart {

   private final Carrinho carrinho;
   private final ServiceFrete correios;
   private BigDecimal totalFrete;

   public ServiceCart(Carrinho carrinho, ServiceFrete correios) {
      this.carrinho = carrinho;
      this.correios = correios;
   }

   public void executa(ItemPedido item) {
      carrinho.adicionarItem(item);
   }

   public void removeItem(String uuid) {
      carrinho.removerItem(uuid);
   }

   public void updateItem(String uuid, int quantidade) {
      carrinho.alterarQuantidadeDeUmItem(uuid, quantidade);
   }

   public void viewAllItems() {
      carrinho.listarTodos().forEach((k, v) -> {
         BigDecimal frete = correios.calcularValorDoFrete(v.getProduto().getUuid(), v.getQuantidade());
         System.out.println(k + ": " + v);
         if (frete.intValue() == 0) {
            System.out.println("Frete: grátis");
         } else {
            System.out.println("Frete: R$" + frete);
         }
      });
   }

   public List<ItemPedido> getAllItems() {
      List<ItemPedido> itens = new ArrayList<>();

      carrinho.listarTodos().values().forEach(v -> itens.add(v));

      return itens;
   }

   public int getQuantityOfItems() {
      return carrinho.quantidadeDeItens();
   }

   public void removeAll() {
      carrinho.removerTudo();
   }

   public void calculateTotalShippingCost() {
      totalFrete = new BigDecimal("0.00");

      carrinho.listarTodos().forEach((k, v) -> {
         BigDecimal frete = correios.calcularValorDoFrete(v.getProduto().getUuid(), v.getQuantidade());
         totalFrete = totalFrete.add(frete);
      });
   }

   public BigDecimal totalPurchaseAmount() {
      calculateTotalShippingCost();
      return totalFrete.add(carrinho.getValorTotal());
   }

}
