package com.example.application.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import com.example.application.interfaces.IServiceFrete;
import com.example.models.frete.Frete;
import com.example.models.frete.interfaces.RepositorioFrete;

public class ServiceFrete implements IServiceFrete {

   private final BigDecimal desconto;
   private final RepositorioFrete repositorio;

   public ServiceFrete(RepositorioFrete repositorio, BigDecimal desconto) {
      this.repositorio = repositorio;
      this.desconto = desconto;
   }

   public BigDecimal calcularValorDoFrete(String codigoProduto, int quantidade) {
      Frete frete = repositorio.buscar(codigoProduto);
      BigDecimal valor = frete.getValorFrete().multiply(new BigDecimal(quantidade));

      if (quantidade > 1) {
         valor = valor.subtract(valor.multiply(desconto));
      }

      return valor.setScale(2, RoundingMode.HALF_UP);
   }

   @Override
   public void criarFreteParaProduto(String codigoProduto, boolean validador) {
      BigDecimal valor = new BigDecimal("0").setScale(2);

      if (validador) {
         float taxa = new Random().nextFloat() * 10;
         valor = new BigDecimal(taxa)
               .setScale(2, RoundingMode.HALF_UP)
               .multiply(new BigDecimal("10"));
      }

      Frete frete = new Frete(codigoProduto, valor);
      repositorio.adicionar(frete);
   }
}
