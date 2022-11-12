package com.example.infrastructure.cart;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.example.models.exceptions.ProdutoNaoEncontrado;
import com.example.models.loja.ItemPedido;
import com.example.models.loja.interfaces.Carrinho;

public class Cart implements Carrinho {

   private Map<String, ItemPedido> itens;
   private BigDecimal valorTotal;

   public Cart() {
      this.itens = new HashMap<>();
      this.valorTotal = new BigDecimal("0");
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
         duplicarItem(pedidoProduto);
      } else {
         itens.put(pedidoProduto.getProduto().getUuid(), pedidoProduto);
      }

      valorTotal = valorTotal.add(pedidoProduto.getValorTotal());
   }

   @Override
   public void atualizarItem(String uuid, int quantidade, BigDecimal valor) {

      ItemPedido item = itens.get(uuid);
      item.setQuantidade(quantidade);
      item.setValorTotal(valorTotal);
      itens.replace(uuid, item);
   }

   public void duplicarItem(ItemPedido item) {
      ItemPedido antigo = itens.get(item.getProduto().getUuid());
      int quantidade = item.getQuantidade() + antigo.getQuantidade();
      BigDecimal valorTotal = antigo.getValorTotal().add(item.getValorTotal());

      atualizarItem(item.getProduto().getUuid(), quantidade, valorTotal);
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
         BigDecimal novoValor = item.getProduto().getValor()
               .multiply(new BigDecimal(quantidade)).setScale(2);

         valorTotal = valorTotal.subtract(item.getValorTotal());
         valorTotal = valorTotal.add(novoValor);

         atualizarItem(uuid, quantidade, novoValor);
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
