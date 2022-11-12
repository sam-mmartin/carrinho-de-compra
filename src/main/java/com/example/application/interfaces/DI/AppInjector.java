package com.example.application.interfaces.DI;

import com.example.application.interfaces.ILogin;
import com.example.application.interfaces.ILoja;

public interface AppInjector {

   public ILoja getLoja(RepositoryInjector repositories, ServiceInjector services);

   public ILogin getLogin(RepositoryInjector repositories, ServiceInjector services);
}
