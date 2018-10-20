package com.example.dian.contactapps.Entity;


public class ProfileArray {
    Integer id;
    String firstname;
    String lastname;
    String email;
    String phonenumber;
    String avatar;
    Boolean isfavorite;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsfavorite() {
        return isfavorite;
    }

    public void setIsfavorite(Boolean isfavorite) {
        this.isfavorite = isfavorite;
    }

    public String getAvatar() {
        return isNull(avatar);
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFirstname() {
        return isNull(firstname);
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return isNull(lastname);
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return isNull(email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return isNull(phonenumber);
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    private String isNull(String s){
        if (s == null){
            s="";
        }
        return s;

    }
}
