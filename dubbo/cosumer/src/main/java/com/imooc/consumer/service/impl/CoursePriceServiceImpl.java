package com.imooc.consumer.service.impl;

import com.imooc.consumer.dao.CoursePriceMapper;
import com.imooc.consumer.entity.CourseAndPrice;
import com.imooc.consumer.entity.CoursePrice;
import com.imooc.consumer.service.CoursePriceService;
import com.imooc.producer.entity.Course;
import com.imooc.producer.service.CourseListService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CoursePriceServiceImpl implements CoursePriceService {

    @Autowired
    CoursePriceMapper coursePriceMapper;

    @Reference(version = "${demo.service.version}")
    CourseListService courseListService;


    @Override
    public CoursePrice getCoursePrice(Integer courseId) {
        return coursePriceMapper.findCoursePrice(courseId);
    }

    @Override
    public List<CourseAndPrice> getCoursesAndPrice() {
        List<CourseAndPrice> courseAndPriceList = new ArrayList<>();
        List<Course> courses = courseListService.getCourseList();
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            if(course != null){
                CoursePrice coursePrice = getCoursePrice(course.getCourseId());
                CourseAndPrice courseAndPrice = new CourseAndPrice();
                courseAndPrice.setPrice(coursePrice.getPrice());
                courseAndPrice.setName(course.getCourseName());
                courseAndPrice.setId(course.getId());
                courseAndPrice.setCourseId(course.getCourseId());
                courseAndPriceList.add(courseAndPrice);
            }
        }
        return courseAndPriceList;
    }
}
