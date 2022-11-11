package com.example.models.frete;

import java.math.BigDecimal;

public class Frete {

   private String codigoProduto;
   private BigDecimal valorFrete;

   public Frete(String codigoProduto, BigDecimal valorFrete) {
      this.codigoProduto = codigoProduto;
      this.valorFrete = valorFrete;
   }

   public String getCodigoProduto() {
      return codigoProduto;
   }

   public BigDecimal getValorFrete() {
      return valorFrete;
   }

}
