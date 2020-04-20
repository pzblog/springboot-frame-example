package com.example.lucene.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.lucene.search.Sort;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageQuery<T> {

    private Integer pageNum =1;

    private Integer pageSize = 10;

    private Long total;

    /**
     * 排序字段
     */
    private Sort sort;
    /**
     * 查询参数类
     */
    private T params;
    /**
     * 返回结果集
     */
    private List<T> results;

    /**
     * 不在T类中的参数
     */
    private String searchName;

}
