@[TOC](Android_LAB)
## 实验二
### 线性布局

![线性布局](https://github.com/lzn1234/AS_LAB/blob/master/images/lab2/img1.JPG)

### 相对布局

![相对布局](https://github.com/lzn1234/AS_LAB/blob/master/images/lab2/img2.JPG)


### 表格布局

![表格布局](https://github.com/lzn1234/AS_LAB/blob/master/images/lab2/img3.JPG)


## 实验三 UI组件

### 利用SimpleAdapter实现如下界面效果


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

![ListView](https://github.com/lzn1234/AS_LAB/blob/master/images/lab2/img3.JPG)
![ListView](https://github.com/lzn1234/AS_LAB/blob/master/images/lab2/img3.JPG)
