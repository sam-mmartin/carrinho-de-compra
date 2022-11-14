package com.example.infrastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.models.exceptions.PedidoNaoEncontrado;
import com.example.models.loja.interfaces.RepositorioPedido;
import com.example.models.loja.pedido.Pedido;

public class RepositorioDePedido implements RepositorioPedido {

   private Map<String, List<Pedido>> repositorio = new HashMap<>();

   @Override
   public void cadastrar(Pedido objeto) {
      List<Pedido> pedidos = new ArrayList<>();

      if (repositorio.containsKey(objeto.getIdCliente())) {
         pedidos = repositorio.get(objeto.getIdCliente());
         pedidos.add(objeto);

         repositorio.replace(objeto.getIdCliente(), pedidos);
      } else {
         pedidos.add(objeto);
         repositorio.put(objeto.getIdCliente(), pedidos);
      }

   }

   @Override
   public List<Pedido> listarTodos(String idCliente) {
      return repositorio.get(idCliente);
   }

   @Override
   public Pedido buscarPorId(String idCliente, String idPedido) {
      List<Pedido> pedidos = repositorio.get(idCliente);
      Pedido pedido = pedidos.stream().filter(p -> p.getIdCliente().equals(idCliente))
            .findFirst().orElseThrow(() -> new PedidoNaoEncontrado(idPedido));

      return pedido;
   }

}
