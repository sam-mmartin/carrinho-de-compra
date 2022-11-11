package com.example.models.produto;

import java.time.LocalDateTime;

import com.example.models.interfaces.Evento;

public class ProdutoCadastrado implements Evento {

   private String descricao;
   private LocalDateTime momento;

   public ProdutoCadastrado(String descricao) {
      this.descricao = descricao;
      this.momento = LocalDateTime.now();
   }

   public String getDescricao() {
      return descricao;
   }

   @Override
   public LocalDateTime momento() {
      return momento;
   }

}
