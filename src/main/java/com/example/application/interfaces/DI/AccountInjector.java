package com.example.application.interfaces.DI;

import com.example.application.AccountPF;
import com.example.application.AccountPJ;
import com.example.application.services.ServicePedido;

public interface AccountInjector {

      public AccountPF getAccountPF(ServicePedido servicePedido);

      public AccountPJ getAccountPJ(ServicePedido servicePedido);
}
