package com.example.application.resources;

import java.math.BigDecimal;

import com.example.application.Loja;
import com.example.application.interfaces.DI.AccountInjector;
import com.example.application.interfaces.DI.AppInjector;
import com.example.application.interfaces.DI.RepositoryInjector;
import com.example.application.interfaces.DI.ServiceInjector;
import com.example.application.services.ServiceCart;
import com.example.application.services.ServiceFrete;
import com.example.application.services.ServicePedido;
import com.example.application.services.ServiceProduto;
import com.example.models.frete.interfaces.RepositorioFrete;
import com.example.models.loja.interfaces.Carrinho;
import com.example.models.loja.interfaces.RepositorioPedido;
import com.example.models.produto.interfaces.RepositorioProduto;

public class ApplicationInjector implements AppInjector {

   @Override
   public Loja getLoja(RepositoryInjector repositories, ServiceInjector services, AccountInjector accountInjector) {

      RepositorioProduto produtos = repositories.getRepositorioProduto();
      RepositorioFrete fretes = repositories.getRepositorioFrete();
      RepositorioPedido pedidos = repositories.getRepositorioPedido();
      Carrinho carrinho = repositories.getCarrinho();

      ServiceFrete correios = services.getIserviceFrete(fretes, new BigDecimal("0.4"));
      ServiceProduto serviceProduto = services.getServiceProduto(produtos, correios);
      ServiceCart serviceCart = services.getServiceCart(carrinho, correios);
      PaginaProdutos paginaProdutos = services.getPageProdutos(serviceProduto);
      ServicePedido servicePedido = services.getServicePedido(pedidos);

      serviceProduto.seed();

      return new Loja(paginaProdutos, serviceCart, servicePedido, accountInjector);

   }

}
