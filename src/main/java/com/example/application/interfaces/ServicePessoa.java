package com.example.application.interfaces;

import java.util.List;

public interface ServicePessoa<T, V> {

   public void executa(V objeto);

   public T getById(String id);

   public List<T> getAll();

   public void remove(T pessoa);

   public void seed();
}
