package com.example.nic.ytuclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.nic.ytuclass.model.Course;
import com.example.nic.ytuclass.util.HttpHelper;
import com.example.nic.ytuclass.util.ParseHelper;

import org.litepal.crud.DataSupport;

public class AboutActivity extends AppCompatActivity {
    TextView about_info,version_content1,version_title1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        about_info = (TextView)findViewById(R.id.about_info);
        about_info.setText("ytuClass是一款为烟大学生服务的APP，使用mayuko.cn学长的api接口，获取json数据并进行转码，展示在手机上，作为安卓课的大作业。目前功能并不完善，界面也比较low，希望以后等我变强了可以继续完善~~~");
        version_title1 = (TextView)findViewById(R.id.version_title1);
        version_title1.setText("V1.0");
        version_content1 = (TextView)findViewById(R.id.version_content1);
        version_content1.setText("1.无需手动添加课程，输入班级学号即可获取班级课表 \n2.可对课表进行修改，包括添加课程，修改课程，删除课程");
        //返回按钮
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("关于课表");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
