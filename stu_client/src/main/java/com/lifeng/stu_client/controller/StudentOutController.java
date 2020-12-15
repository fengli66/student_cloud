package com.lifeng.stu_client.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lifeng.stu_client.pojo.PageIndexer;
import com.lifeng.stu_client.pojo.Student;
import com.lifeng.stu_client.service.StudentService;
import com.lifeng.stu_client.uilt.ServiceInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/out")
public class StudentOutController {

    @Autowired
    private StudentService studentService;

    /**
     * student信息分页
     *
     * @param index page的下标，第几页了
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> studentList(@RequestParam(value = "pageIndex", required = false) Integer index) {
        Map<String, Object> map = new HashMap<>();
        int pageindex, pageSize, pageCount;
        PageIndexer page = new PageIndexer(1, 5, 0);
        //判断是否已经保存了分页session
        if (index != null) {
            page.setPageIndex(index);
        }
        List<Student> studentList = null;
        try {
            studentList = studentService.getStudentsByPage(page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取所有student获取总数
        page.setPageCount((int) Math.ceil(studentService.findAll().size() * 1.0 / page.getPageSize()));
        map.put("studentList", studentList);
        map.put("pb", page);
        return map;
    }

    /**
     * 添加学生信息
     *
     * @param student
     * @return
     */
    @RequestMapping(value = "/addStudent", method = RequestMethod.GET)
    @ResponseBody
    private String addStudent(@RequestParam("student") String student) throws UnsupportedEncodingException {
        System.out.println("添加学生");
        System.out.println(student);
        student = URLDecoder.decode(student, "utf-8");
        System.out.println(student);
        Student student1 =JSON.parseObject(student,Student.class);
        if (student1 != null) {
            int flag = studentService.insert(student1);
            if (flag == 1) {
                System.out.println("成功添加");
                return "redirect:list";
            } else if (flag == 0) {
                System.out.println("学生id已存在");
                return "addlist";
            } else {
                System.out.println("空间已满");
                return "addlist";
            }
        }
        return "addlist";
    }

    /**
     * 按id查询学生信息
     *
     * @param id
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/findByid")
    @ResponseBody
    private Map<String,Object> findByid(@RequestParam("id") String id) throws Exception {
        Map<String,Object> map=new HashMap<>();
        System.out.println("按id查询学生信息2");
        //页面传来的id
        System.out.println("id" + id);
        //使用业务逻辑层
        Student student = null;
        student = studentService.findById(Integer.parseInt(id));
        if (student == null) {
            System.out.println("没有该id的用户");
            map.put("page","findById");
        } else {
            map.put("page","editStudentInfo");
            map.put("student",student);
        }
        return map;
    }

    /**
     * 修改学生信息
     * @return
     */
//    @RequestMapping(value = "/editStudent",method = RequestMethod.GET)
    @GetMapping("/editStudent")
    @ResponseBody
    private String editStudent(@RequestParam("student") String student) throws UnsupportedEncodingException {
        System.out.println(student);
        student = URLDecoder.decode(student, "utf-8");
        System.out.println(student);
        Student student1 =JSON.parseObject(student,Student.class);
        //调用业务逻辑层
        System.out.println("student=" + student1);
        int flag = studentService.editByid(student1);
        if (flag == 1) {
            System.out.println("成功修改");
            return "redirect:list";
        } else {
            System.out.println("没有该学生");
            return "redirect:editStudent";
        }
    }

    /**
     * 删除学生信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delStudentInfo",method = RequestMethod.GET)
    private String delStudentInfo(@RequestParam("id") Integer id) {
        //获取页面id
        System.out.println("id=" + id);
        int flag = studentService.deleteById(id);
        if (flag > 0) {
            return "redirect:list";
        } else {
            return "error";
        }
    }
//hello
@GetMapping(value = "/hello1/{id}")
@ResponseBody
public String hello(@PathVariable String id){
    System.out.println("id="+id);
    System.out.println(ServiceInfoUtil.getport());
    return "请求成功";
}
}
