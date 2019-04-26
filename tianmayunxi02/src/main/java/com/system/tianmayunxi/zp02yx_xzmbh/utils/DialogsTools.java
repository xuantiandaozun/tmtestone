package com.system.tianmayunxi.zp02yx_xzmbh.utils;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.system.myproject.interfaces.OnItemClickListener;
import com.system.tianmayunxi.zp02yx_xzmbh.R;
import com.system.uilibrary.R.id;
import com.system.uilibrary.R.layout;
import com.system.uilibrary.R.style;
import com.system.uilibrary.interfaces.OnStringCallBack;
import com.system.uilibrary.interfaces.PhotoListener;
import com.system.uilibrary.views.alertview.AlertView;
import com.system.uilibrary.views.alertview.AlertView.Style;

public class DialogsTools {
    private Context context;
    private Dialog dialog;
    private static com.system.uilibrary.dialog.DialogsTools dialogUtils;
    private InputMethodManager imm;
    private int position;

    public DialogsTools() {
    }

    public static com.system.uilibrary.dialog.DialogsTools getInstance() {
        if (dialogUtils == null) {
            dialogUtils = new com.system.uilibrary.dialog.DialogsTools();
        }

        return dialogUtils;
    }

    public void initContext(Context context) {
        this.context = context;
        this.imm = (InputMethodManager)context.getSystemService("input_method");
    }

    public void createLoadingDialog(String msg) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View v = inflater.inflate(R.layout.dialog_loading_zpo2yx, (ViewGroup)null);
        LinearLayout layout = (LinearLayout)v.findViewById(id.dialog_loading_view);
        TextView tipTextView = (TextView)v.findViewById(id.tipTextView);
        tipTextView.setText(msg);
        Dialog loadingDialog = new Dialog(this.context, style.MyDialogStyle);
        loadingDialog.setCancelable(true);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(layout, new LayoutParams(-1, -1));
        Window window = loadingDialog.getWindow();
        android.view.WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = -1;
        lp.height = -2;
        window.setGravity(17);
        window.setAttributes(lp);
        window.setWindowAnimations(style.PopWindowAnimStyle);
        loadingDialog.show();
        this.dialog = loadingDialog;
    }

    public void createPhotoDialog(@Nullable String titleStr, final PhotoListener listener) {
        final Dialog loadingDialog = new Dialog(this.context, style.MyDialogStyle);
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View v = inflater.inflate(layout.dialog_photo, (ViewGroup)null);
        RelativeLayout layout = (RelativeLayout)v.findViewById(id.dialog_root);
        TextView title = (TextView)v.findViewById(id.title);
        if (!TextUtils.isEmpty(titleStr)) {
            title.setText(titleStr);
        }

        LinearLayout photo = (LinearLayout)v.findViewById(id.ll_photo);
        LinearLayout phone = (LinearLayout)v.findViewById(id.ll_phone);
        photo.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                loadingDialog.dismiss();
                listener.photoListener();
            }
        });
        phone.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                loadingDialog.dismiss();
                listener.phoneListener();
            }
        });
        loadingDialog.setCancelable(true);
        loadingDialog.setCanceledOnTouchOutside(true);
        loadingDialog.setContentView(layout, new LayoutParams(-1, -1));
        Window window = loadingDialog.getWindow();
        android.view.WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = -1;
        lp.height = -2;
        window.setGravity(17);
        window.setAttributes(lp);
        window.setWindowAnimations(style.PopWindowAnimStyle);
        loadingDialog.show();
        this.dialog = loadingDialog;
    }

    public Builder getMessageDialog(String title, String message, DialogInterface.OnClickListener onClickListener) {
        Builder builder = new Builder(this.context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确定", onClickListener);
        builder.setNegativeButton("取消", (DialogInterface.OnClickListener)null);
        return builder;
    }

    public Builder createAndroidMessage(String title, String message, String btn, DialogInterface.OnClickListener onClickListener, int color) {
        Builder builder = new Builder(this.context);
        builder.setTitle(title);
        builder.setMessage(message);
        AlertDialog alertDialog = builder.setPositiveButton(btn, onClickListener).create();
        alertDialog.show();
        alertDialog.getButton(-1).setTextColor(color);
        return builder;
    }





    public void EditDialog(String title, final OnStringCallBack callBack) {
        View view = LayoutInflater.from(this.context).inflate(layout.half_dialog_view, (ViewGroup)null);
        final EditText editText = (EditText)view.findViewById(id.dialog_edit);
        AlertDialog dialog = (new Builder(this.context)).setTitle(title).setView(view).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String content = editText.getText().toString();
                callBack.onStringCallBack(content);
                dialog.dismiss();
            }
        }).create();
        dialog.show();
    }

    public void twoEditDialog(String title, final OnStringCallBack callBack) {
        View view = LayoutInflater.from(this.context).inflate(layout.dialog_twoedit, (ViewGroup)null);
        final EditText word = (EditText)view.findViewById(id.ed_word);
        final EditText password = (EditText)view.findViewById(id.ed_password);
        AlertDialog dialog = (new Builder(this.context)).setTitle(title).setView(view).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String content = word.getText().toString();
                String passwords = password.getText().toString();
                callBack.onTwoEditPassword(content, passwords);
                dialog.dismiss();
            }
        }).create();
        dialog.show();
    }

    public void createIosList(String[] list, OnItemClickListener listener) {
        (new AlertView((String)null, (String)null, "取消", (String[])null, list, this.context, Style.ActionSheet, listener)).show();
    }

    public void createIosSheetImage(String[] list, OnItemClickListener listener) {
        (new AlertView((String)null, (String)null, "取消", (String[])null, list, this.context, Style.ActionSheetImage, listener)).show();
    }

    public void createIosMessage(String title, String content, String btn) {
        (new AlertView(title, content, (String)null, new String[]{btn}, (String[])null, this.context, Style.Alert, (OnItemClickListener)null)).show();
    }

    public void createEditDialog() {
    }

    public void createListDialog() {
    }

    public void closeDialog() {
        if (this.dialog != null && this.dialog.isShowing()) {
            this.dialog.dismiss();
        }

    }

    public interface onButtonClickListener {
        void onClick(String var1);
    }
}

