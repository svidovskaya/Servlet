package model;


import javax.persistence.*;
import java.awt.*;
import java.util.Collection;
import java.util.Set;


public class User {

    private int id;
    private String username;
    private String name;
    private String surname;
    private String middlename;
    private String phone;
    private String mail;
    private String role;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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




    public void setUsername(String username) {
        this.username = username;
    }




    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public User(int id, String username, String name, String surname, String middlename, String phone, String mail, String role) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
        this.phone = phone;
        this.mail = mail;
        this.role = role;

    }
}
