package com.lr.ghp.volleydemo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lr.ghp.Model.errorModel.LoginError;
import com.lr.ghp.Model.errorModel.LoginUserError;
import com.lr.ghp.Model.responseModel.LoginResponse;
import com.lr.ghp.Model.responseModel.LoginUser;
import com.lr.ghp.app.MyApplication;
import com.lr.ghp.constant.DataConstant;
import com.lr.ghp.constant.URLConstant;
import com.lr.ghp.network.UserNetWork;
import com.lr.ghp.utility.AttemptLogin;
import com.lr.ghp.utility.CommonUtility;
import com.lr.ghp.utility.GetUrlStr;
import com.lr.ghp.view.ClearEditText;
import com.lr.ghp.view.ProgressPromptDialog;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


public class MainActivity extends ActionBarActivity {
    @InjectView(R.id.imgCode_login)ImageView imgCode_login;
    @InjectView(R.id.login_code_progressbar)ProgressBar login_code_progressbar;
    @InjectView(R.id.login_code_tip)TextView login_code_tip;
    @InjectView(R.id.txtCode_login)EditText txtCode_login;
    private ClearEditText txtUserName_login;
    private ClearEditText txtPassword_login;
    @InjectView(R.id.error_tip_login)TextView error_tip_login;
    private ProgressPromptDialog progressPromptDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        ButterKnife.inject(this);
        initView();
        initCode();
    }
    private void initView(){
        txtUserName_login= (ClearEditText) findViewById(R.id.txtUserName_login);
        txtPassword_login= (ClearEditText) findViewById(R.id.txtPassword_login);
    }

    private void initCode(){
        CommonUtility.LoadCodeImage(this, imgCode_login, login_code_progressbar, login_code_tip);
        login_code_tip.setText("");
    }
    @OnClick(R.id.imgCode_login)void getCode(){
        initCode();
    }
    @OnClick(R.id.goLoginBtn)void goLogin(){
        attemptLogin();
    }
    public void attemptLogin() {
        AttemptLogin attemptLogin=new AttemptLogin(txtUserName_login,txtPassword_login,txtCode_login,error_tip_login,this);
        boolean attempLoginResult=attemptLogin.returnAttemptResult();
        if(!attempLoginResult){
            Logining();
        }
    }

    private void Logining(){
        String userName = txtUserName_login.getText().toString();
        String password = txtPassword_login.getText().toString();
        String code = txtCode_login.getText().toString();
        UserNetWork userNetWork=new UserNetWork(this);
        String siteUrl = GetUrlStr.getSiteUrl(this);
        userNetWork.userLogin(siteUrl + URLConstant.getLoginUrl(), userName, password, code);
        showDialog();

    }
    public void onEventMainThread(LoginResponse loginResponse){
        MyApplication app = (MyApplication) getApplicationContext();
        app.setLongToken(loginResponse.getToken());
        //Toast.makeText(this,"login success",Toast.LENGTH_SHORT).show();
        CommonUtility.refreshUser(this);
    }


    public void onEventMainThread(LoginError loginError){
        initCode();
        if(loginError!=null){
            DataErrorCodeLogin(loginError.getErrorString());
        }
        progressPromptDialog.dismiss();
    }

    public void onEventMainThread(LoginUser loginUser){
        CommonUtility.saveUser(loginUser,this);
        progressPromptDialog.dismiss();
//        setResult(DataConstant.LOGIN_INTENT);
//        this.finish();
        Toast.makeText(this,"login success",Toast.LENGTH_SHORT).show();
    }

    public void onEventMainThread(LoginUserError userError){
        initCode();
        if(userError!= null){
            DataErrorCodeLogin(userError.getErrorString());
        }
        progressPromptDialog.dismiss();
    }


    //显示错误信息
    private void DataErrorCodeLogin(String errorString){
        progressPromptDialog.dismiss();
        CommonUtility.showErrorString(error_tip_login, errorString);
        if(errorString.contains("验证码")){
            txtCode_login.requestFocus();
        }
        else {
            txtPassword_login.requestFocus();
        }
    }

    private void showDialog(){
        progressPromptDialog=new ProgressPromptDialog(this);
        progressPromptDialog.setTipStr("登录中...");
        progressPromptDialog.show();
    }
    @OnClick(R.id.forget_pwd)void toForgetpwdClick(){

    }
}
