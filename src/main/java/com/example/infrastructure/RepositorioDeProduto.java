package com.example.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.models.exceptions.ProdutoNaoEncontrado;
import com.example.models.produto.Produto;
import com.example.models.produto.interfaces.RepositorioProduto;

public class RepositorioDeProduto implements RepositorioProduto {

   private List<Produto> produtos = new ArrayList<>();

   @Override
   public Produto buscarPorDescricao(String descricao) {
      return produtos.stream()
            .filter(p -> p.getDescricao().equals(descricao))
            .findFirst()
            .orElseThrow(() -> new ProdutoNaoEncontrado(descricao));
   }

   @Override
   public List<Produto> buscarPorMarca(String marca) {
      return produtos.stream()
            .filter(p -> p.getMarca().getNome().equals(marca))
            .collect(Collectors.toList());
   }

   @Override
   public List<Produto> buscarPorModelo(String modelo) {
      return produtos.stream()
            .filter(p -> p.getModelo().getDescricao().equals(modelo))
            .collect(Collectors.toList());
   }

   @Override
   public void cadastrar(Produto objeto) {
      produtos.add(objeto);
   }

   @Override
   public List<Produto> listarTodos() {
      return produtos;
   }

   @Override
   public Produto buscarPorId(String uuid) {
      return produtos.stream()
            .filter(p -> p.getUuid().equals(uuid))
            .findFirst()
            .orElseThrow(() -> new ProdutoNaoEncontrado(uuid));
   }

}
