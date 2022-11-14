package com.example.application;

import com.example.application.interfaces.ILoja;
import com.example.application.interfaces.DI.AppInjector;
import com.example.application.interfaces.DI.InjectorInterface;
import com.example.application.resources.ApplicationInjector;
import com.example.application.resources.Injector;

public class App {
      public static void main(String[] args) {

            InjectorInterface injector = new Injector();
            AppInjector appInjector = new ApplicationInjector();
            ILoja loja = appInjector.getLoja(injector.getRepositoryInjector(), injector.getServiceInjector());

            loja.run();
      }
}
