package com.example.application.interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.example.models.loja.ItemPedido;

public interface IServiceCart {

   public void addItem(ItemPedido item);

   public void removeItem(String uuid);

   public void updateItem(String uuid, int quantidade);

   public void viewAllItems();

   public List<ItemPedido> getAllItems();

   public int getQuantityOfItems();

   public void removeAll();

   public void calculateTotalShippingCost();

   public BigDecimal totalPurchaseAmount();
}
