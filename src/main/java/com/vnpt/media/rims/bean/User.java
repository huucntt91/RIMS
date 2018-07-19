/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpt.media.rims.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class User  {

    public String username;
    public String password;
    public List<Role> authorities = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

//    public boolean isValidUser() {
//        authorities.clear();
//        if ("user".equalsIgnoreCase(username) && "123456".equalsIgnoreCase(password)) {
//            authorities.add(new Role("ROLE_USER"));
//            return true;
//        }
//        if ("admin".equalsIgnoreCase(username) && "123456".equalsIgnoreCase(password)) {
//            authorities.add(new Role("ROLE_ADMIN"));
//            authorities.add(new Role("ROLE_DBA"));
//            return true;
//        }
//        if ("dba".equalsIgnoreCase(username) && "123456".equalsIgnoreCase(password)) {
//            authorities.add(new Role("ROLE_DBA"));
//            return true;
//        }
//        return false;
//    }

   


   
    public String getPassword() {
        return password;
    }

   
    public String getUsername() {
        return username;
    }

  
    public boolean isAccountNonExpired() {
        return true;
    }

    
    public boolean isAccountNonLocked() {
        return true;
    }

  
    public boolean isCredentialsNonExpired() {
        return true;
    }


    public boolean isEnabled() {
        return true;
    }
}
