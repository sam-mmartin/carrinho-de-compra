package com.example.application.services.discount;

import java.math.BigDecimal;

import com.example.application.interfaces.Discount;

public class DezPorCento implements Discount {

   @Override
   public BigDecimal applyDiscount(BigDecimal totalPurchaseAmount) {
      BigDecimal temp = totalPurchaseAmount.multiply(new BigDecimal("0.1"));
      return totalPurchaseAmount.subtract(temp);
   }

}
