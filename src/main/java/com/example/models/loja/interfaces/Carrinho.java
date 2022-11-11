package com.example.models.loja.interfaces;

import java.math.BigDecimal;
import java.util.Map;

import com.example.models.loja.ItemPedido;
import com.example.models.pessoa.Pessoa;

public interface Carrinho {

   public Pessoa getCliente();

   public BigDecimal getValorTotal();

   public void adicionarItem(ItemPedido item);

   public void removerItem(String uuid);

   public void alterarQuantidadeDeUmItem(String uuid, int quantidade);

   public Map<String, ItemPedido> listarTodos();

   public int quantidadeDeItens();

   public void removerTudo();

}
