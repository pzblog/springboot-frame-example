package com.example.demo.dao;

import com.example.demo.model.entity.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper {
    /**
     * 查询所有数据
     * @return
     */
    List<Dept> findList();

    /**
     * 通过部门ID查询部门信息
     * @param deptId
     * @return
     */
    Dept findDeptInfo(@Param("deptId") Long deptId);

    /**
     * 新增数据
     * @param dept
     */
    void insertInfo(Dept dept);

    /**
     * 修改数据
     * @param dept
     */
    void updateInfo(Dept dept);

    /**
     * 删除数据
     */
    void deleteAll();
}
