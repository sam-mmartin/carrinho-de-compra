package com.example.application.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.application.interfaces.IServiceCart;
import com.example.application.interfaces.IServiceFrete;
import com.example.models.loja.ItemPedido;
import com.example.models.loja.interfaces.Carrinho;

public class ServiceCart implements IServiceCart {

   private final Carrinho carrinho;
   private final IServiceFrete correios;
   private BigDecimal totalFrete;

   public ServiceCart(Carrinho carrinho, IServiceFrete correios) {
      this.carrinho = carrinho;
      this.correios = correios;
   }

   @Override
   public void addItem(ItemPedido item) {
      carrinho.adicionarItem(item);
   }

   @Override
   public void removeItem(String uuid) {
      carrinho.removerItem(uuid);
   }

   @Override
   public void updateItem(String uuid, int quantidade) {
      carrinho.alterarQuantidadeDeUmItem(uuid, quantidade);
   }

   @Override
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

   @Override
   public List<ItemPedido> getAllItems() {
      List<ItemPedido> itens = new ArrayList<>();

      carrinho.listarTodos().values().forEach(v -> itens.add(v));

      return itens;
   }

   @Override
   public int getQuantityOfItems() {
      return carrinho.quantidadeDeItens();
   }

   @Override
   public void removeAll() {
      carrinho.removerTudo();
   }

   @Override
   public void calculateTotalShippingCost() {
      totalFrete = new BigDecimal("0.00");

      carrinho.listarTodos().forEach((k, v) -> {
         BigDecimal frete = correios.calcularValorDoFrete(v.getProduto().getUuid(), v.getQuantidade());
         totalFrete = totalFrete.add(frete);
      });
   }

   @Override
   public BigDecimal totalPurchaseAmount() {
      calculateTotalShippingCost();
      return totalFrete.add(carrinho.getValorTotal());
   }

}
