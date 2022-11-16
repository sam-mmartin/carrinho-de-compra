package com.example.models.pessoa.interfaces;

import com.example.models.pessoa.PessoaJuridica;
import com.example.models.pessoa.factory.FabricaPJ;

public interface FabricaPessoaJuridica {

   public FabricaPJ comCnpjNomeEmail(String cnpj, String nomeFantasia, String razaoSocial, String email,
         String dataCriacao);

   public FabricaPJ comTelefone(String ddd, String numero);

   public PessoaJuridica criar();
}
