package com.example.application.services;

import java.util.List;

import com.example.application.dto.pessoa.DTOCadastrarPJ;
import com.example.application.interfaces.ServicePessoa;
import com.example.models.VOs.CNPJ;
import com.example.models.pessoa.PessoaJuridica;
import com.example.models.pessoa.interfaces.RepositorioPessoa;

public class ServicePJ implements ServicePessoa<PessoaJuridica, DTOCadastrarPJ> {

   private final RepositorioPessoa<PessoaJuridica, CNPJ> repositorio;

   public ServicePJ(RepositorioPessoa<PessoaJuridica, CNPJ> repositorio) {
      this.repositorio = repositorio;
   }

   @Override
   public PessoaJuridica getById(String id) {
      CNPJ cnpj = new CNPJ(id);
      return repositorio.buscarPorId(cnpj);
   }

   @Override
   public List<PessoaJuridica> getAll() {
      return repositorio.listarTodos();
   }

   @Override
   public void executa(DTOCadastrarPJ pessoa) {
      PessoaJuridica novo = pessoa.criarPessoa();
      repositorio.cadastrar(novo);
   }

   @Override
   public void seed() {
      String nomeFantasia = "Banco do Brasil";
      String razaoSocial = "BANCO DO BRASIL S.A.";
      String cnpj = "00.000.000/0001-91";
      String emailPJ = "bb@email.com";
      String dataCriacao = "1905-12-30";

      executa(new DTOCadastrarPJ(cnpj, nomeFantasia, razaoSocial, emailPJ, dataCriacao));
   }

   @Override
   public void remove(PessoaJuridica pessoa) {
      repositorio.remover(pessoa);
   }

}
