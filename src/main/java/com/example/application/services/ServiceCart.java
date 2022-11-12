package com.example.application.services;

import java.math.BigDecimal;

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

   public void addItem(ItemPedido item) {
      carrinho.adicionarItem(item);
   }

   public void removeItem(String uuid) {
      carrinho.removerItem(uuid);
   }

   public void updateItem(String uuid, int quantidade) {
      carrinho.alterarQuantidadeDeUmItem(uuid, quantidade);
   }

   public void getAllItems() {
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
