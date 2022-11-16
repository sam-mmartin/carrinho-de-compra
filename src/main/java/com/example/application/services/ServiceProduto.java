package com.example.application.services;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.example.application.dto.produto.DTOCadastrarProduto;
import com.example.models.produto.Produto;
import com.example.models.produto.interfaces.RepositorioProduto;

public class ServiceProduto {

   private final RepositorioProduto repositorio;
   private final ServiceFrete correios;

   public ServiceProduto(RepositorioProduto repositorio, ServiceFrete correios) {
      this.repositorio = repositorio;
      this.correios = correios;
   }

   public List<Produto> getAll() {
      return repositorio.listarTodos();
   }

   public Produto getById(String uuid) {
      return repositorio.buscarPorId(uuid);
   }

   public Produto getProdutoPorDescricao(String descricao) {
      return repositorio.buscarPorDescricao(descricao);
   }

   public List<Produto> getProdutoPorMarca(String marca) {
      return repositorio.buscarPorMarca(marca);
   }

   public List<Produto> getProdutoPorModelo(String modelo) {
      return repositorio.buscarPorModelo(modelo);
   }

   public void executa(DTOCadastrarProduto objeto) {
      Produto novo = objeto.criaProduto();
      repositorio.cadastrar(novo);
   }

   public void seed() {
      JSONObject jsonObject;
      JSONParser parser = new JSONParser();
      String taxa, id, name, price, category, subcategory;

      try {
         Path jsonFile = Paths.get("src/products.json");
         jsonObject = (JSONObject) parser.parse(new FileReader(jsonFile.toAbsolutePath().toString()));
         JSONArray jsonArray = (JSONArray) jsonObject.get("items");

         for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject j = (JSONObject) jsonArray.get(i);
            taxa = "0.00";
            id = (String) j.get("id");
            name = (String) j.get("name");
            price = (String) j.get("price");
            category = (String) j.get("category");
            subcategory = (String) j.get("subcategory");

            if (i % 2 != 0) {
               taxa = "35.99";
            }

            executa(new DTOCadastrarProduto(
                  id,
                  name,
                  price,
                  category,
                  subcategory,
                  taxa));

            if (i % 2 == 0) {
               correios.criarFreteParaProduto(id, true);
            } else {
               correios.criarFreteParaProduto(id, false);
            }
         }
      } catch (Exception e) {
         System.out.println(e.getMessage());
      }
   }

}
