package com.example.models.frete.interfaces;

import com.example.models.frete.Frete;

public interface RepositorioFrete {

   public void adicionar(Frete frete);

   public Frete buscar(String codigoProduto);

   public void update(Frete frete);
}
