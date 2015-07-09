package com.lr.ghp.Model.errorModel;

/**
 * Created by ghp on 7/9/2015.
 */
public class ErrorMsg {
    private int statusCode;
    private String ErrorString;

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorString() {
        return ErrorString;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setErrorString(String errorString) {
        ErrorString = errorString;
    }

    public ErrorMsg(String errorString) {
        ErrorString = errorString;
    }
}
