package com.example.application.interfaces;

import java.math.BigDecimal;

import com.example.models.loja.ItemPedido;
import com.example.models.pessoa.Pessoa;

public interface IServiceCart {

   public Pessoa getCliente();

   public void addItem(ItemPedido item);

   public void removeItem(String uuid);

   public void updateItem(String uuid, int quantidade);

   public void getAllItems();

   public int getQuantityOfItems();

   public void removeAll();

   public void calculateTotalShippingCost();

   public BigDecimal totalPurchaseAmount();
}
