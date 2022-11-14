package com.example.application.interfaces.DI;

import com.example.application.interfaces.IAccount;
import com.example.application.interfaces.ILoja;
import com.example.application.interfaces.IServicePedido;

public interface AppInjector {

   public ILoja getLoja(RepositoryInjector repositories, ServiceInjector services);

   public IAccount getAccount(RepositoryInjector repositories, ServiceInjector services, IServicePedido servicePedido);
}
