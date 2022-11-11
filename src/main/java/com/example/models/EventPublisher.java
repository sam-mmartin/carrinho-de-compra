package com.example.models;

import java.util.ArrayList;
import java.util.List;

import com.example.models.interfaces.Evento;
import com.example.models.interfaces.LogCadastro;

public class EventPublisher {

   private List<LogCadastro> ouvintes = new ArrayList<>();

   public void adicionar(LogCadastro ouvinte) {
      this.ouvintes.add(ouvinte);
   }

   public void publicar(Evento evento) {
      this.ouvintes.forEach(o -> {
         o.processa(evento);
      });
   }
}
