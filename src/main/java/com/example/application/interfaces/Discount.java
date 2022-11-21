package com.example.application.interfaces;

import java.math.BigDecimal;

public interface Discount {

   public BigDecimal applyDiscount(BigDecimal totalPurchaseAmount);
}
