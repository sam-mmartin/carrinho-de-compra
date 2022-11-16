package com.example.models.pessoa.interfaces;

import com.example.models.pessoa.PessoaFisica;
import com.example.models.pessoa.factory.FabricaPF;

public interface FabricaPessoaFisica {

   public FabricaPF comCpfNomeEmail(String cpf, String nome, String email);

   public FabricaPF comTelefone(String ddd, String numero);

   public PessoaFisica criar();
}
