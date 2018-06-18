package com.example.nic.ytuclass.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nic.ytuclass.model.Course;
import com.example.nic.ytuclass.util.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库操作类
 */
public class CourseDao{
    private DatabaseHelper dbHelper;
    public CourseDao(Context context) {
        //得到数据库对象
        dbHelper = new DatabaseHelper(context);
    }
    /**
     * 根据周次节次获取课程
     * @param day
     * @param classStart
     */
    public Course getCourse(String day,String classStart) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        Cursor cursor=db.query("courses",null,"day=? and classStart=?",new String[]{day,classStart},null,null,null);
        Course course = new Course();
        if(cursor.moveToFirst()){
            do{
                course.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                course.setCourseName(cursor.getString(cursor.getColumnIndex("courseName")));
                course.setTeacher(cursor.getString(cursor.getColumnIndex("teacher")));
                course.setClassRoom(cursor.getString(cursor.getColumnIndex("classRoom")));
                course.setDay(Integer.parseInt(cursor.getString(cursor.getColumnIndex("day"))));
                course.setClassStart(Integer.parseInt(cursor.getString(cursor.getColumnIndex("classStart"))));
                course.setClassEnd(Integer.parseInt(cursor.getString(cursor.getColumnIndex("classEnd"))));
                course.setRemark(cursor.getString(cursor.getColumnIndex("remark")));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return  course;
    }
    /**
     * 获取所有课程
     * @return 所有课程的列表
     */
    public List<Course> getAllCourse() {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        List<Course> result = new ArrayList<Course>();
        Course course;
        Cursor cursor = db.query("courses",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                course = new Course();
                course.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                course.setCourseName(cursor.getString(cursor.getColumnIndex("courseName")));
                course.setTeacher(cursor.getString(cursor.getColumnIndex("teacher")));
                course.setClassRoom(cursor.getString(cursor.getColumnIndex("classRoom")));
                course.setDay(Integer.parseInt(cursor.getString(cursor.getColumnIndex("day"))));
                course.setClassStart(Integer.parseInt(cursor.getString(cursor.getColumnIndex("classStart"))));
                course.setClassEnd(Integer.parseInt(cursor.getString(cursor.getColumnIndex("classEnd"))));
                course.setRemark(cursor.getString(cursor.getColumnIndex("remark")));
                result.add(course);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return  result;
    }
    /**
     * 添加课程
     * @param course
     */
    public void addCourse(Course course) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("courseName",course.getCourseName());
        values.put("teacher",course.getTeacher());
        values.put("classRoom",course.getClassRoom());
        values.put("day",course.getDay());
        values.put("classStart",course.getClassStart());
        values.put("classEnd",course.getClassEnd());
        values.put("remark",course.getRemark());
        db.insert("courses",null,values);
    }
    /**
     * 删除课程
     * @param id
     */
    public void deleteCourse(int id) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        db.delete("courses","id = ?",new String[]{id+""});
    }
    /**
     * 修改课程
     * @param course
     */
    public void updateCourse(Course course) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("courseName",course.getCourseName());
        values.put("teacher",course.getTeacher());
        values.put("classRoom",course.getClassRoom());
        values.put("day",course.getDay());
        values.put("classStart",course.getClassStart());
        values.put("classEnd",course.getClassEnd());
        values.put("remark",course.getRemark());
        db.update("courses",values,"id = ?",new String[]{course.getId()+""});
    }
    /**
     * 删除所有课程
     */
    public void deleteAllCourse() {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        db.execSQL("delete from courses");
    }
}
