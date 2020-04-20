package com.example.demo.model.vo;

import com.example.demo.model.entity.Dept;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class DeptVo {

    /**部门ID*/
    private Long deptId;

    /**部门名称*/
    private String deptName;

    /**部门创建日期，特定的日期格式单独格式化*/
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createDate;

    /**部门修改时间，默认格式化为yyyy-MM-dd HH:mm:ss*/
    private Date updateTime;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public DeptVo() {
    }

    public DeptVo(Dept dept) {
        this.deptId = dept.getDeptId();
        this.deptName = dept.getDeptName();
        this.createDate = dept.getCreateTime();
        this.updateTime = dept.getCreateTime();
    }

    public DeptVo(Long deptId, String deptName, Date createDate, Date updateTime) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.createDate = createDate;
        this.updateTime = updateTime;
    }
}
