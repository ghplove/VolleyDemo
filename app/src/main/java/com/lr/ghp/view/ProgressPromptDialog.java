package com.lr.ghp.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lr.ghp.volleydemo.R;


/**
 * Created by guohuiping on 15-3-27.
 */
public class ProgressPromptDialog extends Dialog {
    private Context context;
    private RelativeLayout dialog_img;
    public ProgressBar progress_loading_img;
    public ImageView progress_tip_img;
    public TextView prompt_txt;
    public ProgressPromptDialog(Context context) {
        super(context, R.style.progress_prompt_dialog_style);
        this.context=context;
        initView();
    }

    public ProgressPromptDialog(Context context, int theme) {
        super(context, theme);
        this.context=context;
        initView();
    }

    protected ProgressPromptDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context=context;
        initView();
    }
    private void initView(){
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.progress_prompt_dialog_layout, null);// 得到加载view
        this.setContentView(v,new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        dialog_img= (RelativeLayout) v.findViewById(R.id.dialog_img);
        progress_loading_img= (ProgressBar) v.findViewById(R.id.progress_loading_img);
        progress_loading_img.setVisibility(View.VISIBLE);
        progress_tip_img= (ImageView) v.findViewById(R.id.progress_tip_img);
        prompt_txt= (TextView) v.findViewById(R.id.prompt_txt);
        this.getWindow().getAttributes().gravity = Gravity.CENTER;
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    public void setProgressTipImg(int tipImg){
        dialog_img.setVisibility(View.VISIBLE);
        progress_loading_img.setVisibility(View.GONE);
        progress_tip_img.setVisibility(View.VISIBLE);
        progress_tip_img.setBackgroundResource(tipImg);
    }
    public void setProgressTip(){
        dialog_img.setVisibility(View.VISIBLE);
        progress_loading_img.setVisibility(View.VISIBLE);
        progress_tip_img.setVisibility(View.GONE);
    }

    public void setDialogCanceledOnTouchOutside(boolean status){
        this.setCanceledOnTouchOutside(status);
    }
    public void setTipStr(String tipStr){
        prompt_txt.setText(tipStr);
    }
}
