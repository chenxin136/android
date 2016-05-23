package com.widget;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Activity {
    private FlowLayout fl_content;
    private String[] strs = new String[] {
            "语文", "数学", "马克思列宁主义", "English", "物理", "心理学", "临床医学", "developer", "体育系", "电子工程", "机械制造"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fl_content = (FlowLayout) findViewById(R.id.fl_content);
        for(int i = 0; i < strs.length; i++) {
            TextView tv = new TextView(this);
            tv.setPadding(50, 25, 50, 25);

            FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(25, 50, 25, 50);
            tv.setLayoutParams(lp);

            tv.setText(strs[i]);
            fl_content.addView(tv);
        }
    }
}
