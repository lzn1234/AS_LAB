package com.example.myapplication;

        import androidx.appcompat.app.AppCompatActivity;

        import android.annotation.SuppressLint;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Gravity;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.LinearLayout;
        import android.widget.ListView;
        import android.widget.SimpleAdapter;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

public class Lab_2_1 extends AppCompatActivity {
    //列表视图
    private ListView listView;
    //数据数组
    private String[] names = new String[] {"Lion","Tiger","Monkey","Dog","Cat","Elephant"};
    private int[] images = new int[] {R.drawable.lion,R.drawable.tiger,R.drawable.monkey,R.drawable.dog,R.drawable.cat,R.drawable.elephant};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_2_1);
        //获取视图
        listView = (ListView) findViewById(R.id.listID);
        //创建List集合
        List<Map<String,Object>> listItems = new ArrayList<>();
        for (int i= 0 ; i < names.length ; i++)
        {
            Map<String,Object> listItem = new HashMap<>();
            listItem.put("name",names[i]);
            listItem.put("image",images[i]);
            listItems.add(listItem);
        }
        //创建SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,R.layout.list_item,new String[]{"name","image"},new int[]{R.id.name,R.id.image});
        listView.setAdapter(simpleAdapter);

        //设置点击事件监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("-CRAZYIT-", names[position] + "被单击了");
                view.setBackgroundColor(R.color.colorAccent);
                //自定义toast
                Toast toast = new Toast(Lab_2_1.this);
                toast.setGravity(Gravity.CENTER,0,400);
                //设置一个布局
                LinearLayout ll = new LinearLayout(Lab_2_1.this);
                //提示文字
                TextView textView = new TextView(Lab_2_1.this);
                textView.setText(names[position]);
                textView.setTextSize(8*2);
                textView.setTextColor(Color.WHITE);
                textView.setBackgroundColor(Color.GRAY);
                textView.setWidth(300);
                textView.setHeight(120);
                textView.setPadding(0,20,0,0);
                textView.setBackgroundResource(R.drawable.radius);
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                ll.addView(textView);
                //添加布局到toast
                toast.setView(ll);
                toast.show();
            }
        });


    }
}

