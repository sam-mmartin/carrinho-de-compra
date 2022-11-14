package com.example.application.interfaces.DI;

import com.example.application.Account;
import com.example.application.Loja;
import com.example.application.services.ServicePedido;

public interface AppInjector {

   public Loja getLoja(RepositoryInjector repositories, ServiceInjector services);

   public Account getAccount(RepositoryInjector repositories, ServiceInjector services, ServicePedido servicePedido);
}
