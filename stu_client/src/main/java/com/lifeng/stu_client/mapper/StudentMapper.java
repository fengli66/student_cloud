package com.lifeng.stu_client.mapper;


import com.lifeng.stu_client.pojo.PageIndexer;
import com.lifeng.stu_client.pojo.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * StudentMapper接口
 * @author fengli
 * @version 1.0
 * @date 2020/12/09
 */
@Mapper
@Repository
public interface StudentMapper {
    /**
     * 查询所有
     */
    @Select("select * from student")
    List<Student> findAll();

    /**
     * 按id查询
     *
     * @param id
     * @return
     */
    @Select("select * from student where id=#{id}")
    Student findById(int id);

    /**
     * 修改
     *
     * @param student
     * @return
     */
    @Update("update student set name=#{name},sex=#{sex},score=#{score} where id=#{id}")
    boolean editByid(Student student);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Delete("delete from student where id=#{id}")
    boolean deleteById(int id);

    /**
     * 插入
     *
     * @param student
     * @return
     */
    @Insert(" insert into student(id,name,sex,score) values(#{id},#{name},#{sex},#{score})")
    boolean insert(Student student);

    /**
     * 按照文本模糊查找用户名
     *
     * @param txtsearch
     * @return
     */

    List<Student> findByLikeName(String txtsearch);

    /**
     * 获取students总数
     *
     * @return
     */
    @Select("select count(*) as count from student")
    int getCount();

    /**
     * 分页
     *
     * @param page
     * @return
     */
    @Select("select * from student limit #{pageIndex},#{pageSize}")
    List<Student> getStudentsByPage(PageIndexer page);


}
