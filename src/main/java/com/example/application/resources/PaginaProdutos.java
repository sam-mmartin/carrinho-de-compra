package com.example.application.resources;

import java.util.HashMap;
import java.util.Map;

import com.example.application.dto.pedido.ItemPedidoDTO;
import com.example.application.services.ServiceProduto;
import com.example.models.produto.Produto;

public class PaginaProdutos {

   private ServiceProduto service;
   private Map<Integer, String> mapeamento = new HashMap<>();
   private int index;

   public PaginaProdutos(ServiceProduto service) {
      this.service = service;
   }

   public void listarTodos() {
      index = 0;

      service.getAll().forEach(p -> {
         index++;
         System.out.println(index + " " + p.toString());
      });
   }

   public ItemPedidoDTO selecionarProduto(int index, int quantidade) {
      String uuid = mapeamento.get(index);
      Produto produto = service.getById(uuid);

      return new ItemPedidoDTO(produto, quantidade);
   }

   public void seed() {
      index = 0;

      service.getAll().forEach(p -> {
         index++;
         mapeamento.put(index, p.getUuid());
      });
   }

}
