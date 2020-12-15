package com.lifeng.stu_client.service.impl;

import com.lifeng.stu_client.mapper.StudentMapper;
import com.lifeng.stu_client.pojo.PageIndexer;
import com.lifeng.stu_client.pojo.Student;
import com.lifeng.stu_client.service.StudentService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Student的业务逻辑层接口实现类
 *
 * @author fengli
 * @version 1.0
 * @date 2020/12/09
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询所有
     */
    @Override
    public List<Student> findAll() {
        List<Student> students = studentMapper.findAll();
        //判断是否找到数据
        if (students.size() < 0) {
            students = null;
        }
        return students;
    }

    /**
     * 按id查询
     *
     * @param id
     * @return
     */
    @Override
    public Student findById(int id) {
        Student student = studentMapper.findById(id);
        if (student == null) {
            student = null;
        }
        return student;
    }

    /**
     * 修改
     *
     * @param student
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int editByid(Student student) {
        int flag = -1;
        if (studentMapper.findById(student.getId()) != null) {
            flag = 0;
            if (studentMapper.editByid(student)) {
                flag = 1;
            }
        }
        return flag;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteById(int id) {
        int flag=0;
        boolean b = studentMapper.deleteById(id);
        if (b){
            flag=1;
        }
        return flag;
    }

    /**
     * 插入
     *
     * @param student
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int insert(Student student) {
        int flag = -1;
        if (studentMapper.findById(student.getId()) != null) {
            flag = 0;
        } else {
            if (studentMapper.insert(student)) {
                flag = 1;
            }
        }
        return flag;

    }

    /**
     * 获取students总数
     *
     * @return
     */
    @Override
    public int getCount() {
        int count = studentMapper.getCount();
        return count;
    }

    /**
     * 分页
     *
     * @param page
     * @return
     */
    @Override
    public List<Student> getStudentsByPage(PageIndexer page) {
        page.setPageIndex((page.getPageIndex() - 1) * page.getPageSize());
        @NotNull
        List<Student> students = studentMapper.getStudentsByPage(page);
        return students;
    }
}
