package com.example.nic.ytuclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import com.example.nic.ytuclass.dao.CourseDao;
import com.example.nic.ytuclass.util.HttpHelper;
import com.example.nic.ytuclass.util.MyApplication;
import com.example.nic.ytuclass.util.ParseHelper;

public class ChooseClassActivity extends AppCompatActivity {
    EditText input_class;
    Spinner select_faculty;
    Button choose_btn;
    String _class, faculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_class);
        final CourseDao courseDao = new CourseDao(MyApplication.getContext());
        input_class = (EditText) findViewById(R.id.input_class);
        choose_btn = (Button) findViewById(R.id.choose_btn);
        select_faculty = (Spinner) findViewById(R.id.select_faculty);
        final String[] facultyArray = new String[]{"计", "食", "音", "药", "经", "生", "环", "海", "法", "材", "机", "新", "数", "建", "应",
                "外", "土", "化", "光", "体", "中"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, facultyArray);
        select_faculty.setAdapter(arrayAdapter);
        select_faculty.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                faculty = facultyArray[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        choose_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _class = input_class.getText().toString();
                courseDao.deleteAllCourse();
                putRequest(faculty, _class);
                Toast.makeText(ChooseClassActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
            }
        });

        //返回按钮
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("修改班级");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    //从服务器获取数据,并解析存入数据库
    private void putRequest(final String faculty, final String _class) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String temp = HttpHelper.getResponseStr(faculty, _class);
                    ParseHelper.parse(temp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
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
