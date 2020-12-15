package com.lifeng.stu_client2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * student实体类
 *
 * @author fengli
 * @version 1.0
 * @date 2020/11/05
 */
@Data
@AllArgsConstructor
public class Student implements Serializable {
    private Integer id; //学生id
    private String name;//学生姓名
    private Integer sex;//学生性别
    private double score;//分数

}
