package com.itxin.ServiceModel;

import com.itxin.AnnotateModel.Cache;
import com.itxin.domain.student;

import java.util.List;

public interface ServiceTwo {

    /**
     * 保存数据
     * @param stu
     */
    void saveStudentData(student stu);

    /**
     * 修改数据
     * @param stu
     */
    void updateStuData(student stu);

    /**
     * 根据id查询数据
     * @param stu
     * @return
     */
    student findById(student stu);

    /**
     * 查询所有数据
     * @return
     */
    @Cache
    List<student> findAll();

    /**
     * 根据学生姓名查询数据
     * @param stu
     * @return
     */
    List<student> findByName(student stu);

    /**
     * 根据获取的id删除表数据
     * @param stu
     */
    void deleteStuById(student stu);
}
