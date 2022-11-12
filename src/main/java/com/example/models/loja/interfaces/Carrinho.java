package com.example.models.loja.interfaces;

import java.math.BigDecimal;
import java.util.Map;

import com.example.models.loja.ItemPedido;

public interface Carrinho {

   public BigDecimal getValorTotal();

   public void adicionarItem(ItemPedido item);

   public void atualizarItem(String uuid, int quantidade, BigDecimal valor);

   public void removerItem(String uuid);

   public void alterarQuantidadeDeUmItem(String uuid, int quantidade);

   public Map<String, ItemPedido> listarTodos();

   public int quantidadeDeItens();

   public void removerTudo();

}
