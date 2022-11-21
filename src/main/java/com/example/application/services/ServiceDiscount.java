package com.example.application.services;

import java.math.BigDecimal;

import com.example.application.interfaces.Discount;
import com.example.application.resources.CadeiaDesconto;

public class ServiceDiscount {

   private Discount discount;

   public BigDecimal realizarAplicacaoDesconto(BigDecimal valor, int quantidade) {
      for (CadeiaDesconto d : CadeiaDesconto.values()) {
         discount = d.aplicarDesconto(quantidade);

         if (discount != null) {
            valor = discount.applyDiscount(valor);
            break;
         }
      }

      return valor;
   }
}
