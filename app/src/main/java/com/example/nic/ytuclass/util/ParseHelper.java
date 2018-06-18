package com.example.nic.ytuclass.util;
import android.util.Log;

import com.example.nic.ytuclass.dao.CourseDao;
import com.example.nic.ytuclass.model.Course;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 解析json数据，存入数据库
 */
public class ParseHelper {
    static CourseDao courseDao = new CourseDao(MyApplication.getContext());
    public static void parse(String responseData){
        Course course = new Course();
        try {
            JSONObject jsonObject = new JSONObject(responseData);//每一层都是一个Object对象
            for (int i = 0;i < 42;i++){
                JSONObject courseObj = jsonObject.getJSONObject(String.valueOf(i)).getJSONObject("0");
                if (!courseObj.getString("kbName").equals("")){
                    course.setCourseName(courseObj.getString("kbName"));
                    course.setTeacher(courseObj.getString("kbTeacher"));
                    course.setClassRoom(courseObj.getString("kbAddr"));
                    course.setRemark(courseObj.getString("kbWeek"));
                    course.setDay(i%7+1);
                    course.setClassStart(((int)(i/7))*2+1);
                    course.setClassEnd(((int)(i/7))*2+2);
//                    Log.v("debug", course.toString());
                    courseDao.addCourse(course);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
