package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;
import android.widget.Toast;

public class lab_2_3 extends AppCompatActivity {

    //字体大小标识
    private static final int FONT_S = 0x111;
    private static final int FONT_M = 0x112;
    private static final int FONT_L = 0x113;


    //普通菜单项标识
    private static final int PLAIN_ITEM = 0x11b;

    //字体颜色标识
    private static final int COLOR_RED = 0x114;
    private static final int COLOR_BLACR = 0x115;

    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_2_3);
        text = findViewById(R.id.testText);
    }

    //重写这个方法来创建一个菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //字体大小
        SubMenu fontMenu = menu.addSubMenu("字体大小");
        fontMenu.add(0,FONT_S,0,"小号字体");
        fontMenu.add(0,FONT_M,0,"中号字体");
        fontMenu.add(0,FONT_L,0,"大号字体");
        //普通菜单项
        menu.add(0,PLAIN_ITEM,0,"普通菜单项");
        

        //字体颜色
        SubMenu fontColorMenu = menu.addSubMenu("字体颜色");
        fontColorMenu.add(0,COLOR_RED,0,"RED");
        fontColorMenu.add(0,COLOR_BLACR,0,"BLACK");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case FONT_S:text.setTextSize(10*2); break;
            case FONT_M:text.setTextSize(16*2); break;
            case FONT_L:text.setTextSize(20*2); break;
            case PLAIN_ITEM:showToast("普通菜单项"); break;
            case COLOR_RED:text.setTextColor(Color.RED); break;
            case COLOR_BLACR:text.setTextColor(Color.BLACK); break;

        }

        return super.onOptionsItemSelected(item);

    }

    public void showToast(String text)
    {
        Toast toast = Toast.makeText(lab_2_3.this,text,Toast.LENGTH_SHORT);
        toast.show();
    }
}