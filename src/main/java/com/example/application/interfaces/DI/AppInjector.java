package com.example.application.interfaces.DI;

import com.example.application.Loja;

public interface AppInjector {

   public Loja getLoja(RepositoryInjector repositories, ServiceInjector services, AccountInjector accountInjector);

}
