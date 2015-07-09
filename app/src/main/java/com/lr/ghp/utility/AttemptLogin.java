package com.lr.ghp.utility;

import android.content.Context;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lr.ghp.Model.ApiUpdate;
import com.lr.ghp.app.MyApplication;

/**
 * Created by ghp on 15/7/8.
 */
public class AttemptLogin {
    private EditText txtUserName;
    private EditText txtUserPwd;
    private EditText txtCode;
    private TextView showErrorText;
    private Context context;
    private View focusView = null;
    private boolean cancel = false;

    public AttemptLogin(EditText txtUserName, EditText txtUserPwd, EditText txtCode, TextView showErrorText, Context context) {
        this.txtUserName = txtUserName;
        this.txtUserPwd = txtUserPwd;
        this.txtCode = txtCode;
        this.showErrorText = showErrorText;
        this.context = context;
        txtUserName.setOnTouchListener(new tipOnTouchListener());
        txtUserPwd.setOnTouchListener(new tipOnTouchListener());
        txtCode.setOnTouchListener(new tipOnTouchListener());
    }

    public boolean returnAttemptResult(){
        MyApplication application= (MyApplication) context.getApplicationContext();
        ApiUpdate apiUpdate=application.getApiUpdate();
        String userNameRegex="(?!^\\d[A-Za-z0-9]*$)^[A-Za-z0-9_-]{6,25}$";
        String userPasswordRegex="^[A-Za-z0-9\\^\\$\\.\\+\\*_@!#%&~=-]{6,32}$";
        String phoneRegex   ="^[1][0-9]{10}$";
//        String recommendRegex="^[A-Za-z0-9]{1,9}$";
//        String chineseRegex = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";
//        String idCardeNoRegex="(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";
//        String tradeRegex="^[A-Za-z0-9\\^\\$\\.\\+\\*_@!#%&amp;~=-]{6,32}$";
        if(apiUpdate!=null){
            if(!TextUtils.isEmpty(apiUpdate.getUserNameRegex())){
                userNameRegex=apiUpdate.getUserNameRegex();
            }
            if(!TextUtils.isEmpty(apiUpdate.getPasswordRegex())){
                userPasswordRegex=apiUpdate.getPasswordRegex();
            }
        }

        String mEmail = txtUserName.getText().toString();
        String mPassword = txtUserPwd.getText().toString();
        String code = txtCode.getText().toString();

        txtUserName.setError(null);
        txtUserPwd.setError(null);

        if (TextUtils.isEmpty(mPassword)) {
            setShowError(true,txtUserPwd,"请输入密码");
        } else if (mPassword.length() < 6) {
            setShowError(true,txtUserPwd,"密码必须大于6位");
        }else if(!CommonUtility.checkString(mPassword, userPasswordRegex)){
            setShowError(true,txtUserPwd,"密码应由 6 - 32 位数字，字母及常用符号组成");
        }

        if (TextUtils.isEmpty(mEmail)) {
            setShowError(true,txtUserName,"请输入用户名/手机号");
        }else if(!CommonUtility.checkString(mEmail, phoneRegex)){
            if(!CommonUtility.checkString(mEmail, userNameRegex)){
                setShowError(true,txtUserName,"您的用户名/手机号有误，请重新输入。\n使用用户名则只允许字母、数字、下划线、横线组成，首位只能为字母，且至少需要 6 个字符。");
            }
        }else if(!CommonUtility.checkString(mEmail, userNameRegex)){
            if(!CommonUtility.checkString(mEmail, phoneRegex)){
                setShowError(true,txtUserName,"您的用户名/手机号有误，请重新输入。\n使用用户名则只允许字母、数字、下划线、横线组成，首位只能为字母，且至少需要 6 个字符。");
            }
        }

        if(TextUtils.isEmpty(code)){
            setShowError(true,txtCode,"请输入验证码");
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            cancel=false;
        }
        return cancel;
    }
    private void setShowError(boolean cancle,View view,String errorMsg){
        this.cancel=cancle;
        focusView=view;
        CommonUtility.showErrorString(showErrorText, errorMsg);
    }

    /**
     * 获得焦点时，错误提示隐藏
     */
    private class tipOnTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            showErrorText.setVisibility(View.GONE);
            return false;
        }
    }
}
