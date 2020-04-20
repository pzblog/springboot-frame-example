package com.example.demo.model.entity;

import java.util.Date;

/**
 * 部门实体类
 */
public class Dept {

    /**部门ID*/
    private Long deptId;

    /**部门名称*/
    private String deptName;

    /**部门创建时间*/
    private Date createTime;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Dept() {
    }

    public Dept(String deptName) {
        this.deptName = deptName;
        this.createTime = new Date();
    }
}