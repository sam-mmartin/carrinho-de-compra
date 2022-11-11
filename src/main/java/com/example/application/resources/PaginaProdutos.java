package com.example.application.resources;

import java.util.HashMap;
import java.util.Map;

import com.example.application.dto.pedido.ItemPedidoDTO;
import com.example.application.interfaces.IServiceProduto;
import com.example.application.interfaces.Page;
import com.example.models.produto.Produto;

public class PaginaProdutos implements Page {

   private IServiceProduto service;
   private Map<Integer, String> mapeamento = new HashMap<>();
   private int index;

   public PaginaProdutos(IServiceProduto service) {
      this.service = service;
   }

   @Override
   public void listarTodos() {
      index = 0;

      service.getAll().forEach(p -> {
         index++;
         System.out.println(index + " " + p.toString());
      });
   }

   @Override
   public ItemPedidoDTO selecionarProduto(int index, int quantidade) {
      String uuid = mapeamento.get(index);
      Produto produto = service.getById(uuid);

      return new ItemPedidoDTO(produto, quantidade);
   }

   @Override
   public void seed() {
      index = 0;

      service.getAll().forEach(p -> {
         index++;
         mapeamento.put(index, p.getUuid());
      });
   }

}
