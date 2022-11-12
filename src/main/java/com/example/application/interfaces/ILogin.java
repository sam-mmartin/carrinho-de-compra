package com.example.application.interfaces;

import com.example.models.pessoa.Pessoa;

public interface ILogin {

   public void userAccount(Pessoa user);

   public void singIn(String id);

   public <T> void singOut(T user, int tipo);

   public Pessoa logIn();

   public void logOut(Pessoa user);

   public <T> void setUser(T user, int tipo);

   public Pessoa getUser(int tipo);

}
