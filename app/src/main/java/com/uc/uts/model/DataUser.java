package com.uc.uts.model;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.uc.uts.R;

import java.util.ArrayList;

public class DataUser {
    public static ArrayList<User> userdata = new ArrayList<>();
    public static final Dialog loadingDialog(Context context){
        final Dialog dialog = new Dialog(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.screen_loading, null);
        dialog.setContentView(dialogView);
        dialog.setCancelable(false);
        return dialog;
    }
}
