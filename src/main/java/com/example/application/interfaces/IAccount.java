package com.example.application.interfaces;

import com.example.models.pessoa.Pessoa;

public interface IAccount {

   public Pessoa userAccount(Pessoa user);

   public void viewUserInfos(Pessoa user);

   public void singIn(String id);

   public <T> void singOut(T user, int tipo);

   public Pessoa logIn();

   public Pessoa logOut();

   public <T> void setUser(T user, int tipo);

   public Pessoa getUser(int tipo);

}
