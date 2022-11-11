package com.example.models.interfaces;

public interface LogCadastro {

   public void processa(Evento evento);

   public void reageAo(Evento evento);

   public boolean deveProcessar(Evento evento);
}
