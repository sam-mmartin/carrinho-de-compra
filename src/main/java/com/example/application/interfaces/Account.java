package com.example.application.interfaces;

public interface Account {

   public void userAccount();

   public void viewUserInfos();

   public void singIn(String id);

   public void logIn(String id);

   public void singOut();

   public void logOut();

   public <T> void setUser(T user);
}
