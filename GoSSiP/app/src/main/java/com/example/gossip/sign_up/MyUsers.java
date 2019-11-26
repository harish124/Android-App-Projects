package com.example.gossip.sign_up;

public class MyUsers {
    private String userName;
    private String email;
    private String uniqueId;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public MyUsers() {
    }

    public MyUsers(String userName, String email, String uniqueId) {
        this.userName = userName;
        this.email = email;
        this.uniqueId=uniqueId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }
}
