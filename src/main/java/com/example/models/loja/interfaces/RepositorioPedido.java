package com.example.models.loja.interfaces;

import java.util.List;

import com.example.models.loja.pedido.Pedido;

public interface RepositorioPedido {

   public void cadastrar(Pedido objeto);

   public List<Pedido> listarTodos(String idCliente);

   public Pedido buscarPorId(String idCliente, String idPedido);
}
