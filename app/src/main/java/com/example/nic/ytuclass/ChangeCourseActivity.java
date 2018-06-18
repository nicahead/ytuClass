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
import android.widget.Toast;

import com.example.nic.ytuclass.dao.CourseDao;
import com.example.nic.ytuclass.model.Course;
import com.example.nic.ytuclass.util.MyApplication;

public class ChangeCourseActivity extends AppCompatActivity {

    Spinner day, class_begin;
    EditText course_name, class_end, teacher_name, class_room, remark;
    String search_day = "1", search_begin = "1";
    Button updatebtn, deletebtn;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_course);
        final CourseDao courseDao = new CourseDao(MyApplication.getContext());
        day = (Spinner) findViewById(R.id.day);
        class_begin = (Spinner) findViewById(R.id.class_begin);
        course_name = (EditText) findViewById(R.id.course_name);
        class_end = (EditText) findViewById(R.id.class_end);
        teacher_name = (EditText) findViewById(R.id.teacher_name);
        class_room = (EditText) findViewById(R.id.class_room);
        remark = (EditText) findViewById(R.id.remark);
        updatebtn = (Button) findViewById(R.id.updatebtn);
        deletebtn = (Button) findViewById(R.id.deletebtn);
        final Course course = courseDao.getCourse("1", "1");
        changeView(course);
        final String[] days = new String[]{"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
        ArrayAdapter daysAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, days);
        day.setAdapter(daysAdapter);
        day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                search_day = position + 1 + "";
                Course course = courseDao.getCourse(search_day, search_begin);
                changeView(course);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        final String[] class_begins = new String[]{"1", "3", "5", "7", "9", "11"};
        ArrayAdapter beginsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, class_begins);
        class_begin.setAdapter(beginsAdapter);
        class_begin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                search_begin = 2 * position + 1 + "";
                Course course = courseDao.getCourse(search_day, search_begin);
                changeView(course);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (course_name.getText().toString().equals("")) {
                    Toast.makeText(ChangeCourseActivity.this, "课程名称必须填写", Toast.LENGTH_SHORT).show();
                }
                if (class_end.getText().toString().equals("")) {
                    Toast.makeText(ChangeCourseActivity.this, "课程结束节次必须填写", Toast.LENGTH_SHORT).show();
                } else {
                    Course course = new Course();
                    course.setId(id);
                    course.setDay(Integer.parseInt(search_day));
                    course.setClassStart(Integer.parseInt(search_begin));
                    course.setCourseName(course_name.getText() + "");
                    course.setClassEnd(Integer.parseInt(class_end.getText() + ""));
                    course.setTeacher(teacher_name.getText() + "");
                    course.setClassRoom(class_room.getText() + "");
                    course.setRemark(remark.getText() + "");
                    courseDao.updateCourse(course);
                    Toast.makeText(ChangeCourseActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseDao.deleteCourse(id);
                Toast.makeText(ChangeCourseActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
            }
        });

        //返回按钮
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("修改课程");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void changeView(Course course) {
        id = course.getId();
        course_name.setText(course.getCourseName());
        class_end.setText(course.getClassEnd() + "");
        teacher_name.setText(course.getTeacher());
        class_room.setText(course.getClassRoom());
        remark.setText(course.getRemark());
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
