package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lab_2_4 extends AppCompatActivity {
    //列表视图
    private ListView listView;
    //数据数组
    private String[] text1 = new String[] {"Lion","Tiger","Monkey","Dog","Cat","Elephant"};
    private int[] images = new int[] {R.drawable.lion,R.drawable.tiger,R.drawable.monkey,R.drawable.dog,R.drawable.cat,R.drawable.elephant};
    private List<Map<String,Object>> listItems;
    //LIstView的选中视图
    private List<Integer> selectedList;
    private SimpleAdapter simpleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_2_4);
        //获取视图
        listView = (ListView) findViewById(R.id.listID1);
        //创建List集合
        listItems = new ArrayList<>();
        for (int i= 0 ; i < text1.length ; i++)
        {
            Map<String,Object> listItem = new HashMap<>();
            listItem.put("text",text1[i]);
            listItem.put("image",images[i]);
            listItems.add(listItem);
        }

        //创建SimpleAdapter
        simpleAdapter = new SimpleAdapter(this,listItems,R.layout.list_item1,new String[]{"text","image"},new int[]{R.id.text1,R.id.image1});
        listView.setAdapter(simpleAdapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        //        //创建上下文菜单
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                //点击对应item时修改对应状态
                LinearLayout item =  (LinearLayout) getViewByPosition(position,listView);
                int itemColor = checked ? Color.RED : Color.WHITE;
                item.setBackgroundColor(itemColor);

                if (checked){
                    selectedList.add((Integer)position);
                    item.setBackgroundColor(Color.RED);
                } else {
                    selectedList.remove((Integer)position);
                    item.setBackgroundColor(Color.WHITE);
                }

                //修改model的文字
                mode.setTitle(String.valueOf(selectedList.size())+" selected");
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                //初始化选中集合
                selectedList = new ArrayList<Integer>();
                //设置标题
                mode.setTitle("0 selected");
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu_1, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.delete:
                        Collections.sort(selectedList);
                        for ( int i = selectedList.size()-1 ; i>=0 ; i--)
                        {
                            LinearLayout itemView =  (LinearLayout) getViewByPosition((int)selectedList.get(i),listView);
                            itemView.setBackgroundColor(Color.WHITE);
                            listItems.remove((int)selectedList.get(i));
                        }
                        simpleAdapter.notifyDataSetChanged();
                        selectedList.clear();
                        mode.setTitle("0 selected");
                        break;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                //销毁时取消列表项选择状态
                for ( int i = 0 ; i<selectedList.size() ; i++)
                {
                    LinearLayout item =  (LinearLayout) getViewByPosition(selectedList.get(i),listView);
                    item.setBackgroundColor(Color.WHITE);
                }
                selectedList.clear();
            }
        });

        //获取导航栏
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    //根据position和listview获取某一项视图
     private View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition() ;
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }
}