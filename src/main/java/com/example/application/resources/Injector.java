package com.example.application.resources;

import com.example.application.interfaces.DI.InjectorInterface;
import com.example.application.interfaces.DI.RepositoryInjector;
import com.example.application.interfaces.DI.ServiceInjector;

public class Injector implements InjectorInterface {

   @Override
   public RepositoryInjector getRepositoryInjector() {
      return new RepositoryDependencyInjector();
   }

   @Override
   public ServiceInjector getServiceInjector() {
      return new ServiceDependencyInjector();
   }

}
