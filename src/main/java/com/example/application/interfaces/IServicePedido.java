package com.example.application.interfaces;

import java.util.List;

import com.example.models.loja.pedido.Pedido;

public interface IServicePedido {

   public void addPedido(Pedido pedido);

   public List<Pedido> getAll(String idCliente);

   public Pedido getPedido(String idCliente, String idPedido);
}
