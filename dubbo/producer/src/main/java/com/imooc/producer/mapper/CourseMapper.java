package com.imooc.producer.mapper;


import com.imooc.producer.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface CourseMapper {

    @Select("select * from course where valid = 1")
    List<Course> findValidCourses();
}
