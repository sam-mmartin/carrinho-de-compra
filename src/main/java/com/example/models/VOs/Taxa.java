package com.example.models.VOs;

import java.math.BigDecimal;

public class Taxa {

   private BigDecimal valor;

   public Taxa(BigDecimal valor) {
      this.valor = valor;
   }

   public BigDecimal getValor() {
      return valor;
   }
}
