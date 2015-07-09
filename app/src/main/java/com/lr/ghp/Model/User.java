package com.lr.ghp.Model;

import java.io.Serializable;
import java.math.BigDecimal;


public class User implements Serializable {
	/// <summary>
    /// 用户ID
    /// </summary>
    public int UserID ;
    /// <summary>
    /// 用户名
    /// </summary>
    public String UserName ;

	public int getUserID() {
		return UserID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}
}
