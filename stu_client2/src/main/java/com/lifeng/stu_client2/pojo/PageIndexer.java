package com.lifeng.stu_client2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class PageIndexer implements Serializable {
    private int pageIndex;//页码
    private int pageSize;//每页行数
    private int pageCount;//总页数

}
