package com.itxin;

import com.itxin.ServiceModel.ServiceTwo;
import com.itxin.domain.student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class service {
    @Test
    public void test(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationConfig-Service.xml");
        ServiceTwo st = classPathXmlApplicationContext.getBean("st", ServiceTwo.class);
        List<student> all = st.findAll();
        for (student student : all) {
            System.out.println(student);
        }

    }
}
