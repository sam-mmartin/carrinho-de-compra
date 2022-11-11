package com.example.models.interfaces;

import java.util.List;

public interface Repositorio<T> {

   public void cadastrar(T objeto);

   public List<T> listarTodos();
}
