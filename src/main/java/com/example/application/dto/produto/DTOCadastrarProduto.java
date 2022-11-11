package com.example.application.dto.produto;

import java.math.BigDecimal;

import com.example.models.VOs.Marca;
import com.example.models.VOs.Modelo;
import com.example.models.VOs.Taxa;
import com.example.models.produto.Produto;

public class DTOCadastrarProduto {

   private String uuid;
   private String descricao;
   private String valor;
   private String marca;
   private String modelo;
   private String taxa;

   public DTOCadastrarProduto(String uuid, String descricao, String valor, String marca, String modelo, String taxa) {
      this.uuid = uuid;
      this.descricao = descricao;
      this.valor = valor;
      this.marca = marca;
      this.modelo = modelo;
      this.taxa = taxa;
   }

   public Produto criaProduto() {
      return new Produto(uuid, descricao,
            new BigDecimal(valor).setScale(2),
            new Marca(marca),
            new Modelo(modelo),
            new Taxa(new BigDecimal(taxa)));
   }
}
