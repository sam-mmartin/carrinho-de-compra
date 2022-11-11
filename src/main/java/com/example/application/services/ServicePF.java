package com.example.application.services;

import java.util.List;

import com.example.application.dto.pessoa.DTOCadastrarPF;
import com.example.application.interfaces.ServicePessoa;
import com.example.models.EventPublisher;
import com.example.models.VOs.CPF;
import com.example.models.interfaces.Evento;
import com.example.models.pessoa.PFCadastrada;
import com.example.models.pessoa.PessoaFisica;
import com.example.models.pessoa.interfaces.RepositorioPessoa;

public class ServicePF implements ServicePessoa<PessoaFisica, DTOCadastrarPF> {

   private final RepositorioPessoa<PessoaFisica, CPF> repositorio;
   private final EventPublisher publicador;

   public ServicePF(RepositorioPessoa<PessoaFisica, CPF> repositorio, EventPublisher publicador) {
      this.repositorio = repositorio;
      this.publicador = publicador;
   }

   @Override
   public PessoaFisica getById(String id) {
      CPF cpf = new CPF(id);
      return repositorio.buscarPorId(cpf);
   }

   @Override
   public List<PessoaFisica> getAll() {
      return repositorio.listarTodos();
   }

   @Override
   public void executa(DTOCadastrarPF pessoa) {
      PessoaFisica novo = pessoa.criarPessoa();
      repositorio.cadastrar(novo);

      Evento evento = new PFCadastrada(novo.getCpf());
      publicador.publicar(evento);
   }

   @Override
   public void seed() {
      String nome = "Fulano de Tal";
      String cpf = "001.002.003-04";
      String email = "fulano@email.com";

      executa(new DTOCadastrarPF(nome, cpf, email));
   }

}
