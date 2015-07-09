package com.lr.ghp.Model.responseModel;

/**
 * Created by ghp on 7/9/2015.
 */
public class LoginResponse {
    private int UserID ;
    private String Username ;
    private String Token ;

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
