package com.example.infrastructure;

import java.util.ArrayList;
import java.util.List;

import com.example.models.exceptions.FreteNaoEncontrado;
import com.example.models.frete.Frete;
import com.example.models.frete.interfaces.RepositorioFrete;

public class RepositorioDeFrete implements RepositorioFrete {

   private List<Frete> fretes = new ArrayList<>();

   @Override
   public void adicionar(Frete frete) {
      fretes.add(frete);
   }

   @Override
   public Frete buscar(String codigoProduto) {
      return fretes.stream()
            .filter(f -> f.getCodigoProduto().equals(codigoProduto))
            .findFirst()
            .orElseThrow(() -> new FreteNaoEncontrado());
   }

   @Override
   public void update(Frete frete) {
      fretes.removeIf(f -> f.getCodigoProduto().equals(frete.getCodigoProduto()));
      fretes.add(frete);
   }

}
