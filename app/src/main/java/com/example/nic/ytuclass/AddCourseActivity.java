package com.example.nic.ytuclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nic.ytuclass.dao.CourseDao;
import com.example.nic.ytuclass.model.Course;
import com.example.nic.ytuclass.util.MyApplication;

import java.io.Serializable;

public class AddCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        setFinishOnTouchOutside(false);

        final EditText inputCourseName = (EditText) findViewById(R.id.course_name);
        final EditText inputDay = (EditText) findViewById(R.id.day);
        final EditText inputStart = (EditText) findViewById(R.id.class_begin);
        final EditText inputEnd = (EditText) findViewById(R.id.class_end);
        final EditText inputTeacher = (EditText) findViewById(R.id.teacher_name);
        final EditText inputClassRoom = (EditText) findViewById(R.id.class_room);
        final EditText inputRemark = (EditText) findViewById(R.id.class_room);
        final CourseDao courseDao = new CourseDao(MyApplication.getContext());

        Button addButton = (Button) findViewById(R.id.add_btn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName = inputCourseName.getText().toString();
                String day = inputDay.getText().toString();
                String start = inputStart.getText().toString();
                String end = inputEnd.getText().toString();
                String teacher = inputTeacher.getText().toString();
                String classRoom = inputClassRoom.getText().toString();
                String remark = inputRemark.getText().toString();

                if (courseName.equals("")) {
                    Toast.makeText(AddCourseActivity.this, "课程名称必须填写", Toast.LENGTH_SHORT).show();
                }
                if (day.equals("")) {
                    Toast.makeText(AddCourseActivity.this, "课程周次必须填写", Toast.LENGTH_SHORT).show();
                }
                if (start.equals("")) {
                    Toast.makeText(AddCourseActivity.this, "课程开始节次必须填写", Toast.LENGTH_SHORT).show();
                }
                if (end.equals("")) {
                    Toast.makeText(AddCourseActivity.this, "课程结束节次必须填写", Toast.LENGTH_SHORT).show();
                } else {
                    Course course = new Course(courseName, teacher, classRoom,
                            Integer.valueOf(day), Integer.valueOf(start), Integer.valueOf(end), remark);
                    courseDao.addCourse(course);
                    Intent intent = new Intent(AddCourseActivity.this, MainActivity.class);
                    intent.putExtra("course", course);
                    setResult(0, intent);
                    finish();
                }
            }
        });
        //返回按钮
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("添加课程");
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
