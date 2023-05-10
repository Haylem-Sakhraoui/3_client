/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;

/**
 *
 * @author Salim Ben Hamida
 */
public final class UserSession {

    User user;

    private static UserSession instance=new UserSession();

    public static UserSession getInstance() {
       
        return instance;
    }

    private UserSession( ) {}
      

    public User getU() {
        return user;
    }

    public void setU(User u) {
        this.user = u;
    }

    public void destorySession() {
        this.user = null;
    }

}
