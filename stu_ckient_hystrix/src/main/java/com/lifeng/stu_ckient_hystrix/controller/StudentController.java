package com.lifeng.stu_ckient_hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 学生控制器
 */
@RestController
public class StudentController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/findById/{id}")
    @HystrixCommand(fallbackMethod = "fallbackInfo")
    public String findByid(@PathVariable String id) {
        System.out.println(id);
        return this.restTemplate.getForObject("http://studentclient1/out/hello1/"+id, String.class);
    }
    /**
     * 返回信息方法
     * @param id
     * @return
     */
    public String fallbackInfo(@PathVariable String id){
        return "服务不可用，请稍后再试！";
    }
    
}
