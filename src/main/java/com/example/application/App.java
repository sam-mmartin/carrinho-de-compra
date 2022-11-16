package com.example.application;

import com.example.application.interfaces.DI.AccountInjector;
import com.example.application.interfaces.DI.AppInjector;
import com.example.application.interfaces.DI.InjectorInterface;
import com.example.application.resources.ApplicationInjector;
import com.example.application.resources.Injector;
import com.example.application.resources.UserInjector;

public class App {
      public static void main(String[] args) {

            InjectorInterface injector = new Injector();
            AppInjector appInjector = new ApplicationInjector();
            AccountInjector accountInjector = new UserInjector(injector.getRepositoryInjector(),
                        injector.getServiceInjector());
            Loja loja = appInjector.getLoja(injector.getRepositoryInjector(), injector.getServiceInjector(),
                        accountInjector);

            loja.run();
      }
}
