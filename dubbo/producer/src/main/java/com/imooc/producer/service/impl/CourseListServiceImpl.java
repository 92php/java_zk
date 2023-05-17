package com.imooc.producer.service.impl;

import com.imooc.producer.entity.Course;
import com.imooc.producer.mapper.CourseMapper;
import com.imooc.producer.service.CourseListService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service(version = "${demo.service.version}" )
public class CourseListServiceImpl implements CourseListService {

    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<Course> getCourseList() {
        return courseMapper.findValidCourses();
    }
}
