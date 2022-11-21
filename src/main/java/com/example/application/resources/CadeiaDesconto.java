package com.example.application.resources;

import com.example.application.interfaces.Discount;
import com.example.application.services.discount.CincoPorCento;
import com.example.application.services.discount.DezPorCento;
import com.example.application.services.discount.OitoPorCento;

public enum CadeiaDesconto {
   CINCOPORCENTO {
      @Override
      public Discount aplicarDesconto(int quantidade) {
         if (quantidade >= 15) {
            return new DezPorCento();
         }
         return null;
      }
   },
   OITOPORCENTO {
      @Override
      public Discount aplicarDesconto(int quantidade) {
         if (quantidade < 15 && quantidade >= 10) {
            return new OitoPorCento();
         }
         return null;
      }
   },
   DEZPORCENTO {
      @Override
      public Discount aplicarDesconto(int quantidade) {
         if (quantidade < 10 && quantidade >= 5) {
            return new CincoPorCento();
         }
         return null;
      }
   };

   public abstract Discount aplicarDesconto(int quantidade);
}
