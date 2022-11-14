package com.example.application.services;

import java.util.List;

import com.example.application.interfaces.IServicePedido;
import com.example.models.loja.interfaces.RepositorioPedido;
import com.example.models.loja.pedido.Pedido;

public class ServicePedido implements IServicePedido {

   private final RepositorioPedido repositorio;

   public ServicePedido(RepositorioPedido repositorio) {
      this.repositorio = repositorio;
   }

   @Override
   public void addPedido(Pedido pedido) {
      repositorio.cadastrar(pedido);
   }

   @Override
   public List<Pedido> getAll(String idCliente) {
      return repositorio.listarTodos(idCliente);
   }

   @Override
   public Pedido getPedido(String idCliente, String idPedido) {
      return repositorio.buscarPorId(idCliente, idPedido);
   }

}
