package com.example.nic.ytuclass;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nic.ytuclass.dao.CourseDao;
import com.example.nic.ytuclass.model.Course;
import com.example.nic.ytuclass.util.MyApplication;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RelativeLayout day; //星期几
    int currentCoursesNumber = 0;
    int maxCoursesNumber = 0;
    CourseDao courseDao = new CourseDao(MyApplication.getContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.drawable.bg);
        setContentView(R.layout.activity_main);
        //判断是否为第一次使用，需不需要选择班级
        judge();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        currentCoursesNumber = 0;
        maxCoursesNumber = 0;
        emptyView();
        loadData();
    }

    private void judge() {
        SharedPreferences shared = getSharedPreferences("is", MODE_PRIVATE);
        boolean isfer = shared.getBoolean("isfer", true);
        SharedPreferences.Editor editor = shared.edit();
        if (isfer) {
            //第一次进入跳转
            Intent intent = new Intent(MainActivity.this, ChooseClassActivity.class);
            startActivity(intent);
            editor.putBoolean("isfer", false);
            editor.commit();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_change) {
            Intent intent = new Intent(MainActivity.this, ChooseClassActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_add) {
            Intent AddCourseIntent = new Intent(MainActivity.this, AddCourseActivity.class);
            startActivityForResult(AddCourseIntent, 0);
        } else if (id == R.id.nav_refresh) {
            Intent intent = new Intent(MainActivity.this, ChangeCourseActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_contact) {
            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // 接收返回的参数
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == 0 && data != null) {
            Course course = (Course) data.getSerializableExtra("course");
            //创建课程表左边视图(节数)
            createLeftView(course);
            //创建课程表视图
            createCourseView(course);
        }
    }

    //从数据库加载数据
    private void loadData() {
        List<Course> courseList = courseDao.getAllCourse(); //课程列表
        //使用从数据库读取出来的课程信息来加载课程表视图
        for (Course course : courseList) {
            createLeftView(course);
            createCourseView(course);
        }
    }

    //创建课程节数视图
    private void createLeftView(Course course) {
        int len = course.getClassEnd();
        if (len > maxCoursesNumber) {
            for (int i = 0; i < len - maxCoursesNumber; i++) {
                View view = LayoutInflater.from(this).inflate(R.layout.order_view, null); //实例化view对象，加载布局
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, 180);
                view.setLayoutParams(params);
                TextView text = view.findViewById(R.id.class_number_text);
                text.setText(String.valueOf(++currentCoursesNumber));
                LinearLayout leftViewLayout = findViewById(R.id.left_view_layout);
                leftViewLayout.addView(view);
            }
        }
        maxCoursesNumber = len;
    }

    //创建课程视图
    private void createCourseView(final Course course) {
        int height = 180;
        int integer = course.getDay();
        if ((integer < 1 || integer > 7))
            Toast.makeText(this, "请填写正确的周次", Toast.LENGTH_LONG).show();
        else if (course.getClassStart() > course.getClassEnd())
            Toast.makeText(this, "课程结束时间比开始时间还早，请核对", Toast.LENGTH_LONG).show();
        else {
            switch (integer) {
                case 1:
                    day = findViewById(R.id.monday);
                    break;
                case 2:
                    day = findViewById(R.id.tuesday);
                    break;
                case 3:
                    day = findViewById(R.id.wednesday);
                    break;
                case 4:
                    day = findViewById(R.id.thursday);
                    break;
                case 5:
                    day = findViewById(R.id.friday);
                    break;
                case 6:
                    day = findViewById(R.id.saturday);
                    break;
                case 7:
                    day = findViewById(R.id.weekday);
                    break;
            }
            final View view = LayoutInflater.from(this).inflate(R.layout.course_card, null); //加载单个课程布局
            view.setY(height * (course.getClassStart() - 1)); //设置开始高度,即第几节课开始
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, (course.getClassStart() - course.getClassStart() + 2) * height); //设置布局高度,即跨多少节课
            view.setLayoutParams(params);
            TextView text = view.findViewById(R.id.text_view);
            text.setText(course.getCourseName() + "\n" + course.getTeacher() + "\n" + course.getClassRoom()); //显示课程名
            day.addView(view);
        }
    }

    private void emptyView() {
        LinearLayout leftViewLayout = findViewById(R.id.left_view_layout);
        leftViewLayout.removeAllViewsInLayout();
        day = findViewById(R.id.monday);
        day.removeAllViewsInLayout();
        day = findViewById(R.id.tuesday);
        day.removeAllViewsInLayout();
        day = findViewById(R.id.wednesday);
        day.removeAllViewsInLayout();
        day = findViewById(R.id.thursday);
        day.removeAllViewsInLayout();
        day = findViewById(R.id.friday);
        day.removeAllViewsInLayout();
        day = findViewById(R.id.saturday);
        day.removeAllViewsInLayout();
        day = findViewById(R.id.weekday);
        day.removeAllViewsInLayout();
    }
}
