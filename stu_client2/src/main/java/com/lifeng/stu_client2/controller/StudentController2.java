package com.lifeng.stu_client2.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lifeng.stu_client2.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
public class StudentController2 {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/list")
    public String list(@RequestParam(value = "pageIndex", required = false) Integer pageIndex, Model model) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("pageIndex=" + pageIndex);
        String url = "http://studentclient1/out/list";
        if (pageIndex != null && pageIndex > 0) {
            url = "http://studentclient1/out/list?pageIndex=" + pageIndex;
        }
        map = restTemplate.getForObject(url, Map.class);
        System.out.println(map.size());
        model.addAttribute("studentList", map.get("studentList"));
        model.addAttribute("pb", map.get("pb"));
        return "list";
    }

    /**
     * 添加学生信息
     *
     * @return
     */
    @GetMapping(value = "/addStudent")
    private String addStudent(Student student) throws UnsupportedEncodingException {
        System.out.println("添加学生");
        System.out.println("student=" + student);
        String value = JSON.toJSONString(student, SerializerFeature.WriteMapNullValue, SerializerFeature.UseSingleQuotes);
        System.out.println("value=" + value);
        value = URLEncoder.encode(value, "utf-8");
        String str = restTemplate.getForObject("http://studentclient1/out/addStudent?student=" + value, String.class);
        return str;
    }

    /**
     * 按id查询学生信息
     *
     * @param id
     */
    @GetMapping(value = "/findByid")
    private String findByid(@RequestParam(value = "code", required = true) String id, Model model) throws Exception {
        System.out.println("按id查询学生信息");
        Map map = this.restTemplate.getForObject("http://studentclient1/out/findByid?id=" + id, Map.class);
        model.addAttribute("student", map.get("student"));
        System.out.println(2222);
        String page = (String) map.get("page");
        return page;
    }

    /**
     * 修改学生信息
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/editStudent")
    private String editStudent(Student student, Model model) throws UnsupportedEncodingException {
        String value = JSON.toJSONString(student, SerializerFeature.WriteMapNullValue, SerializerFeature.UseSingleQuotes);
        value = URLEncoder.encode(value, "utf-8");
        String str = this.restTemplate.getForObject("http://studentclient1/out/editStudent?student=" + value, String.class);
        return str;
    }


    /**
     * 删除学生信息
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/delStudentInfo")
    private String delStudentInfo(@RequestParam(value = "ids") int id, Model model) {
        System.out.println("id" + id);
        String page = this.restTemplate.getForObject("http://studentclient1/out/delStudentInfo?id=" + id, String.class);
        //获取页面id
        return page;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
//        return this.restTemplate.getForObject("http://localhost:7900/hello1",String.class);
        return this.restTemplate.getForObject("http://studentclient1/hello1", String.class);
    }
}
