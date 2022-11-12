package com.example.infrastructure;

import java.util.ArrayList;
import java.util.List;

import com.example.models.VOs.CNPJ;
import com.example.models.exceptions.PJNaoEncontrada;
import com.example.models.pessoa.PessoaJuridica;
import com.example.models.pessoa.interfaces.RepositorioPessoa;

public class RepositorioDePJ implements RepositorioPessoa<PessoaJuridica, CNPJ> {

   private List<PessoaJuridica> pessoas = new ArrayList<>();;

   @Override
   public PessoaJuridica buscarPorId(CNPJ cnpj) {
      return this.pessoas.stream()
            .filter(p -> p.getCnpj().equals(cnpj.getNumero()))
            .findFirst()
            .orElseThrow(() -> new PJNaoEncontrada(cnpj));
   }

   @Override
   public void cadastrar(PessoaJuridica pessoa) {
      this.pessoas.add(pessoa);
   }

   @Override
   public List<PessoaJuridica> listarTodos() {
      return this.pessoas;
   }

   @Override
   public void remover(PessoaJuridica pessoa) {
      this.pessoas.remove(pessoa);
   }
}
