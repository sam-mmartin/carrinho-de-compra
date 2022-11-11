package com.example.models.produto;

import java.math.BigDecimal;
import com.example.models.VOs.Marca;
import com.example.models.VOs.Modelo;
import com.example.models.VOs.Taxa;

public class Produto {

   private String uuid;
   private String descricao;
   private BigDecimal valor;
   private Marca marca;
   private Modelo modelo;
   private Taxa taxa;

   public Produto(String uuid, String descricao, BigDecimal valor, Marca marca, Modelo modelo, Taxa taxa) {
      this.uuid = uuid;
      this.descricao = descricao;
      this.valor = valor;
      this.marca = marca;
      this.modelo = modelo;
      this.taxa = taxa;
   }

   public String getUuid() {
      return uuid;
   }

   public String getDescricao() {
      return descricao;
   }

   public BigDecimal getValor() {
      return valor;
   }

   public Marca getMarca() {
      return marca;
   }

   public Modelo getModelo() {
      return modelo;
   }

   public Taxa getTaxa() {
      return taxa;
   }

   @Override
   public String toString() {
      return "---------------------------------------------------------------------"
            + "\n| " + descricao
            + "\n| " + valor;
   }
}
