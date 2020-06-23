package com.example.paint.pojo.po;

public class User {
    private Long id;
    private String miniName;
    private String email;
    private String passWord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMiniName() {
        return miniName;
    }

    public void setMiniName(String miniName) {
        this.miniName = miniName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
