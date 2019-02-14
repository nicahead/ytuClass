# ytuClass
烟台大学课表app

#### 功能描述：
 本app使用教务系统api接口，可通过选择学院名称和班级一键导入班级课表，若课表信息有误还可以对课表进行修改，包括添加课程，修改课程，删除课程。详细运行结果见附录截图。

#### 结构分析：
本项目使用视图层、实体层和数据操作层三层架构，降低了层与层之间的依赖，实现了高内聚，低耦合”，利于各层逻辑的复用。Util包中包括自定义的一些工具函数，提高了代码复用率。结构较为清晰。
其中，view包中包括MainActivity、ChooseClassActivity、ChangeCourseActivity、AddCourseActivity、AboutActivity、ContactActivity七个类，分别对应七个页面
Dao包中包括CourseDao类，是对数据库的增删改查操作
Model包中包括实体类Course
Util包中包括数据库操作类DatabaseHelper、网络请求类HttpHelper、数据解析类ParseHelper


#### 运行截图：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190122223528574.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0ODM4NjQz,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190122223618379.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0ODM4NjQz,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190122223635352.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0ODM4NjQz,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190122223700118.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0ODM4NjQz,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/2019012222372520.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0ODM4NjQz,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190122223747401.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0ODM4NjQz,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190122223809102.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM0ODM4NjQz,size_16,color_FFFFFF,t_70)

