package com.example.models.pessoa;

import java.util.List;

import com.example.models.VOs.Email;
import com.example.models.VOs.Telefone;

public abstract class Pessoa {

   protected String id;
   protected String nome;
   protected List<Telefone> telefones;
   protected Email email;

   public String getId() {
      return id;
   }
}
