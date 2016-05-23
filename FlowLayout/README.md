## CustomView - FlowLayout  流式布局

#### 效果
动态添加子view，并可以自动换行。如一些标签的展示等。

#### 使用
将FlowLayout.java文件copy到项目中即可。
```
<com.widget.FlowLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">
</com.widget.FlowLayout>
```
java代码中：
```
private String[] strs = new String[] {
            "语文", "数学", "马克思列宁主义", "English", "物理", "心理学", "临床医学", "developer", "体育系", "电子工程", "机械制造"
    };
    
  FlowLayout fl_content = (FlowLayout) findViewById(R.id.fl_content);
      for(int i = 0; i < strs.length; i++) {
          TextView tv = new TextView(this);
          tv.setPadding(50, 25, 50, 25);

          FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
          lp.setMargins(25, 50, 25, 50);
          tv.setLayoutParams(lp);

          tv.setText(strs[i]);
          fl_content.addView(tv);
      }
```
