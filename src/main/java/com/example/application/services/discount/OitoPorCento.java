package com.example.application.services.discount;

import java.math.BigDecimal;

import com.example.application.interfaces.Discount;

public class OitoPorCento implements Discount {

   @Override
   public BigDecimal applyDiscount(BigDecimal totalPurchaseAmount) {
      BigDecimal temp = totalPurchaseAmount.multiply(new BigDecimal("0.08"));
      return totalPurchaseAmount.subtract(temp);
   }

}
