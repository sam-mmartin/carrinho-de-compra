package com.example.models.loja.pedido;

import java.util.List;

import com.example.models.loja.ItemPedido;

public class Pedido {

   private String idPedido;
   private String idCliente;
   private List<ItemPedido> itens;

   public Pedido(String idPedido, String idCliente, List<ItemPedido> itens) {
      this.idPedido = idPedido;
      this.idCliente = idCliente;
      this.itens = itens;
   }

   public String getIdPedido() {
      return idPedido;
   }

   public String getIdCliente() {
      return idCliente;
   }

   public List<ItemPedido> getItens() {
      return itens;
   }

}
