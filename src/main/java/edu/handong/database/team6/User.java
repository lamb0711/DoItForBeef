package edu.handong.database.team6;

public class User {

    private int id;
    private String name;
    private int pw;
    private int phone;
    private String email;

    public User(int id, String name, int pw, int phone, String email) {
        this.id = id;
        this.name = name;
        this.pw = pw;
        this.phone = phone;
        this.email = email;
    }

    /*
    public String toString() {
        return "ID: " + id + " 이름: " + name;
    }
     */

    public int getID() {
        return id;
    }

    public void setID(int number) {
        this.id = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPW () {
        return pw;
    }

    public void setPW(int pw) {
        this.pw = pw;
    }

    public int getPhone() {
        return phone;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {
    }
}