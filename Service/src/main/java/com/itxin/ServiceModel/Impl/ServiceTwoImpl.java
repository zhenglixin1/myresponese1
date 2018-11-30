package com.itxin.ServiceModel.Impl;

import com.itxin.AnnotateModel.Cache;
import com.itxin.ModelDao.MavenDao;
import com.itxin.ServiceModel.ServiceTwo;
import com.itxin.domain.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceTwoImpl implements ServiceTwo {
    @Autowired
    private MavenDao md;

    public void saveStudentData(student stu) {
        md.saveStudentData(stu);
    }

    public void updateStuData(student stu) {
        md.updateStuData(stu);
    }

    public student findById(student stu) {
        return md.findById(stu);
    }
    @Cache
    public List<student> findAll() {
        return md.findAll();
    }

    public List<student> findByName(student stu) {
        return md.findByName(stu);
    }

    public void deleteStuById(student stu) {
        md.deleteStuById(stu);
    }
}
