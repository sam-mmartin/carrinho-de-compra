package com.example.models.produto.factory;

import java.math.BigDecimal;

import com.example.models.VOs.Marca;
import com.example.models.VOs.Modelo;
import com.example.models.VOs.Taxa;
import com.example.models.produto.Produto;

public class FabricaProduto {

   private Produto produto;

   public FabricaProduto comDescricaoValorMarca(String uuid, String descricao, BigDecimal valor, Marca marca,
         Modelo modelo, Taxa taxa) {
      this.produto = new Produto(uuid, descricao, valor, marca, modelo, taxa);
      return this;
   }

   public Produto criar() {
      return this.produto;
   }
}
