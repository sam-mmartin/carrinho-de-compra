package com.example.models.produto.logs;

import java.time.format.DateTimeFormatter;

import com.example.models.interfaces.Evento;
import com.example.models.interfaces.LogCadastro;
import com.example.models.produto.ProdutoCadastrado;

public class LogProdutoCadastrado implements LogCadastro {

   @Override
   public void processa(Evento evento) {
      if (this.deveProcessar(evento)) {
         this.reageAo(evento);
      }
   }

   public void reageAo(Evento evento) {
      String data = evento.momento().format(DateTimeFormatter.ofPattern("dd//MM//yyyy HH:mm"));

      System.out
            .println(String.format("Produto %s cadastrado em: %s", ((ProdutoCadastrado) evento).getDescricao(), data));
   }

   @Override
   public boolean deveProcessar(Evento evento) {
      return evento instanceof ProdutoCadastrado;
   }

}
