package com.example.demo.model;

public class customer {
    //Atributos
    private int ID;
    private String name;
    private String username;
    private String password;

    

    public customer(int ID, String name, String username, String password) {
        this.ID = ID;
        this.name = name;
        this.username = username;
        this.password = password;
    }


    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    
}
