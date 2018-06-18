package com.example.nic.ytuclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    TextView aboutme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        aboutme = (TextView)findViewById(R.id.aboutme);
        aboutme.setText("你好，我是倪畅，计155班CS小菜鸡一枚，你可以发送邮件到nicahead@gmail.com与我交流，或者访问我的个人主页www.nichang.site");
        //返回按钮
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("联系作者");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
