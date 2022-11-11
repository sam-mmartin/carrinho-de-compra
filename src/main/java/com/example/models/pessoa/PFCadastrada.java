package com.example.models.pessoa;

import java.time.LocalDateTime;

import com.example.models.VOs.CPF;
import com.example.models.interfaces.Evento;

public class PFCadastrada implements Evento {

   private final CPF cpf;
   private final LocalDateTime momento;

   public PFCadastrada(CPF cpf) {
      this.cpf = cpf;
      this.momento = LocalDateTime.now();
   }

   public CPF getId() {
      return cpf;
   }

   @Override
   public LocalDateTime momento() {
      return momento;
   }

}
