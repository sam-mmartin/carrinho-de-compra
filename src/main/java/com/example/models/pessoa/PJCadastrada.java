package com.example.models.pessoa;

import java.time.LocalDateTime;

import com.example.models.VOs.CNPJ;
import com.example.models.interfaces.Evento;

public class PJCadastrada implements Evento {

   private final CNPJ cnpj;
   private final LocalDateTime momento;

   public PJCadastrada(CNPJ cnpj) {
      this.cnpj = cnpj;
      this.momento = LocalDateTime.now();
   }

   public CNPJ getId() {
      return cnpj;
   }

   @Override
   public LocalDateTime momento() {
      return this.momento;
   }

}
