package com.burak.blooder;

/**
 * Created by burak on 4/21/2017.
 */

public class Users {

    String email;
    String password;
    String name;
    String surname;
    String bloodGroup;
    String phoneNumber;
    String userId;
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }




    public Users() {

    }

    public Users(String email, String password, String name, String surname, String bloodGroup, String phoneNumber, String userId) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.bloodGroup = bloodGroup;
        this.phoneNumber = phoneNumber;
        this.bloodGroup = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "Users{" +
                "email='" + email + '\'' +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                '}';
    }
}
