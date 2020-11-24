package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Console;

public class lab4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab4);

        Button btn = (Button) findViewById(R.id.button);
        final EditText text = (EditText)findViewById(R.id.urlText);
        text.setText("https://www.baidu.com/");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent 对象
                Intent intent = new Intent();
                //设置action
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra("urlText",String.valueOf(text.getText()));
                intent.setType("text/plain");

                System.out.println(text.getText());

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        System.out.println(text.getText());
    }
}