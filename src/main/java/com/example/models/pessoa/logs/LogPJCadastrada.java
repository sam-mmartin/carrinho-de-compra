package com.example.models.pessoa.logs;

import java.time.format.DateTimeFormatter;

import com.example.models.interfaces.Evento;
import com.example.models.interfaces.LogCadastro;
import com.example.models.pessoa.PJCadastrada;

public class LogPJCadastrada implements LogCadastro {

   @Override
   public void processa(Evento evento) {
      if (this.deveProcessar(evento)) {
         this.reageAo(evento);
      }
   }

   @Override
   public void reageAo(Evento evento) {
      String data = evento.momento().format(DateTimeFormatter.ofPattern("dd//MM//yyyy HH:mm"));

      System.out.println(String.format("Pessoa %s cadastrada em: %s", ((PJCadastrada) evento).getId(), data));
   }

   @Override
   public boolean deveProcessar(Evento evento) {
      return evento instanceof PJCadastrada;
   }

}
