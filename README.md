@[TOC](Android_LAB)
## 实验二
### 线性布局

![线性布局](https://github.com/lzn1234/AS_LAB/blob/master/images/lab2/img1.JPG)

### 相对布局

![相对布局](https://github.com/lzn1234/AS_LAB/blob/master/images/lab2/img2.JPG)


### 表格布局

![表格布局](https://github.com/lzn1234/AS_LAB/blob/master/images/lab2/img3.JPG)


## 实验三 UI组件

### 1.利用SimpleAdapter实现如下界面效果


创建一个ListView并添加SimpleAdapter适配
```java

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
```

添加ListView item的点击事件
```java
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
```

ListVIew
![ListView](https://github.com/lzn1234/AS_LAB/blob/master/images/lab3/1.1.png){height="300"}
点击某一项
![ListView](https://github.com/lzn1234/AS_LAB/blob/master/images/lab3/1.2.png)
<img src="https://github.com/lzn1234/AS_LAB/blob/master/images/lab3/1.2.png"  height="330" width="495">

### 2.创建自定义布局的AlertDialog

使用Builder创建一个AlterDialog
```java
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
```

点击按钮弹出一个AlterDialog
![ListView](https://github.com/lzn1234/AS_LAB/blob/master/images/lab3/2.1.png)
![ListView](https://github.com/lzn1234/AS_LAB/blob/master/images/lab3/2.2.png)


### 3.使用后xml定义一个菜单

重写这个方法来创建一个菜单
```java
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

```

重写这个方法来实现菜单点击事件
```java
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

```

弹出菜单
![ListView](https://github.com/lzn1234/AS_LAB/blob/master/images/lab3/3.1.JPG)

设置字体大小
![ListView](https://github.com/lzn1234/AS_LAB/blob/master/images/lab3/3.2.JPG)

点击菜单项
![ListView](https://github.com/lzn1234/AS_LAB/blob/master/images/lab3/3.3.JPG)

设置字体颜色
![ListView](https://github.com/lzn1234/AS_LAB/blob/master/images/lab3/3.4.JPG)


### 4.创建上下文操作模式(ActionMode)的上下文菜单

创建一个ListView
```java
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
```

设置listView使用上下文模式
```java
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

```

进入视图
![ListView](https://github.com/lzn1234/AS_LAB/blob/master/images/lab3/4.1.JPG)

进入上下文操作模式并选择列表项
![ListView](https://github.com/lzn1234/AS_LAB/blob/master/images/lab3/4.2.JPG)

删除视图
![ListView](https://github.com/lzn1234/AS_LAB/blob/master/images/lab3/4.3.JPG)
