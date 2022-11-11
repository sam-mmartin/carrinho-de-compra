package com.example.application.interfaces;

import com.example.application.dto.pedido.ItemPedidoDTO;

public interface Page {

   public void listarTodos();

   public ItemPedidoDTO selecionarProduto(int index, int quantidade);

   public void seed();
}
