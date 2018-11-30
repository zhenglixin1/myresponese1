package com.itxin;

import com.itxin.ModelDao.MavenDao;
import com.itxin.domain.student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class daotest {
    @Test
    public void daote(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("apllicationConfig-dao.xml");
        MavenDao bean = applicationContext.getBean("mavenDao", MavenDao.class);
        List<student> all = bean.findAll();
        for (student student : all) {
            System.out.println(student);
        }
    }
}
