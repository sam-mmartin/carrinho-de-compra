package com.example.models.pessoa.interfaces;

import java.util.List;

public interface RepositorioPessoa<T, V> {

   public T buscarPorId(V id);

   public void cadastrar(T pessoa);

   public List<T> listarTodos();

   public void remover(T pessoa);

}
