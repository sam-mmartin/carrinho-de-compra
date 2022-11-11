package com.example.models.pessoa.interfaces;

import java.util.List;

public interface RepositorioPessoa<T, V> {

   public T buscarPorId(V id);

   public void cadastrar(T objeto);

   public List<T> listarTodos();

}
