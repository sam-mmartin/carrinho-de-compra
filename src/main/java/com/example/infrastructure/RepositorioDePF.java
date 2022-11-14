package com.example.infrastructure;

import java.util.ArrayList;
import java.util.List;

import com.example.models.VOs.CPF;
import com.example.models.exceptions.PFNaoEncontrada;
import com.example.models.pessoa.PessoaFisica;
import com.example.models.pessoa.interfaces.RepositorioPessoa;

public class RepositorioDePF implements RepositorioPessoa<PessoaFisica, CPF> {

   private List<PessoaFisica> pessoas = new ArrayList<>();

   @Override
   public void cadastrar(PessoaFisica pessoa) {
      this.pessoas.add(pessoa);
   }

   @Override
   public List<PessoaFisica> listarTodos() {
      return this.pessoas;
   }

   @Override
   public PessoaFisica buscarPorId(CPF cpf) {
      return this.pessoas.stream()
            .filter(p -> p.getCpf().getNumero().equals(cpf.getNumero()))
            .findFirst()
            .orElseThrow(() -> new PFNaoEncontrada(cpf));
   }

   @Override
   public void remover(PessoaFisica pessoa) {
      this.pessoas.removeIf(p -> p.getId().equals(pessoa.getId()));
   }
}
