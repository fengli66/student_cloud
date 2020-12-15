package com.lifeng.stu_client.service;

import com.lifeng.stu_client.pojo.PageIndexer;
import com.lifeng.stu_client.pojo.Student;

import java.util.List;

/**
 * Student的业务逻辑层接口
 *
 * @author fengli
 * @version 1.0
 * @date 2020/12/09
 */
public interface StudentService {
    /**
     * 查询所有
     */
    List<Student> findAll();

    /**
     * 按id查询
     *
     * @param id
     * @return
     */
    Student findById(int id);

    /**
     * 修改
     *
     * @param student
     * @return
     */
    int editByid(Student student);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 插入
     *
     * @param student
     * @return
     */
    int insert(Student student);

    /**
     * 获取students总数
     *
     * @return
     */
    int getCount();

    /**
     * 分页
     *
     * @param page
     * @return
     */
    List<Student> getStudentsByPage(PageIndexer page);

}

