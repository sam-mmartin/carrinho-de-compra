package com.example.application.interfaces;

import java.math.BigDecimal;

public interface IServiceFrete {

   public BigDecimal calcularValorDoFrete(String codigoProduto, int quantidade);

   public void criarFreteParaProduto(String codigoProduto, boolean validador);
}