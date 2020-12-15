package com.lifeng.stu_client.controller;


import com.lifeng.stu_client.pojo.PageIndexer;
import com.lifeng.stu_client.pojo.Student;
import com.lifeng.stu_client.service.StudentService;
import com.lifeng.stu_client.uilt.ServiceInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * student的控制层
 *
 * @author fengli
 * @version 1.0
 * @date 2020/11/25
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 添加学生信息
     *
     * @param student
     * @param model
     * @return
     */
    @RequestMapping(value = "/addStudent.do")
    private String addStudent(Student student, Model model) {
        System.out.println("添加学生");

        int flag = studentService.insert(student);
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

    /**
     * 按id查询学生信息
     *
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findByid.do")
    private String findByid(@RequestParam(value = "code", required = true) Integer id, Model model) throws Exception {
        System.out.println("按id查询学生信息");
        //页面传来的id
        System.out.println("id" + id);
        //使用业务逻辑层
        Student student = null;
        student = studentService.findById(id);
        if (student == null) {
            System.out.println("没有该id的用户");
            return "index";
        } else {
            model.addAttribute("student", student);
            return "editStudentInfo";
        }
    }

    /**
     * 修改学生信息
     *
     * @param student
     * @param model
     * @return
     */
    @RequestMapping(value = "/editStudent.do")
    private String editStudent(Student student, Model model) {

        //调用业务逻辑层
        System.out.println("student="+student);
        int flag = studentService.editByid(student);
        if (flag == 1) {
            System.out.println("成功修改");
            return "redirect:list";
        } else {
            System.out.println("没有该学生");
            return "redirect:list";
        }
    }

    /**
     * 删除学生信息
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/delStudentInfo.do")
    private String delStudentInfo(@RequestParam(value = "ids") int id, Model model) {
        //获取页面id
        System.out.println("id=" + id);
        int flag = studentService.deleteById(id);
        if (flag>0) {
            return "redirect:list";
        }else {
            return "error";
        }
    }

    /**
     *  student信息分页
     * @param index page的下标，第几页了
     *
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    private ModelAndView studentList(@RequestParam(value = "pageIndex",required = false) Integer index){
        int pageindex, pageSize, pageCount;
        ModelAndView model = new ModelAndView("list");
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
        model.addObject("studentList", studentList);
        model.addObject("pb", page);
        return model;
    }



    @GetMapping(value = "/hello1")
    @ResponseBody
    public String hello(){
        System.out.println(ServiceInfoUtil.getport());
        return "请求成功";
    }

}
