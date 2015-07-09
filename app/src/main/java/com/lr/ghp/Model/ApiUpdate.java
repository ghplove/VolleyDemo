package com.lr.ghp.Model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ApiUpdate implements Serializable{

	public String ApiVersion;
	public String ApiMinVersion;
	public String AndroidVersion;
	public String AndroidMinVersion;
	public String AndroidAppUrl;
	public String IOSVersion;
	public String IOSMinVersion;
	public String IOSAppUrl;
	public String DictVersion;
	public boolean Recharge;
	public boolean Withdraw;
	public boolean BindCard;
	public boolean Invest;
	public boolean Coupon;
	public boolean UMeng;
	public boolean JPush;
	public String UserNameRegex;
	public String PasswordRegex;
    public String ChannelRechargeDomain;
    public String ContractDomain;
    public String InvestmentSuccessUrl;

    public BigDecimal getAppCreditAssignMaxDiscount() {
        return AppCreditAssignMaxDiscount;
    }

    public void setAppCreditAssignMaxDiscount(BigDecimal appCreditAssignMaxDiscount) {
        AppCreditAssignMaxDiscount = appCreditAssignMaxDiscount;
    }

    public BigDecimal AppCreditAssignMaxDiscount;
    private boolean RedBagEnable;

    public String getUpdateChannel() {
        return UpdateChannel;
    }

    public void setUpdateChannel(String updateChannel) {
        UpdateChannel = updateChannel;
    }

    public String UpdateChannel;

    public String getPayChannel() {
        return PayChannel;
    }

    public void setPayChannel(String payChannel) {
        PayChannel = payChannel;
    }

    public String getInvestmentSuccessUrl() {
        return InvestmentSuccessUrl;
    }

    public void setInvestmentSuccessUrl(String investmentSuccessUrl) {
        InvestmentSuccessUrl = investmentSuccessUrl;
    }

    public String PayChannel;

    public BigDecimal getCreditAssignMaxDiscount() {
        return CreditAssignMaxDiscount;
    }

    public void setCreditAssignMaxDiscount(BigDecimal creditAssignMaxDiscount) {
        CreditAssignMaxDiscount = creditAssignMaxDiscount;
    }

    public BigDecimal CreditAssignMaxDiscount;

    public String getChannelRechargeDomain() {
        return ChannelRechargeDomain;
    }

    public void setChannelRechargeDomain(String channelRechargeDomain) {
        ChannelRechargeDomain = channelRechargeDomain;
    }

    public String getAppServerDomain() {
        return AppServerDomain;
    }

    public void setAppServerDomain(String appServerDomain) {
        AppServerDomain = appServerDomain;
    }

    public String getContractDomain() {
        return ContractDomain;
    }

    public void setContractDomain(String contractDomain) {
        ContractDomain = contractDomain;
    }

    public String AppServerDomain;


	public String getApiVersion() {
		return ApiVersion;
	}

	public void setApiVersion(String apiVersion) {
		ApiVersion = apiVersion;
	}

	public String getApiMinVersion() {
		return ApiMinVersion;
	}

	public void setApiMinVersion(String apiMinVersion) {
		ApiMinVersion = apiMinVersion;
	}

	public String getAndroidVersion() {
		return AndroidVersion;
	}

	public void setAndroidVersion(String androidVersion) {
		AndroidVersion = androidVersion;
	}

	public String getAndroidMinVersion() {
		return AndroidMinVersion;
	}

	public void setAndroidMinVersion(String androidMinVersion) {
		AndroidMinVersion = androidMinVersion;
	}

	public String getAndroidAppUrl() {
		return AndroidAppUrl;
	}

	public void setAndroidAppUrl(String androidAppUrl) {
		AndroidAppUrl = androidAppUrl;
	}

	public String getIOSVersion() {
		return IOSVersion;
	}

	public void setIOSVersion(String iOSVersion) {
		IOSVersion = iOSVersion;
	}

	public String getIOSMinVersion() {
		return IOSMinVersion;
	}

	public void setIOSMinVersion(String iOSMinVersion) {
		IOSMinVersion = iOSMinVersion;
	}

	public String getIOSAppUrl() {
		return IOSAppUrl;
	}

	public void setIOSAppUrl(String iOSAppUrl) {
		IOSAppUrl = iOSAppUrl;
	}

	public String getDictVersion() {
		return DictVersion;
	}

	public void setDictVersion(String dictVersion) {
		DictVersion = dictVersion;
	}

	public boolean isRecharge() {
		return Recharge;
	}

	public void setRecharge(boolean recharge) {
		Recharge = recharge;
	}

	public boolean isWithdraw() {
		return Withdraw;
	}

	public void setWithdraw(boolean withdraw) {
		Withdraw = withdraw;
	}

	public boolean isBindCard() {
		return BindCard;
	}

	public void setBindCard(boolean bindCard) {
		BindCard = bindCard;
	}

	public boolean isInvest() {
		return Invest;
	}

	public void setInvest(boolean invest) {
		Invest = invest;
	}

	public boolean isCoupon() {
		return Coupon;
	}

	public void setCoupon(boolean coupon) {
		Coupon = coupon;
	}

	public boolean isUMeng() {
		return UMeng;
	}

	public void setUMeng(boolean uMeng) {
		UMeng = uMeng;
	}

	public boolean isJPush() {
		return JPush;
	}

	public void setJPush(boolean jPush) {
		JPush = jPush;
	}

	public String getUserNameRegex() {
		return UserNameRegex;
	}

	public void setUserNameRegex(String userNameRegex) {
		UserNameRegex = userNameRegex;
	}

	public String getPasswordRegex() {
		return PasswordRegex;
	}

	public void setPasswordRegex(String passwordRegex) {
		PasswordRegex = passwordRegex;
	}

    public boolean isRedBagEnable() {
        return RedBagEnable;
    }

    public void setRedBagEnable(boolean redBagEnable) {
        RedBagEnable = redBagEnable;
    }
}
