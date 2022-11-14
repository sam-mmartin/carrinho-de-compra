package com.example.application.services;

import java.util.List;

import com.example.models.loja.interfaces.RepositorioPedido;
import com.example.models.loja.pedido.Pedido;

public class ServicePedido {

   private final RepositorioPedido repositorio;

   public ServicePedido(RepositorioPedido repositorio) {
      this.repositorio = repositorio;
   }

   public void executa(Pedido pedido) {
      repositorio.cadastrar(pedido);
   }

   public List<Pedido> getAll(String idCliente) {
      return repositorio.listarTodos(idCliente);
   }

   public Pedido getPedido(String idCliente, String idPedido) {
      return repositorio.buscarPorId(idCliente, idPedido);
   }

}
