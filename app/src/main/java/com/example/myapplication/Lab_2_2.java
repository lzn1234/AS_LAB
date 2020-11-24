package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.AlteredCharSequence;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Lab_2_2 extends AppCompatActivity {
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_2_2);
        Button btn = findViewById(R.id.dialogBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("btn","btn");
                dialog =  costomView(v);
                dialog.show();
            }
        });



    }

    public AlertDialog costomView(View sourse){
        LinearLayout loginForm = (LinearLayout)getLayoutInflater().inflate(R.layout.dialog_layout,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setView(loginForm);

        final AlertDialog dialog = builder.create();

        Button cancle = loginForm.findViewById(R.id.cancle);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button confirm = loginForm.findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        return dialog;
    }
}