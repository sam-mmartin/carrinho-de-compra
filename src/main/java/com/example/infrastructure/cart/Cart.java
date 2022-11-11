package com.example.infrastructure.cart;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.example.models.exceptions.ProdutoNaoEncontrado;
import com.example.models.loja.ItemPedido;
import com.example.models.loja.interfaces.Carrinho;
import com.example.models.pessoa.Pessoa;

public class Cart implements Carrinho {

   private Pessoa cliente;
   private Map<String, ItemPedido> itens;
   private BigDecimal valorTotal;

   public Cart(Pessoa cliente) {
      this.cliente = cliente;
      this.itens = new HashMap<>();
      this.valorTotal = new BigDecimal("0");
   }

   @Override
   public Pessoa getCliente() {
      return cliente;
   }

   @Override
   public BigDecimal getValorTotal() {
      return valorTotal;
   }

   @Override
   public Map<String, ItemPedido> listarTodos() {
      return itens;
   }

   @Override
   public void adicionarItem(ItemPedido pedidoProduto) {
      if (verificaProdutoDuplicado(pedidoProduto)) {

         itens.forEach(null);

         ItemPedido antigo = itens.get(pedidoProduto.getProduto().getUuid());
         int quantidade = pedidoProduto.getQuantidade() + antigo.getQuantidade();
         BigDecimal valorTotal = antigo.getValorTotal().add(pedidoProduto.getValorTotal());

         pedidoProduto.setQuantidade(quantidade);
         pedidoProduto.setValorTotal(valorTotal);
         itens.replace(antigo.getProduto().getUuid(), antigo, pedidoProduto);

      } else {
         itens.put(pedidoProduto.getProduto().getUuid(), pedidoProduto);
      }

      valorTotal = valorTotal.add(pedidoProduto.getValorTotal());
   }

   @Override
   public void removerItem(String uuid) {
      if (itens.containsKey(uuid)) {
         valorTotal = valorTotal.subtract(itens.get(uuid).getValorTotal());
         itens.remove(uuid);
      }
   }

   public boolean verificaProdutoDuplicado(ItemPedido pedidoProduto) {
      return itens.size() > 0 && itens.containsKey(pedidoProduto.getProduto().getUuid());
   }

   @Override
   public void alterarQuantidadeDeUmItem(String uuid, int quantidade) {
      if (itens.containsKey(uuid)) {

         ItemPedido item = itens.get(uuid);
         BigDecimal novoValor = item.getProduto().getValor().multiply(new BigDecimal(quantidade)).setScale(2);

         valorTotal = valorTotal.subtract(item.getValorTotal());
         valorTotal = valorTotal.add(novoValor);

         item.setQuantidade(quantidade);
         item.setValorTotal(novoValor);
         itens.replace(uuid, item);
      } else {
         throw new ProdutoNaoEncontrado(uuid);
      }
   }

   @Override
   public int quantidadeDeItens() {
      return itens.size();
   }

   @Override
   public void removerTudo() {
      itens.clear();
      valorTotal = new BigDecimal("0.00");
   }

}
